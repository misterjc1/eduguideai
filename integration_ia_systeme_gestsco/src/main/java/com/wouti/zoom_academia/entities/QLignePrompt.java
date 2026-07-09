package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLignePrompt is a Querydsl query type for LignePrompt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLignePrompt extends EntityPathBase<LignePrompt> {

    private static final long serialVersionUID = 1589674750L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLignePrompt lignePrompt = new QLignePrompt("lignePrompt");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeLignePrompt = createString("codeLignePrompt");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idLignePrompt = createNumber("idLignePrompt", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final EnumPath<com.wouti.zoom_academia.transverse.LignePromptEnum> lignePromptEnum = createEnum("lignePromptEnum", com.wouti.zoom_academia.transverse.LignePromptEnum.class);

    public final QPrompt prompt;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QLignePrompt(String variable) {
        this(LignePrompt.class, forVariable(variable), INITS);
    }

    public QLignePrompt(Path<? extends LignePrompt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLignePrompt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLignePrompt(PathMetadata metadata, PathInits inits) {
        this(LignePrompt.class, metadata, inits);
    }

    public QLignePrompt(Class<? extends LignePrompt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prompt = inits.isInitialized("prompt") ? new QPrompt(forProperty("prompt"), inits.get("prompt")) : null;
    }

}

