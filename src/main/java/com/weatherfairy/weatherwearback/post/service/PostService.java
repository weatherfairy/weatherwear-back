package com.weatherfairy.weatherwearback.post.service;

import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostResponse;
import com.weatherfairy.weatherwearback.post.entity.Post;
import com.weatherfairy.weatherwearback.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GetCurrentData getCurrentData;

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

        System.out.println("tempCategory = " + tempCategory);
        System.out.println("skyCategory = " + skyCategory);

        List<Post> posts = postRepository.findRecentPostsBySkyAndTempCategory(memberNo, SkyCategory.from(skyCategory), tempCategory);

        return posts.stream()
                .map(GetPostResponse::from)
                .collect(Collectors.toList());
    }


}
