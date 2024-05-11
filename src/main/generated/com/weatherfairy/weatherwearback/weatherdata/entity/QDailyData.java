package com.weatherfairy.weatherwearback.weatherdata.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDailyData is a Querydsl query type for DailyData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDailyData extends EntityPathBase<DailyData> {

    private static final long serialVersionUID = 1549685788L;

    public static final QDailyData dailyData = new QDailyData("dailyData");

    public final StringPath locationName = createString("locationName");

    public final ListPath<String, StringPath> rain = this.<String, StringPath>createList("rain", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Integer, NumberPath<Integer>> skyCategory = this.<Integer, NumberPath<Integer>>createList("skyCategory", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> temperature = this.<String, StringPath>createList("temperature", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> todayId = createNumber("todayId", Integer.class);

    public final ListPath<String, StringPath> windSpeed = this.<String, StringPath>createList("windSpeed", String.class, StringPath.class, PathInits.DIRECT2);

    public QDailyData(String variable) {
        super(DailyData.class, forVariable(variable));
    }

    public QDailyData(Path<? extends DailyData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyData(PathMetadata metadata) {
        super(DailyData.class, metadata);
    }

}

