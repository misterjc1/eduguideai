package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeCinematique is a Querydsl query type for TypeCinematique
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeCinematique extends EntityPathBase<TypeCinematique> {

    private static final long serialVersionUID = -632898502L;

    public static final QTypeCinematique typeCinematique = new QTypeCinematique("typeCinematique");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath choixMultiple = createString("choixMultiple");

    public final StringPath codeTypeCinematique = createString("codeTypeCinematique");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idTypeCinematique = createNumber("idTypeCinematique", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final EnumPath<com.wouti.zoom_academia.transverse.Niveau> niveau = createEnum("niveau", com.wouti.zoom_academia.transverse.Niveau.class);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath typeBouton = createString("typeBouton");

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QTypeCinematique(String variable) {
        super(TypeCinematique.class, forVariable(variable));
    }

    public QTypeCinematique(Path<? extends TypeCinematique> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeCinematique(PathMetadata metadata) {
        super(TypeCinematique.class, metadata);
    }

}

