package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import com.wouti.zoom_academia.transverse.StatutLiaison;


/**
 * QLiaison is a Querydsl query type for Liaison
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiaison extends EntityPathBase<Liaison> {

    private static final long serialVersionUID = 1271495174L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiaison liaison = new QLiaison("liaison");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeLiaison = createString("codeLiaison");

    public final StringPath commentaire = createString("commentaire");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idLiaison = createNumber("idLiaison", Long.class);

    public final QInscrit inscrit;

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath motif = createString("motif");

    public final EnumPath<StatutLiaison> statutLiaison = createEnum("statutLiaison", StatutLiaison.class);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final QUtilisateur utilisateur;

    public QLiaison(String variable) {
        this(Liaison.class, forVariable(variable), INITS);
    }

    public QLiaison(Path<? extends Liaison> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiaison(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiaison(PathMetadata metadata, PathInits inits) {
        this(Liaison.class, metadata, inits);
    }

    public QLiaison(Class<? extends Liaison> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inscrit = inits.isInitialized("inscrit") ? new QInscrit(forProperty("inscrit"), inits.get("inscrit")) : null;
        this.utilisateur = inits.isInitialized("utilisateur") ? new QUtilisateur(forProperty("utilisateur")) : null;
    }

}

