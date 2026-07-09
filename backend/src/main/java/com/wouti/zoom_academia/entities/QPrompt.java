package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrompt is a Querydsl query type for Prompt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrompt extends EntityPathBase<Prompt> {

    private static final long serialVersionUID = -1775397557L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrompt prompt = new QPrompt("prompt");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codePrompt = createString("codePrompt");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DateTimePath<java.util.Date> dateCreation = createDateTime("dateCreation", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idPrompt = createNumber("idPrompt", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final ListPath<LignePrompt, QLignePrompt> lignePrompt = this.<LignePrompt, QLignePrompt>createList("lignePrompt", LignePrompt.class, QLignePrompt.class, PathInits.DIRECT2);

    public final StringPath message = createString("message");

    public final ListPath<Notification, QNotification> notification = this.<Notification, QNotification>createList("notification", Notification.class, QNotification.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final QTemplatePrompt templatePrompt;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final QUtilisateur utilisateur;

    public QPrompt(String variable) {
        this(Prompt.class, forVariable(variable), INITS);
    }

    public QPrompt(Path<? extends Prompt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrompt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrompt(PathMetadata metadata, PathInits inits) {
        this(Prompt.class, metadata, inits);
    }

    public QPrompt(Class<? extends Prompt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.templatePrompt = inits.isInitialized("templatePrompt") ? new QTemplatePrompt(forProperty("templatePrompt"), inits.get("templatePrompt")) : null;
        this.utilisateur = inits.isInitialized("utilisateur") ? new QUtilisateur(forProperty("utilisateur")) : null;
    }

}

