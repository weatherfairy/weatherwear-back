package com.weatherfairy.weatherwearback.post.dto.response;

import com.weatherfairy.weatherwearback.post.entity.Post;

import java.time.LocalDate;

public record GetPostResponse(
        Long postNo,
        String image1,
        String image2,
        String image3,
        LocalDate postDate,
        float minTemp,
        float maxTemp,
        String clothesText,
        String review,
        Integer emoji,
        Integer sky

) {
    public static GetPostResponse from(Post post) {
        return new GetPostResponse(post.getPostId(), post.getImage1(), post.getImage2(), post.getImage3(),post.getDate(),
                post.getWeatherDataVO().getMinTemp(), post.getWeatherDataVO().getMaxTemp(), post.getClothes(), post.getReview(),
                post.getEmoji().getValue(), post.getWeatherDataVO().getSky().getValue());
    }
}
