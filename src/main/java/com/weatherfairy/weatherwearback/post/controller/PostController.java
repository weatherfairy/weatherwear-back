package com.weatherfairy.weatherwearback.post.controller;

import com.weatherfairy.weatherwearback.post.dto.request.CreatePostRequest;
import com.weatherfairy.weatherwearback.post.dto.request.PostFilterCriteria;
import com.weatherfairy.weatherwearback.post.dto.response.CreatePostResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.dto.response.GetPostResponse;
import com.weatherfairy.weatherwearback.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/closet/lists")
    public ResponseEntity<List<GetPostsResponse>> getAllPosts(@RequestHeader("Authorization")String token,
                                                              @ModelAttribute PostFilterCriteria criteria) {

        List<GetPostsResponse> response = postService.getPostsByFilter(token, criteria);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/api/v1/closet/lists/{postNo}")
    public ResponseEntity<GetPostResponse> getPost(@RequestHeader("Authorization")String token,
                                                   @PathVariable Long postNo) {

        GetPostResponse response = postService.getPost(postNo);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/closet/recommend")
    public ResponseEntity<List<GetPostResponse>> getRecommendedPosts(@RequestHeader("Authorization")String token,
                                                                     @RequestParam("location") String locationName) {
        List<GetPostResponse> recommendedPosts = postService.getRecommendedPosts(token, locationName);

        return ResponseEntity.ok(recommendedPosts);
    }

    @PostMapping("/api/v1/closet")
    public ResponseEntity<CreatePostResponse> createPost(
            @RequestHeader("Authorization")String token,
            @RequestPart(value = "image1", required = false) MultipartFile image1,
            @RequestPart(value = "image2", required = false) MultipartFile image2,
            @RequestPart(value = "image3", required = false) MultipartFile image3,
            @RequestPart("min") String minTemp,
            @RequestPart("max") String maxTemp,
            @RequestPart("clothes") String clothesText,
            @RequestPart("review") String review,
            @RequestPart("emoji") String emoji,
            @RequestPart("sky") String sky
    ) throws IOException {

        CreatePostRequest request = new CreatePostRequest( Float.parseFloat(minTemp), Float.parseFloat(maxTemp),
                clothesText, review, Integer.parseInt(emoji), Integer.parseInt(sky));

        CreatePostResponse response = postService.createPost(token, image1,image2,image3, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/v1/closet/{postNo}")
    public ResponseEntity<String> deletePost(@RequestHeader("Authorization")String token,
                                             @PathVariable("postNo") Long postNo) {

        Boolean response = postService.deletePost(postNo);

        if (Boolean.TRUE.equals(response)) return ResponseEntity.ok("SUCCESS");
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR");
        }

    }


}
