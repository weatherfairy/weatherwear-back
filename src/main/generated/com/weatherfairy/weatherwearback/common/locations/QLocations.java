package com.weatherfairy.weatherwearback.common.locations;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocations is a Querydsl query type for Locations
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocations extends EntityPathBase<Locations> {

    private static final long serialVersionUID = 1950113279L;

    public static final QLocations locations = new QLocations("locations");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath locationName = createString("locationName");

    public final NumberPath<Integer> locationX = createNumber("locationX", Integer.class);

    public final NumberPath<Integer> locationY = createNumber("locationY", Integer.class);

    public QLocations(String variable) {
        super(Locations.class, forVariable(variable));
    }

    public QLocations(Path<? extends Locations> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocations(PathMetadata metadata) {
        super(Locations.class, metadata);
    }

}

