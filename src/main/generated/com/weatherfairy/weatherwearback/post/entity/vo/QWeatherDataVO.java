package com.weatherfairy.weatherwearback.post.entity.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeatherDataVO is a Querydsl query type for WeatherDataVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QWeatherDataVO extends BeanPath<WeatherDataVO> {

    private static final long serialVersionUID = -1499674989L;

    public static final QWeatherDataVO weatherDataVO = new QWeatherDataVO("weatherDataVO");

    public final NumberPath<Float> maxTemp = createNumber("maxTemp", Float.class);

    public final NumberPath<Float> minTemp = createNumber("minTemp", Float.class);

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.SkyCategory> sky = createEnum("sky", com.weatherfairy.weatherwearback.common.enums.SkyCategory.class);

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.TempCategory> tempCategory = createEnum("tempCategory", com.weatherfairy.weatherwearback.common.enums.TempCategory.class);

    public QWeatherDataVO(String variable) {
        super(WeatherDataVO.class, forVariable(variable));
    }

    public QWeatherDataVO(Path<? extends WeatherDataVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeatherDataVO(PathMetadata metadata) {
        super(WeatherDataVO.class, metadata);
    }

}

