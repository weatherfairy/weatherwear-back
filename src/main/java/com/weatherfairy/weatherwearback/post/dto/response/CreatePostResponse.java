package com.weatherfairy.weatherwearback.post.dto.response;

import com.weatherfairy.weatherwearback.post.dto.request.CreatePostRequest;
import com.weatherfairy.weatherwearback.post.entity.Post;

public record CreatePostResponse(
        Long postNo
) {
    public static CreatePostResponse from(Post post) {
        return new CreatePostResponse(
                post.getPostId());
    }
}
