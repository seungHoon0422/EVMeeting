package com.ssafy.user.db.entitiy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -440323897L;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath birth = createString("birth");

    public final StringPath cigarette = createString("cigarette");

    public final StringPath description = createString("description");

    public final StringPath drink = createString("drink");

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final StringPath height = createString("height");

    public final StringPath hobby = createString("hobby");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mbti = createString("mbti");

    public final StringPath password = createString("password");

    public final StringPath school = createString("school");

    public final StringPath userid = createString("userid");

    public final StringPath username = createString("username");

    public final StringPath weight = createString("weight");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

