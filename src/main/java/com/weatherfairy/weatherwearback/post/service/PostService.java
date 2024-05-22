package com.weatherfairy.weatherwearback.post.service;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weatherfairy.weatherwearback.common.auth.JwtTokenProvider;
import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
import com.weatherfairy.weatherwearback.common.enums.Emoji;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.post.dto.request.CreatePostRequest;
import com.weatherfairy.weatherwearback.post.dto.request.PostFilterCriteria;
import com.weatherfairy.weatherwearback.post.dto.request.UpdatePostRequest;
import com.weatherfairy.weatherwearback.post.dto.response.CreatePostResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostResponse;
import com.weatherfairy.weatherwearback.post.dto.response.UpdatePostResponse;
import com.weatherfairy.weatherwearback.post.entity.Post;
import com.weatherfairy.weatherwearback.post.entity.QPost;
import com.weatherfairy.weatherwearback.post.entity.vo.WeatherDataVO;
import com.weatherfairy.weatherwearback.post.repository.PostRepository;
import com.weatherfairy.weatherwearback.weatherdata.entity.WeeklyData;
import com.weatherfairy.weatherwearback.weatherdata.repository.WeeklyDataRepository;
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

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GetCurrentData getCurrentData;
    private final JPAQueryFactory jpaQueryFactory;
    private final WeeklyDataRepository weeklyDataRepository;
    private final ChatGPTService chatGPTService;
    private final JwtTokenProvider jwtTokenProvider;


    @Value("${firebase.bucket}")
    private String firebaseBucket;


    @Value("${firebase.storage-link}")
    private String storageLink;

    private final static int IMAGE_RESIZE_TARGET_WIDTH = 650;


    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return fileName.substring(dotIndex + 1);
    }

//    private byte[] compressImage(MultipartFile multipartFile) throws IOException {
//
//        BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());
//        BufferedImage resizedImage =
//                Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, IMAGE_RESIZE_TARGET_WIDTH, Scalr.THRESHOLD_QUALITY_BALANCED);
//        String fileExtension = getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ImageIO.write(resizedImage, fileExtension, outputStream);
//
//        return outputStream.toByteArray();
//    }


//    private InputStream compressImage(InputStream inputStream) {
//        try {
//            BufferedImage originalImage = ImageIO.read(inputStream);
//            int imageWidth = originalImage.getWidth();
//            int imageHeight = originalImage.getHeight();
//            double outputQuality = 1.0;
//
//            // 압축된 이미지 생성 및 리사이징 반복
//            while (true) {
//                BufferedImage compressedImage = Thumbnails.of(originalImage)
//                        .size(imageWidth, imageHeight)
//                        .outputQuality(outputQuality)
//                        .asBufferedImage();
//
//                // 포맷 형식을 webp로 설정 -> 용량을 낮추기 위해
//                ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream();
//                ImageIO.write(compressedImage, "webp", compressedOutputStream);
//
//                byte[] compressedData = compressedOutputStream.toByteArray();
//                int compressedSizeKB = compressedData.length / 1024;  // 바이트를 킬로바이트로 변환
//
//                // 압축된 이미지의 크기가 2MB 이하인 경우 반환
//                if (compressedSizeKB <= 2000) {
//                    return new ByteArrayInputStream(compressedData);
//                }
//
//                // 이미지의 크기가 2MB를 넘어갈 경우 퀄리티 조절, 리사이징
//                outputQuality -= 0.1;
//                imageWidth /= 5;
//                imageHeight /= 5;
//
//                if (outputQuality <= 0.5) {
//                    // 품질이 너무 낮아져도 2MB 이하 크기를 달성하지 못하는 경우, 예외처리
//                    throw new IllegalArgumentException("이미지 용량이 너무 큽니다.");
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private String saveImage(MultipartFile img) throws IOException {

//        byte[] compressedImage = compressImage(img);

        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        String name = UUID.randomUUID().toString();
        InputStream content = new ByteArrayInputStream(img.getBytes());
        Blob blob = bucket.create(name, content, img.getContentType());

        return storageLink + name + "?alt=media";

    }

    @Transactional(readOnly = true)
    public GetPostResponse getPost(Long postNo) {

        Post post = postRepository.findById(postNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        return new GetPostResponse(post.getPostId(), post.getImage1(), post.getImage2(), post.getImage3(),
                post.getDate(), post.getWeatherDataVO().getMinTemp(), post.getWeatherDataVO().getMaxTemp(), post.getClothes(),
                post.getReview(), post.getEmoji().getValue(), post.getWeatherDataVO().getSky().getValue());
    }


    @Transactional
    public CreatePostResponse createPost(String token,
                                         MultipartFile image1,
                                         MultipartFile image2,
                                         MultipartFile image3,
                                         CreatePostRequest request) throws IOException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

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

        float mean = (request.max() + request.min())/2;

        TempCategory tempCategory = getCurrentData.returnTempCategory(mean);

        WeatherDataVO weatherDataVO = WeatherDataVO.builder()
                .sky(SkyCategory.from(request.sky()))
                .maxTemp(request.max())
                .minTemp(request.min())
                .tempCategory(tempCategory)
                .build();

        Post post = postRepository.save(Post.builder()
                .memberNo(memberNo)
                .date(LocalDate.now())
                .clothes(request.clothes())
                .review(request.review())
                .emoji(Emoji.from(request.emoji()))
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
    public List<GetPostsResponse> getPostsByFilter(String token, PostFilterCriteria criteria) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        QPost post = QPost.post;

        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(post.memberNo.eq(memberNo));

        if (criteria.month() != null && !criteria.month().isEmpty()) {
            whereClause.and(post.date.month().in(criteria.month()));
        }

        if (criteria.min() != null) {
            whereClause.and(post.weatherDataVO.minTemp.goe(criteria.min()));
        }

        if (criteria.max() != null) {
            whereClause.and(post.weatherDataVO.maxTemp.loe(criteria.max()));
        }

        if (criteria.emoji() != null) {
            whereClause.and(post.emoji.in(Emoji.fromList(criteria.emoji())));
        }

        if (criteria.sky() != null) {
            whereClause.and(post.weatherDataVO.sky.in(SkyCategory.fromList(criteria.sky())));
        }

        List<Post> posts = jpaQueryFactory.selectFrom(post)
                .where(whereClause)
                .fetch();

        return posts.stream()
                .map(GetPostsResponse::from)
                .collect(Collectors.toList());

    }
  
    @Transactional(readOnly = true)
    public List<GetPostResponse> getRecommendedPosts(String token, String locationName) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        TempCategory tempCategory = getCurrentData.returnCurrentTempCategory(locationName);
        int skyCategory = getCurrentData.returnCurrentSkyCategory(locationName);

        List<Post> posts = postRepository.findRecentPostsBySkyAndTempCategory(memberNo, SkyCategory.from(skyCategory), tempCategory);

        return posts.stream()
                .map(GetPostResponse::from)
                .collect(Collectors.toList());
    }


    public static List<Long> extractNumbers(String str) {
        List<Long> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            numbers.add(Long.parseLong(matcher.group()));
        }
        return numbers;
    }

    @Transactional(readOnly = true)
    public List<GetPostResponse> getRecommendedPostsByGPT(String token, String locationName) {

        ArrayList<Object> temps = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

        try {
            List<List<Object>> memberPosts = postRepository.getDataAsList(1L);

            Optional<WeeklyData> weeklyData = weeklyDataRepository.findByLocationName(locationName);

            temps.add(weeklyData.get().getMinTemp().get(0));
            temps.add(weeklyData.get().getMaxTemp().get(0));
            temps.add(SkyCategory.from(weeklyData.get().getDaySky().get(0)));

            String response = chatGPTService.sendRequest(temps.toString(), memberPosts.toString());

            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

            String content = jsonObject.getAsJsonArray("choices").get(0)
                    .getAsJsonObject().get("message").getAsJsonObject()
                    .get("content").getAsString();

            List<Long> numbers = extractNumbers(content);
            for (Long number : numbers) {
                Optional<Post> post = postRepository.findById(number);
                if (post.isPresent()) {
                    posts.add(post.get());
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return getRecommendedPosts(token, locationName);
        }

        return posts.stream()
                .map(GetPostResponse::from)
                .collect(Collectors.toList());
    }


    @Transactional
    public UpdatePostResponse updatePost(Long postNo, String token, MultipartFile image1, MultipartFile image2, MultipartFile image3, UpdatePostRequest request) throws IOException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
        Post post = postRepository.findById(postNo)
                        .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        isWriter(memberNo, post.getMemberNo());

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

        post.update(request, image1Url, image2Url, image3Url);

        return UpdatePostResponse.from(post);

    }

    private void isWriter(Long memberNo, Long postWriterNo) {
        if (!postWriterNo.equals(memberNo)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}