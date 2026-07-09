package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCinematique is a Querydsl query type for Cinematique
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCinematique extends EntityPathBase<Cinematique> {

    private static final long serialVersionUID = 1308127316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCinematique cinematique = new QCinematique("cinematique");

    public final QAuditModel _super = new QAuditModel(this);

    public final QCinematique cinematiqueMere;

    public final StringPath codeCinematique = createString("codeCinematique");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idCinematique = createNumber("idCinematique", Long.class);

    public final StringPath image = createString("image");

    public final StringPath intitule = createString("intitule");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final QTypeCinematique typeCinematique;

    public final QService typeService;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QCinematique(String variable) {
        this(Cinematique.class, forVariable(variable), INITS);
    }

    public QCinematique(Path<? extends Cinematique> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCinematique(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCinematique(PathMetadata metadata, PathInits inits) {
        this(Cinematique.class, metadata, inits);
    }

    public QCinematique(Class<? extends Cinematique> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cinematiqueMere = inits.isInitialized("cinematiqueMere") ? new QCinematique(forProperty("cinematiqueMere"), inits.get("cinematiqueMere")) : null;
        this.typeCinematique = inits.isInitialized("typeCinematique") ? new QTypeCinematique(forProperty("typeCinematique")) : null;
        this.typeService = inits.isInitialized("typeService") ? new QService(forProperty("typeService"), inits.get("typeService")) : null;
    }

}

