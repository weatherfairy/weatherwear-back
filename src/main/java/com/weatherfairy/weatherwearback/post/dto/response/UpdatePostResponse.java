package com.weatherfairy.weatherwearback.post.dto.response;

import com.weatherfairy.weatherwearback.post.entity.Post;

public record UpdatePostResponse(
        Long postNo
) {
    public static UpdatePostResponse from(Post post) {
        return new UpdatePostResponse(
                post.getPostId());
    }
}
