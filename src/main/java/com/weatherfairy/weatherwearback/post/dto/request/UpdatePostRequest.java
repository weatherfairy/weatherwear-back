package com.weatherfairy.weatherwearback.post.dto.request;


public record UpdatePostRequest(
        String clothes,
        String review,
        Integer emoji

) {

}
