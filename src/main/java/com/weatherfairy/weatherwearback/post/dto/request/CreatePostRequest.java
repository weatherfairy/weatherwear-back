package com.weatherfairy.weatherwearback.post.dto.request;


public record CreatePostRequest(
        float min,
        float max,
        String clothes,
        String review,
        Integer emoji,
        Integer sky

) {

}
