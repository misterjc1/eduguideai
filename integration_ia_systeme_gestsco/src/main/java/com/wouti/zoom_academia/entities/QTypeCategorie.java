package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeCategorie is a Querydsl query type for TypeCategorie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeCategorie extends EntityPathBase<TypeCategorie> {

    private static final long serialVersionUID = -761801386L;

    public static final QTypeCategorie typeCategorie = new QTypeCategorie("typeCategorie");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeTypeCategorie = createString("codeTypeCategorie");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final StringPath defaultCodeTypeSuscription = createString("defaultCodeTypeSuscription");

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath description = createString("description");

    public final NumberPath<Long> idTypeCategorie = createNumber("idTypeCategorie", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QTypeCategorie(String variable) {
        super(TypeCategorie.class, forVariable(variable));
    }

    public QTypeCategorie(Path<? extends TypeCategorie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeCategorie(PathMetadata metadata) {
        super(TypeCategorie.class, metadata);
    }

}

