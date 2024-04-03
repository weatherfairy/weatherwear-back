package com.weatherfairy.weatherwearback.post.dto.request;

import com.weatherfairy.weatherwearback.common.enums.Emoji;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;

public record CreatePostRequest(
        float minTemp,
        float maxTemp,
        String clothesText,
        String review,
        Emoji emoji,
        SkyCategory sky

) {

}
