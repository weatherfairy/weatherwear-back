package com.weatherfairy.weatherwearback.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1023017359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final StringPath clothes = createString("clothes");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.Emoji> emoji = createEnum("emoji", com.weatherfairy.weatherwearback.common.enums.Emoji.class);

    public final StringPath image1 = createString("image1");

    public final StringPath image2 = createString("image2");

    public final StringPath image3 = createString("image3");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath review = createString("review");

    public final com.weatherfairy.weatherwearback.post.entity.vo.QWeatherDataVO weatherDataVO;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.weatherDataVO = inits.isInitialized("weatherDataVO") ? new com.weatherfairy.weatherwearback.post.entity.vo.QWeatherDataVO(forProperty("weatherDataVO")) : null;
    }

}

