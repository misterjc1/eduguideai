package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInscrit is a Querydsl query type for Inscrit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInscrit extends EntityPathBase<Inscrit> {

    private static final long serialVersionUID = -1231426623L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInscrit inscrit = new QInscrit("inscrit");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeInscrit = createString("codeInscrit");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath email = createString("email");

    public final NumberPath<Long> idInscrit = createNumber("idInscrit", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath matricule = createString("matricule");

    public final QNiveau niveau;

    public final StringPath nom = createString("nom");

    public final StringPath prenom = createString("prenom");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath telephone = createString("telephone");

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QInscrit(String variable) {
        this(Inscrit.class, forVariable(variable), INITS);
    }

    public QInscrit(Path<? extends Inscrit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInscrit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInscrit(PathMetadata metadata, PathInits inits) {
        this(Inscrit.class, metadata, inits);
    }

    public QInscrit(Class<? extends Inscrit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.niveau = inits.isInitialized("niveau") ? new QNiveau(forProperty("niveau")) : null;
    }

}

