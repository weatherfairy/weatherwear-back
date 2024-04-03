package com.weatherfairy.weatherwearback.post.service;

import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GetCurrentData getCurrentData;

    private static String root = "C:\\Users\\jungi\\바탕 화면\\WeatherWear\\weatherwear-back\\weatherwear-back\\src\\main\\resources";
    private static String filePath = root + "/images";

    private String saveImage(MultipartFile image) {

        if (image == null || image.isEmpty()) {
            return null;
        }

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        try {
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            File uploadDir = new File(filePath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uploadPath = filePath + File.separator + uniqueFileName;
            image.transferTo(new File(uploadPath));

            return uploadPath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Page<GetPostsResponse> getPostsScroll(Pageable pageable, Long memberNo) {

        Page<Post> postsList = postRepository.findAllByMemberNo(memberNo, pageable);

        return postsList.map(GetPostsResponse::from);
    }

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

        String image1Url = saveImage(image1);
        String image2Url = saveImage(image2);
        String image3Url = saveImage(image3);

        WeatherDataVO weatherDataVO = WeatherDataVO.builder()
                .sky(request.sky())
                .maxTemp(request.maxTemp())
                .minTemp(request.minTemp())
                .build();

        Post post = postRepository.save(Post.builder()
                .memberNo(1L)
                .date(LocalDate.now())
                .clothes(request.clothesText())
                .review(request.review())
                .emoji(request.emoji())
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
}
