package com.weatherfairy.weatherwearback.post.dto.response;

import com.weatherfairy.weatherwearback.post.entity.Post;

public record GetPostsResponse(
        Long postNo,
        String imageUrl
) {
    public static GetPostsResponse from(Post post) {
        return new GetPostsResponse(post.getPostId(), post.getImage1());
    }
}
