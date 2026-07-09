package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonne is a Querydsl query type for Personne
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonne extends EntityPathBase<Personne> {

    private static final long serialVersionUID = 383714803L;

    public static final QPersonne personne = new QPersonne("personne");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codePersonne = createString("codePersonne");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DateTimePath<java.util.Date> dateNaissance = createDateTime("dateNaissance", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath email = createString("email");

    public final NumberPath<Long> idPersonne = createNumber("idPersonne", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath lieuNaissance = createString("lieuNaissance");

    public final StringPath matricule = createString("matricule");

    public final StringPath nom = createString("nom");

    public final StringPath prenom = createString("prenom");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath telephone1 = createString("telephone1");

    public final StringPath telephone2 = createString("telephone2");

    public final EnumPath<com.wouti.zoom_academia.transverse.TypePersonne> typePersonne = createEnum("typePersonne", com.wouti.zoom_academia.transverse.TypePersonne.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QPersonne(String variable) {
        super(Personne.class, forVariable(variable));
    }

    public QPersonne(Path<? extends Personne> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonne(PathMetadata metadata) {
        super(Personne.class, metadata);
    }

}

