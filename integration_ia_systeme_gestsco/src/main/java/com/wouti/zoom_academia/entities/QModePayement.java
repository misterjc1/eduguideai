package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QModePayement is a Querydsl query type for ModePayement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QModePayement extends EntityPathBase<ModePayement> {

    private static final long serialVersionUID = -196112123L;

    public static final QModePayement modePayement = new QModePayement("modePayement");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeModePayement = createString("codeModePayement");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idModePayement = createNumber("idModePayement", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    public final StringPath lien = createString("lien");

    public final StringPath logo = createString("logo");

    public final StringPath numeroCompte = createString("numeroCompte");

    public final StringPath parametres = createString("parametres");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath syntaxe = createString("syntaxe");

    public final EnumPath<com.wouti.zoom_academia.transverse.TypeModePaiement> type = createEnum("type", com.wouti.zoom_academia.transverse.TypeModePaiement.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QModePayement(String variable) {
        super(ModePayement.class, forVariable(variable));
    }

    public QModePayement(Path<? extends ModePayement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModePayement(PathMetadata metadata) {
        super(ModePayement.class, metadata);
    }

}

