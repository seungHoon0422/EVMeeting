package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCallMeeting is a Querydsl query type for CallMeeting
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCallMeeting extends EntityPathBase<CallMeeting> {

    private static final long serialVersionUID = 1528830203L;

    public static final QCallMeeting callMeeting = new QCallMeeting("callMeeting");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath sessionid = createString("sessionid");

    public QCallMeeting(String variable) {
        super(CallMeeting.class, forVariable(variable));
    }

    public QCallMeeting(Path<? extends CallMeeting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCallMeeting(PathMetadata metadata) {
        super(CallMeeting.class, metadata);
    }

}

