package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeSouscription is a Querydsl query type for TypeSouscription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeSouscription extends EntityPathBase<TypeSouscription> {

    private static final long serialVersionUID = -1830538907L;

    public static final QTypeSouscription typeSouscription = new QTypeSouscription("typeSouscription");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeTypeSouscription = createString("codeTypeSouscription");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idTypeSouscription = createNumber("idTypeSouscription", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QTypeSouscription(String variable) {
        super(TypeSouscription.class, forVariable(variable));
    }

    public QTypeSouscription(Path<? extends TypeSouscription> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeSouscription(PathMetadata metadata) {
        super(TypeSouscription.class, metadata);
    }

}

