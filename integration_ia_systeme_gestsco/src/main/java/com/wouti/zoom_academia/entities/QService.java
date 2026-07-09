package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QService is a Querydsl query type for Service
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QService extends EntityPathBase<Service> {

    private static final long serialVersionUID = -1204353106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QService service = new QService("service");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeService = createString("codeService");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idService = createNumber("idService", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath logo = createString("logo");

    public final StringPath message = createString("message");

    public final ListPath<Parametre, QParametre> parametre = this.<Parametre, QParametre>createList("parametre", Parametre.class, QParametre.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final QTemplatePrompt templatePrompt;

    public final EnumPath<com.wouti.zoom_academia.transverse.TypeService> typeService = createEnum("typeService", com.wouti.zoom_academia.transverse.TypeService.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QService(String variable) {
        this(Service.class, forVariable(variable), INITS);
    }

    public QService(Path<? extends Service> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QService(PathMetadata metadata, PathInits inits) {
        this(Service.class, metadata, inits);
    }

    public QService(Class<? extends Service> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.templatePrompt = inits.isInitialized("templatePrompt") ? new QTemplatePrompt(forProperty("templatePrompt"), inits.get("templatePrompt")) : null;
    }

}

