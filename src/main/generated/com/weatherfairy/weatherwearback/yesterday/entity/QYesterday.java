package com.weatherfairy.weatherwearback.yesterday.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QYesterday is a Querydsl query type for Yesterday
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QYesterday extends EntityPathBase<Yesterday> {

    private static final long serialVersionUID = 1857764823L;

    public static final QYesterday yesterday = new QYesterday("yesterday");

    public final ListPath<Float, NumberPath<Float>> feelsLike = this.<Float, NumberPath<Float>>createList("feelsLike", Float.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath locationName = createString("locationName");

    public final NumberPath<Float> maxTemp = createNumber("maxTemp", Float.class);

    public final NumberPath<Float> minTemp = createNumber("minTemp", Float.class);

    public final ListPath<String, StringPath> rain = this.<String, StringPath>createList("rain", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Integer, NumberPath<Integer>> skyCategory = this.<Integer, NumberPath<Integer>>createList("skyCategory", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final ListPath<Float, NumberPath<Float>> temperature = this.<Float, NumberPath<Float>>createList("temperature", Float.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> yesterdayId = createNumber("yesterdayId", Integer.class);

    public QYesterday(String variable) {
        super(Yesterday.class, forVariable(variable));
    }

    public QYesterday(Path<? extends Yesterday> path) {
        super(path.getType(), path.getMetadata());
    }

    public QYesterday(PathMetadata metadata) {
        super(Yesterday.class, metadata);
    }

}

