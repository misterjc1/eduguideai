package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditModel is a Querydsl query type for AuditModel
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditModel extends EntityPathBase<AuditModel> {

    private static final long serialVersionUID = 379031669L;

    public static final QAuditModel auditModel = new QAuditModel("auditModel");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final StringPath creatorCode = createString("creatorCode");

    public final DateTimePath<java.util.Date> deleteDate = createDateTime("deleteDate", java.util.Date.class);

    public final StringPath deleterCode = createString("deleterCode");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final DateTimePath<java.util.Date> synchronizationDate = createDateTime("synchronizationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final StringPath updaterCode = createString("updaterCode");

    public QAuditModel(String variable) {
        super(AuditModel.class, forVariable(variable));
    }

    public QAuditModel(Path<? extends AuditModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditModel(PathMetadata metadata) {
        super(AuditModel.class, metadata);
    }

}

