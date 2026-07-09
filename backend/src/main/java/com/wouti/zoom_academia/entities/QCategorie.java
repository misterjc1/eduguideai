package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategorie is a Querydsl query type for Categorie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategorie extends EntityPathBase<Categorie> {

    private static final long serialVersionUID = -277100560L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategorie categorie = new QCategorie("categorie");

    public final QAuditModel _super = new QAuditModel(this);

    public final QCategorie categorieMere;

    public final StringPath codeCategorie = createString("codeCategorie");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath description = createString("description");

    public final NumberPath<Long> idCategorie = createNumber("idCategorie", Long.class);

    public final StringPath image = createString("image");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final QTypeCategorie typeCategorie;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QCategorie(String variable) {
        this(Categorie.class, forVariable(variable), INITS);
    }

    public QCategorie(Path<? extends Categorie> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategorie(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategorie(PathMetadata metadata, PathInits inits) {
        this(Categorie.class, metadata, inits);
    }

    public QCategorie(Class<? extends Categorie> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categorieMere = inits.isInitialized("categorieMere") ? new QCategorie(forProperty("categorieMere"), inits.get("categorieMere")) : null;
        this.typeCategorie = inits.isInitialized("typeCategorie") ? new QTypeCategorie(forProperty("typeCategorie")) : null;
    }

}

