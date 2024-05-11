package com.weatherfairy.weatherwearback.post.dto.response;

import com.weatherfairy.weatherwearback.post.entity.Post;

import java.time.LocalDate;

public record GetPostsResponse(
        Long postNo,
        LocalDate date,
        String imageUrl
) {
    public static GetPostsResponse from(Post post) {
        return new GetPostsResponse(post.getPostId(), post.getDate(), post.getImage1());
    }
}
