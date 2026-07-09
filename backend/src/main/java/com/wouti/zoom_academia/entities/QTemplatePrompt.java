package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplatePrompt is a Querydsl query type for TemplatePrompt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplatePrompt extends EntityPathBase<TemplatePrompt> {

    private static final long serialVersionUID = 630305637L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplatePrompt templatePrompt = new QTemplatePrompt("templatePrompt");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeTemplatePrompt = createString("codeTemplatePrompt");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath description = createString("description");

    public final NumberPath<Long> idTemplatePrompt = createNumber("idTemplatePrompt", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final ListPath<Prompt, QPrompt> prompts = this.<Prompt, QPrompt>createList("prompts", Prompt.class, QPrompt.class, PathInits.DIRECT2);

    public final QService service;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final StringPath variables = createString("variables");

    public QTemplatePrompt(String variable) {
        this(TemplatePrompt.class, forVariable(variable), INITS);
    }

    public QTemplatePrompt(Path<? extends TemplatePrompt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplatePrompt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplatePrompt(PathMetadata metadata, PathInits inits) {
        this(TemplatePrompt.class, metadata, inits);
    }

    public QTemplatePrompt(Class<? extends TemplatePrompt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.service = inits.isInitialized("service") ? new QService(forProperty("service"), inits.get("service")) : null;
    }

}

