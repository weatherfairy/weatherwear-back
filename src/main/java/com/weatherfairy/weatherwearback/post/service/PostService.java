package com.weatherfairy.weatherwearback.post.service;

import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetRecommendResponse;
import com.weatherfairy.weatherwearback.post.entity.Post;
import com.weatherfairy.weatherwearback.post.repository.PostMapper;
import com.weatherfairy.weatherwearback.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Page<GetPostsResponse> getPostsScroll(Pageable pageable, Long memberNo) {

        Page<Post> postsList = postRepository.findAllByMemberNo(memberNo, pageable);

        return postsList.map(GetPostsResponse::from);
    }

    @Transactional(readOnly = true)
    public List<GetPostsResponse> getFilteredPosts(Map<String, Object> filters) {

        List<Post> postsList = postMapper.getFilteredPosts(filters);
        return postsList.stream()
                .map(GetPostsResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GetRecommendResponse> getRecommendedPosts(Long memberNo) {

        List<GetRecommendResponse> dummyData = new ArrayList<>();

        // 첫 번째 더미 데이터 생성
        GetRecommendResponse dummyResponse1 = new GetRecommendResponse(
                1L, // postNo
                "https://example.com/image1.jpg",
                LocalDate.now(),
                10.0f,
                20.0f,
                "Dummy clothes text 1",
                "Dummy review 1",
                0,
                0
        );
        dummyData.add(dummyResponse1);

        // 두 번째 더미 데이터 생성
        GetRecommendResponse dummyResponse2 = new GetRecommendResponse(
                2L, // postNo
                "https://example.com/image2.jpg",
                LocalDate.now(),
                11.0f,
                21.0f,
                "Dummy clothes text 2",
                "Dummy review 2",
                1,
                1
        );
        dummyData.add(dummyResponse2);

        // 세 번째 더미 데이터 생성
        GetRecommendResponse dummyResponse3 = new GetRecommendResponse(
                3L,
                "https://example.com/image3.jpg",
                LocalDate.now(),
                12.0f,
                22.0f,
                "Dummy clothes text 3",
                "Dummy review 3",
                2,
                2
        );
        dummyData.add(dummyResponse3);

        return dummyData;

    }
}
