package com.weatherfairy.weatherwearback.weatherdata.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWeeklyData is a Querydsl query type for WeeklyData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeeklyData extends EntityPathBase<WeeklyData> {

    private static final long serialVersionUID = -1170944974L;

    public static final QWeeklyData weeklyData = new QWeeklyData("weeklyData");

    public final ListPath<String, StringPath> dayRain = this.<String, StringPath>createList("dayRain", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Integer, NumberPath<Integer>> daySky = this.<Integer, NumberPath<Integer>>createList("daySky", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath locationName = createString("locationName");

    public final ListPath<String, StringPath> maxTemp = this.<String, StringPath>createList("maxTemp", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> minTemp = this.<String, StringPath>createList("minTemp", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> nightRain = this.<String, StringPath>createList("nightRain", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<Integer, NumberPath<Integer>> nightSky = this.<Integer, NumberPath<Integer>>createList("nightSky", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> weeklyId = createNumber("weeklyId", Integer.class);

    public QWeeklyData(String variable) {
        super(WeeklyData.class, forVariable(variable));
    }

    public QWeeklyData(Path<? extends WeeklyData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeeklyData(PathMetadata metadata) {
        super(WeeklyData.class, metadata);
    }

}

