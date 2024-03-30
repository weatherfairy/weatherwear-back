package com.weatherfairy.weatherwearback.post.controller;

import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostResponse;
import com.weatherfairy.weatherwearback.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/v1/closet/lists/{postNo}")
    public ResponseEntity<GetPostResponse> getPost1(@PathVariable Long postNo) {

        GetPostResponse response = postService.getPost(postNo);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/closet/recommend")
    public ResponseEntity<List<GetPostResponse>> getRecommendedPosts(@RequestParam("location") String locationName) {
        Long memberNo = 1L;

        List<GetPostResponse> recommendedPosts = postService.getRecommendedPosts(memberNo, locationName);

        return ResponseEntity.ok(recommendedPosts);
    }


}
