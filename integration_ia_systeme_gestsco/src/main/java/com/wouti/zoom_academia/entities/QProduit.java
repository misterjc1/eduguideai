package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduit is a Querydsl query type for Produit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduit extends EntityPathBase<Produit> {

    private static final long serialVersionUID = 796987042L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduit produit = new QProduit("produit");

    public final QAuditModel _super = new QAuditModel(this);

    public final QCategorie categorie;

    public final StringPath codeProduit = createString("codeProduit");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath description = createString("description");

    public final NumberPath<Long> idProduit = createNumber("idProduit", Long.class);

    public final StringPath Image = createString("Image");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath nom = createString("nom");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QProduit(String variable) {
        this(Produit.class, forVariable(variable), INITS);
    }

    public QProduit(Path<? extends Produit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduit(PathMetadata metadata, PathInits inits) {
        this(Produit.class, metadata, inits);
    }

    public QProduit(Class<? extends Produit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categorie = inits.isInitialized("categorie") ? new QCategorie(forProperty("categorie"), inits.get("categorie")) : null;
    }

}

