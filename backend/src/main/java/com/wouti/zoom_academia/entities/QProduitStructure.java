package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduitStructure is a Querydsl query type for ProduitStructure
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduitStructure extends EntityPathBase<ProduitStructure> {

    private static final long serialVersionUID = -866675471L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduitStructure produitStructure = new QProduitStructure("produitStructure");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeProduitStructure = createString("codeProduitStructure");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath details = createString("details");

    public final BooleanPath estDisponible = createBoolean("estDisponible");

    public final NumberPath<Long> idProduitStructure = createNumber("idProduitStructure", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final NumberPath<Integer> nombreTranche = createNumber("nombreTranche", Integer.class);

    public final NumberPath<Double> prixFournisseur = createNumber("prixFournisseur", Double.class);

    public final NumberPath<Double> prixPropose = createNumber("prixPropose", Double.class);

    public final QProduit produit;

    public final QStructure structure;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QProduitStructure(String variable) {
        this(ProduitStructure.class, forVariable(variable), INITS);
    }

    public QProduitStructure(Path<? extends ProduitStructure> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduitStructure(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduitStructure(PathMetadata metadata, PathInits inits) {
        this(ProduitStructure.class, metadata, inits);
    }

    public QProduitStructure(Class<? extends ProduitStructure> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.produit = inits.isInitialized("produit") ? new QProduit(forProperty("produit"), inits.get("produit")) : null;
        this.structure = inits.isInitialized("structure") ? new QStructure(forProperty("structure")) : null;
    }

}

