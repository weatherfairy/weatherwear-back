package com.weatherfairy.weatherwearback.common.locations;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocation is a Querydsl query type for Location
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation extends EntityPathBase<Location> {

    private static final long serialVersionUID = -214187788L;

    public static final QLocation location = new QLocation("location");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath locationName = createString("locationName");

    public final NumberPath<Integer> locationX = createNumber("locationX", Integer.class);

    public final NumberPath<Integer> locationY = createNumber("locationY", Integer.class);

    public final StringPath longitude = createString("longitude");

    public QLocation(String variable) {
        super(Location.class, forVariable(variable));
    }

    public QLocation(Path<? extends Location> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation(PathMetadata metadata) {
        super(Location.class, metadata);
    }

}

