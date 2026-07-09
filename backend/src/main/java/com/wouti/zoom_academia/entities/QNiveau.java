package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNiveau is a Querydsl query type for Niveau
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNiveau extends EntityPathBase<Niveau> {

    private static final long serialVersionUID = -1840767163L;

    public static final QNiveau niveau = new QNiveau("niveau");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeNiveau = createString("codeNiveau");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath filiere = createString("filiere");

    public final NumberPath<Long> idNiveau = createNumber("idNiveau", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QNiveau(String variable) {
        super(Niveau.class, forVariable(variable));
    }

    public QNiveau(Path<? extends Niveau> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNiveau(PathMetadata metadata) {
        super(Niveau.class, metadata);
    }

}

