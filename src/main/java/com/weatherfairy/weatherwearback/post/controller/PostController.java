package com.weatherfairy.weatherwearback.post.controller;

import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.service.PostService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/closet/lists")
    public ResponseEntity<Page<GetPostsResponse>> getAllPosts(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                              @RequestParam(defaultValue = "12") @Positive int size) {

        Long memberNo = 1L;
        Pageable pageable = PageRequest.of(page, size);

        Page<GetPostsResponse> response = postService.getPostsScroll(pageable, memberNo);

        return ResponseEntity.ok(response);
    }
}
