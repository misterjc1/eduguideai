package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessageModel is a Querydsl query type for MessageModel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessageModel extends EntityPathBase<MessageModel> {

    private static final long serialVersionUID = -1890358775L;

    public static final QMessageModel messageModel = new QMessageModel("messageModel");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeMessageModel = createString("codeMessageModel");

    public final StringPath contenue = createString("contenue");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final EnumPath<com.wouti.zoom_academia.transverse.TypeMessage> type = createEnum("type", com.wouti.zoom_academia.transverse.TypeMessage.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QMessageModel(String variable) {
        super(MessageModel.class, forVariable(variable));
    }

    public QMessageModel(Path<? extends MessageModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageModel(PathMetadata metadata) {
        super(MessageModel.class, metadata);
    }

}

