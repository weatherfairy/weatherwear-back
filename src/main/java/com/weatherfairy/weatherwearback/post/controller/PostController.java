package com.weatherfairy.weatherwearback.post.controller;

import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetRecommendResponse;
import com.weatherfairy.weatherwearback.post.entity.Post;
import com.weatherfairy.weatherwearback.post.service.PostService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/closet/lists")
    public ResponseEntity<List<GetPostsResponse>> getFilteredPosts(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                       @RequestParam(defaultValue = "12") @Positive int size,
                                                       @RequestBody Map<String, Object> filters) {
        Long memberNo = 1L;
        filters.put("memberNo", memberNo);

        // 페이지 처리를 위한 계산
        int offset = page * size;
        filters.put("offset", offset);
        filters.put("limit", size);

        List<GetPostsResponse> filteredPosts = postService.getFilteredPosts(filters);

        return ResponseEntity.ok(filteredPosts);
    }

    @GetMapping("/api/v1/closet/recommend")
    public ResponseEntity<List<GetRecommendResponse>> getRecommendedPosts() {
        Long memberNo = 1L;

        List<GetRecommendResponse> recommendedPosts = postService.getRecommendedPosts(memberNo);

        return ResponseEntity.ok(recommendedPosts);
    }
}
