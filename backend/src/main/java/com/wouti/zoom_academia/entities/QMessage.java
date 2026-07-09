package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = 2061385984L;

    public static final QMessage message = new QMessage("message");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeMessage = createString("codeMessage");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateAnnulation = createDateTime("dateAnnulation", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateEnvoie = createDateTime("dateEnvoie", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final EnumPath<com.wouti.zoom_academia.transverse.EtatMessage> etat = createEnum("etat", com.wouti.zoom_academia.transverse.EtatMessage.class);

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

    public QMessage(String variable) {
        super(Message.class, forVariable(variable));
    }

    public QMessage(Path<? extends Message> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessage(PathMetadata metadata) {
        super(Message.class, metadata);
    }

}

