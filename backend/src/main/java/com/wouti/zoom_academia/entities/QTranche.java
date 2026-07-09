package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTranche is a Querydsl query type for Tranche
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTranche extends EntityPathBase<Tranche> {

    private static final long serialVersionUID = 39385742L;

    public static final QTranche tranche = new QTranche("tranche");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeTranche = createString("codeTranche");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DatePath<java.util.Date> dateDebut = createDate("dateDebut", java.util.Date.class);

    public final DatePath<java.util.Date> dateFin = createDate("dateFin", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> duree = createNumber("duree", Integer.class);

    public final BooleanPath estActif = createBoolean("estActif");

    public final NumberPath<Long> idTranche = createNumber("idTranche", Long.class);

    public final StringPath intitule = createString("intitule");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final NumberPath<Double> montantTtc = createNumber("montantTtc", Double.class);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QTranche(String variable) {
        super(Tranche.class, forVariable(variable));
    }

    public QTranche(Path<? extends Tranche> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTranche(PathMetadata metadata) {
        super(Tranche.class, metadata);
    }

}

