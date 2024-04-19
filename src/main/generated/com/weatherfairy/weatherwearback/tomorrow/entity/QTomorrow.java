package com.weatherfairy.weatherwearback.tomorrow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTomorrow is a Querydsl query type for Tomorrow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTomorrow extends EntityPathBase<Tomorrow> {

    private static final long serialVersionUID = 1613072059L;

    public static final QTomorrow tomorrow = new QTomorrow("tomorrow");

    public final ListPath<Float, NumberPath<Float>> feelsLike = this.<Float, NumberPath<Float>>createList("feelsLike", Float.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath locationName = createString("locationName");

    public final NumberPath<Float> maxTemp = createNumber("maxTemp", Float.class);

    public final NumberPath<Float> minTemp = createNumber("minTemp", Float.class);

    public final ListPath<String, StringPath> rain = this.<String, StringPath>createList("rain", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Integer, NumberPath<Integer>> skyCategory = this.<Integer, NumberPath<Integer>>createList("skyCategory", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final ListPath<Float, NumberPath<Float>> temperature = this.<Float, NumberPath<Float>>createList("temperature", Float.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> tomorrowId = createNumber("tomorrowId", Integer.class);

    public QTomorrow(String variable) {
        super(Tomorrow.class, forVariable(variable));
    }

    public QTomorrow(Path<? extends Tomorrow> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTomorrow(PathMetadata metadata) {
        super(Tomorrow.class, metadata);
    }

}

