package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParametre is a Querydsl query type for Parametre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParametre extends EntityPathBase<Parametre> {

    private static final long serialVersionUID = 111516648L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParametre parametre = new QParametre("parametre");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeParametre = createString("codeParametre");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idParametre = createNumber("idParametre", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath parametres = createString("parametres");

    public final QService service;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QParametre(String variable) {
        this(Parametre.class, forVariable(variable), INITS);
    }

    public QParametre(Path<? extends Parametre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParametre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParametre(PathMetadata metadata, PathInits inits) {
        this(Parametre.class, metadata, inits);
    }

    public QParametre(Class<? extends Parametre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.service = inits.isInitialized("service") ? new QService(forProperty("service"), inits.get("service")) : null;
    }

}

