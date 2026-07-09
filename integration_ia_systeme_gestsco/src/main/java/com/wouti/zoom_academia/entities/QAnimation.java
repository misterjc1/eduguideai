package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnimation is a Querydsl query type for Animation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnimation extends EntityPathBase<Animation> {

    private static final long serialVersionUID = -724434371L;

    public static final QAnimation animation = new QAnimation("animation");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeAnimation = createString("codeAnimation");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> idAnimation = createNumber("idAnimation", Long.class);

    public final StringPath Intitule = createString("Intitule");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final EnumPath<com.wouti.zoom_academia.transverse.TypeAnimation> type = createEnum("type", com.wouti.zoom_academia.transverse.TypeAnimation.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final StringPath value = createString("value");

    public QAnimation(String variable) {
        super(Animation.class, forVariable(variable));
    }

    public QAnimation(Path<? extends Animation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnimation(PathMetadata metadata) {
        super(Animation.class, metadata);
    }

}

