package com.weatherfairy.weatherwearback.post.dto.response;

import com.weatherfairy.weatherwearback.post.entity.Post;

import java.time.LocalDate;

public record GetRecommendResponse(
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
    public static GetRecommendResponse from(Post post) {
        return new GetRecommendResponse(post.getPostId(), post.getImage1(), post.getImage2(), post.getImage3(),post.getDate(), post.getMinTemp(), post.getMaxTemp(), post.getClothes(), post.getReview(),
                post.getEmoji().getValue(), post.getSky().getValue());
    }
}
