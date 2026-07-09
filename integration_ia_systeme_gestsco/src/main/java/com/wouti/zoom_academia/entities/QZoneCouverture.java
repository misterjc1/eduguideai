package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QZoneCouverture is a Querydsl query type for ZoneCouverture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZoneCouverture extends EntityPathBase<ZoneCouverture> {

    private static final long serialVersionUID = 1858002593L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QZoneCouverture zoneCouverture = new QZoneCouverture("zoneCouverture");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeZoneCouverture = createString("codeZoneCouverture");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final QZoneCouverture father;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intitule = createString("intitule");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath key = createString("key");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QZoneCouverture(String variable) {
        this(ZoneCouverture.class, forVariable(variable), INITS);
    }

    public QZoneCouverture(Path<? extends ZoneCouverture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QZoneCouverture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QZoneCouverture(PathMetadata metadata, PathInits inits) {
        this(ZoneCouverture.class, metadata, inits);
    }

    public QZoneCouverture(Class<? extends ZoneCouverture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.father = inits.isInitialized("father") ? new QZoneCouverture(forProperty("father"), inits.get("father")) : null;
    }

}

