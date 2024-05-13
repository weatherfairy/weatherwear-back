package com.weatherfairy.weatherwearback.post.dto.request;

import java.util.List;

public record PostFilterCriteria(
         List<Integer> month,
         Integer min,
         Integer max,
         List<Integer> emoji,
         List<Integer> sky
) {

}

