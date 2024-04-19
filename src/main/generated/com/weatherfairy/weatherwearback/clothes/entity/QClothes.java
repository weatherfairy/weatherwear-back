package com.weatherfairy.weatherwearback.clothes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothes is a Querydsl query type for Clothes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothes extends EntityPathBase<Clothes> {

    private static final long serialVersionUID = 151637847L;

    public static final QClothes clothes = new QClothes("clothes");

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.ClothesCategory> clothesCategory = createEnum("clothesCategory", com.weatherfairy.weatherwearback.common.enums.ClothesCategory.class);

    public final NumberPath<Long> clothesId = createNumber("clothesId", Long.class);

    public final StringPath clothesName = createString("clothesName");

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.SkyCategory> skyCategory = createEnum("skyCategory", com.weatherfairy.weatherwearback.common.enums.SkyCategory.class);

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.TempCategory> tempCategory = createEnum("tempCategory", com.weatherfairy.weatherwearback.common.enums.TempCategory.class);

    public QClothes(String variable) {
        super(Clothes.class, forVariable(variable));
    }

    public QClothes(Path<? extends Clothes> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothes(PathMetadata metadata) {
        super(Clothes.class, metadata);
    }

}

