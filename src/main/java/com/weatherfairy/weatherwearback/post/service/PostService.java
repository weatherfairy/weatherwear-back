package com.weatherfairy.weatherwearback.post.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
import com.weatherfairy.weatherwearback.common.enums.Emoji;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.post.dto.request.CreatePostRequest;
import com.weatherfairy.weatherwearback.post.dto.response.CreatePostResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostResponse;
import com.weatherfairy.weatherwearback.post.entity.Post;
import com.weatherfairy.weatherwearback.post.entity.vo.WeatherDataVO;
import com.weatherfairy.weatherwearback.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GetCurrentData getCurrentData;

    @Value("${firebase.bucket}")
    private String firebaseBucket;

    @Value("${firebase.storage-link}")
    private String storageLink;

    private InputStream compressImage(InputStream inputStream) {
        try {
            BufferedImage originalImage = ImageIO.read(inputStream);
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();
            double outputQuality = 1.0;

            // 압축된 이미지 생성 및 리사이징 반복
            while (true) {
                BufferedImage compressedImage = Thumbnails.of(originalImage)
                        .size(imageWidth, imageHeight)
                        .outputQuality(outputQuality)
                        .asBufferedImage();

                // 포맷 형식을 webp로 설정 -> 용량을 낮추기 위해
                ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream();
                ImageIO.write(compressedImage, "webp", compressedOutputStream);

                byte[] compressedData = compressedOutputStream.toByteArray();
                int compressedSizeKB = compressedData.length / 1024;  // 바이트를 킬로바이트로 변환

                // 압축된 이미지의 크기가 2MB 이하인 경우 반환
                if (compressedSizeKB <= 2000) {
                    return new ByteArrayInputStream(compressedData);
                }

                // 이미지의 크기가 2MB를 넘어갈 경우 퀄리티 조절, 리사이징
                outputQuality -= 0.1;
                imageWidth /= 5;
                imageHeight /= 5;

                if (outputQuality <= 0.5) {
                    // 품질이 너무 낮아져도 2MB 이하 크기를 달성하지 못하는 경우, 예외처리
                    throw new IllegalArgumentException("이미지 용량이 너무 큽니다.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String saveImage(MultipartFile img) {


        try (InputStream inputStream = img.getInputStream()) {
            // 이미지 압축
            InputStream compressedInputStream = compressImage(inputStream);

            // Firebase Storage에 이미지 저장
            Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
            String name = UUID.randomUUID().toString();
            InputStream content = new ByteArrayInputStream(compressedInputStream.readAllBytes());
            Blob blob = bucket.create(name, content, img.getContentType());

            return storageLink + name + "?alt=media";

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

//    @Transactional(readOnly = true)
//    public Page<GetPostsResponse> getPostsScroll(Pageable pageable, Long memberNo) {
//
//        Page<Post> postsList = postRepository.findAllByMemberNo(memberNo, pageable);
//
//        return postsList.map(GetPostsResponse::from);
//    }

    @Transactional(readOnly = true)
    public GetPostResponse getPost(Long postNo) {

        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        return new GetPostResponse(post.getPostId(), post.getImage1(), post.getImage2(), post.getImage3(),
                post.getDate(), post.getWeatherDataVO().getMinTemp(), post.getWeatherDataVO().getMaxTemp(), post.getClothes(),
                post.getReview(), post.getEmoji().getValue(), post.getWeatherDataVO().getSky().getValue());
    }

    @Transactional(readOnly = true)
    public List<GetPostResponse> getRecommendedPosts(Long memberNo, String locationName) {

        TempCategory tempCategory = getCurrentData.returnCurrentTempCategory(locationName);
        int skyCategory = getCurrentData.returnCurrentSkyCategory(locationName);

        List<Post> posts = postRepository.findRecentPostsBySkyAndTempCategory(memberNo, SkyCategory.from(skyCategory), tempCategory);

        return posts.stream()
                .map(GetPostResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreatePostResponse createPost(MultipartFile image1,
                                         MultipartFile image2,
                                         MultipartFile image3,
                                         CreatePostRequest request) {

        String image1Url = null;
        String image2Url = null;
        String image3Url = null;

        if (image1 != null) {
            image1Url = saveImage(image1);
        }
        if (image2 != null) {
            image2Url = saveImage(image2);
        }
        if (image3 != null) {
            image3Url = saveImage(image3);
        }

        WeatherDataVO weatherDataVO = WeatherDataVO.builder()
                .sky(SkyCategory.SNOW)
                .maxTemp(request.max())
                .minTemp(request.min())
                .build();

        Post post = postRepository.save(Post.builder()
                .memberNo(1L)
                .date(LocalDate.now())
                .clothes(request.clothes())
                .review(request.review())
                .emoji(Emoji.HAPPY)
                .weatherDataVO(weatherDataVO)
                .image1(image1Url)
                .image2(image2Url)
                .image3(image3Url)
                .build());

        return CreatePostResponse.from(post);

    }

    @Transactional
    public boolean deletePost(Long postNo) {

        try {
            Post post = postRepository.findById(postNo)
                    .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));
            postRepository.delete(post);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<GetPostsResponse> getAllPosts(Long memberNo) {

        List<Post> postsList = postRepository.findAllByMemberNo(memberNo);

        return postsList.stream()
                .map(GetPostsResponse::from)
                .collect(Collectors.toList());

    }
}
