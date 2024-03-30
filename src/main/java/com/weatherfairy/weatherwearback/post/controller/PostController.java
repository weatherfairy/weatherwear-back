package com.weatherfairy.weatherwearback.post.controller;

import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetRecommendResponse;
import com.weatherfairy.weatherwearback.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/closet/lists")
    public ResponseEntity<Page<GetPostsResponse>> getAllPosts(@RequestBody Map<String, Object> filters) {

        Long memberNo = 1L;


        Pageable pageable = PageRequest.of((Integer) filters.get("page"), (Integer) filters.get("size"));

        Page<GetPostsResponse> response = postService.getPostsScroll(pageable, memberNo);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/closet/lists/1")
    public ResponseEntity<GetRecommendResponse> getPost1() {

        Long memberNo = 1L;

        GetRecommendResponse dummyResponse1 = new GetRecommendResponse(
                1L, // postNo
                "https://picsum.photos/200/300",
                "https://picsum.photos/200/300",
                "https://picsum.photos/200/300",
                LocalDate.now(),
                10.0f,
                20.0f,
                "Dummy clothes text 1",
                "Dummy review 1",
                3,
                1
        );


        return ResponseEntity.ok(dummyResponse1);
    }

    @GetMapping("/api/v1/closet/recommend")
    public ResponseEntity<List<GetRecommendResponse>> getRecommendedPosts() {
        Long memberNo = 1L;

        List<GetRecommendResponse> recommendedPosts = postService.getRecommendedPosts(memberNo);

        return ResponseEntity.ok(recommendedPosts);
    }


}
