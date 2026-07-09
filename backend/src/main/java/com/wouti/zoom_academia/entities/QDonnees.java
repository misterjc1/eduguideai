package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDonnees is a Querydsl query type for Donnees
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDonnees extends EntityPathBase<Donnees> {

    private static final long serialVersionUID = -1349651103L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDonnees donnees = new QDonnees("donnees");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeDonnee = createString("codeDonnee");

    public final QParametre codeParametre;

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath fichierJoint = createString("fichierJoint");

    public final NumberPath<Long> idDonnee = createNumber("idDonnee", Long.class);

    public final StringPath intituleD = createString("intituleD");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final QUtilisateur utilisateur;

    public final StringPath valeurD = createString("valeurD");

    public QDonnees(String variable) {
        this(Donnees.class, forVariable(variable), INITS);
    }

    public QDonnees(Path<? extends Donnees> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDonnees(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDonnees(PathMetadata metadata, PathInits inits) {
        this(Donnees.class, metadata, inits);
    }

    public QDonnees(Class<? extends Donnees> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.codeParametre = inits.isInitialized("codeParametre") ? new QParametre(forProperty("codeParametre"), inits.get("codeParametre")) : null;
        this.utilisateur = inits.isInitialized("utilisateur") ? new QUtilisateur(forProperty("utilisateur")) : null;
    }

}

