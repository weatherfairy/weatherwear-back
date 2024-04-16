package com.weatherfairy.weatherwearback.today.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToday is a Querydsl query type for Today
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToday extends EntityPathBase<Today> {

    private static final long serialVersionUID = -789788489L;

    public static final QToday today = new QToday("today");

    public final ListPath<Float, NumberPath<Float>> feelsLike = this.<Float, NumberPath<Float>>createList("feelsLike", Float.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath locationName = createString("locationName");

    public final NumberPath<Float> maxTemp = createNumber("maxTemp", Float.class);

    public final NumberPath<Float> minTemp = createNumber("minTemp", Float.class);

    public final ListPath<String, StringPath> rain = this.<String, StringPath>createList("rain", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Integer, NumberPath<Integer>> skyCategory = this.<Integer, NumberPath<Integer>>createList("skyCategory", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final ListPath<Float, NumberPath<Float>> temperature = this.<Float, NumberPath<Float>>createList("temperature", Float.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> todayId = createNumber("todayId", Integer.class);

    public QToday(String variable) {
        super(Today.class, forVariable(variable));
    }

    public QToday(Path<? extends Today> path) {
        super(path.getType(), path.getMetadata());
    }

    public QToday(PathMetadata metadata) {
        super(Today.class, metadata);
    }

}

