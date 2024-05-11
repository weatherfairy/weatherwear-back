package com.weatherfairy.weatherwearback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1472557147L;

    public static final QMember member = new QMember("member1");

    public final StringPath email = createString("email");

    public final EnumPath<com.weatherfairy.weatherwearback.common.enums.Gender> gender = createEnum("gender", com.weatherfairy.weatherwearback.common.enums.Gender.class);

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final StringPath password = createString("password");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

