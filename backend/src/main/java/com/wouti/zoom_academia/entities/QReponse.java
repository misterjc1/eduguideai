package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReponse is a Querydsl query type for Reponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReponse extends EntityPathBase<Reponse> {

    private static final long serialVersionUID = -2093907065L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReponse reponse = new QReponse("reponse");

    public final QAuditModel _super = new QAuditModel(this);

    public final QCinematique cinematique;

    public final StringPath codeResp = createString("codeResp");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idResp = createNumber("idResp", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath message = createString("message");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QReponse(String variable) {
        this(Reponse.class, forVariable(variable), INITS);
    }

    public QReponse(Path<? extends Reponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReponse(PathMetadata metadata, PathInits inits) {
        this(Reponse.class, metadata, inits);
    }

    public QReponse(Class<? extends Reponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cinematique = inits.isInitialized("cinematique") ? new QCinematique(forProperty("cinematique"), inits.get("cinematique")) : null;
    }

}

