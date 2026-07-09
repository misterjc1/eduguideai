package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTypeDocument is a Querydsl query type for TypeDocument
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeDocument extends EntityPathBase<TypeDocument> {

    private static final long serialVersionUID = 1340824860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTypeDocument typeDocument = new QTypeDocument("typeDocument");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeTypeDocument = createString("codeTypeDocument");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idTypeDocument = createNumber("idTypeDocument", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final QTypeSouscription typeSouscription;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QTypeDocument(String variable) {
        this(TypeDocument.class, forVariable(variable), INITS);
    }

    public QTypeDocument(Path<? extends TypeDocument> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTypeDocument(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTypeDocument(PathMetadata metadata, PathInits inits) {
        this(TypeDocument.class, metadata, inits);
    }

    public QTypeDocument(Class<? extends TypeDocument> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.typeSouscription = inits.isInitialized("typeSouscription") ? new QTypeSouscription(forProperty("typeSouscription")) : null;
    }

}

