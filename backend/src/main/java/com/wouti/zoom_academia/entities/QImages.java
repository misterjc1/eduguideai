package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImages is a Querydsl query type for Images
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImages extends EntityPathBase<Images> {

    private static final long serialVersionUID = -1980842401L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImages images = new QImages("images");

    public final NumberPath<Long> idImage = createNumber("idImage", Long.class);

    public final StringPath intitule = createString("intitule");

    public final StringPath path = createString("path");

    public final QProduit produit;

    public QImages(String variable) {
        this(Images.class, forVariable(variable), INITS);
    }

    public QImages(Path<? extends Images> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImages(PathMetadata metadata, PathInits inits) {
        this(Images.class, metadata, inits);
    }

    public QImages(Class<? extends Images> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.produit = inits.isInitialized("produit") ? new QProduit(forProperty("produit"), inits.get("produit")) : null;
    }

}

