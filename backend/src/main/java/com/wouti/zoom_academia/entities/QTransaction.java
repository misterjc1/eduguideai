package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = 598273879L;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeTransaction = createString("codeTransaction");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DateTimePath<java.util.Date> dateTransaction = createDateTime("dateTransaction", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    public final StringPath modePayement = createString("modePayement");

    public final NumberPath<Double> montant = createNumber("montant", Double.class);

    public final StringPath numeroCompte = createString("numeroCompte");

    public final StringPath referencePayement = createString("referencePayement");

    public final EnumPath<com.wouti.zoom_academia.transverse.StatutPayement> statutPayement = createEnum("statutPayement", com.wouti.zoom_academia.transverse.StatutPayement.class);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final EnumPath<com.wouti.zoom_academia.transverse.TypePaiement> typePaiement = createEnum("typePaiement", com.wouti.zoom_academia.transverse.TypePaiement.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QTransaction(String variable) {
        super(Transaction.class, forVariable(variable));
    }

    public QTransaction(Path<? extends Transaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransaction(PathMetadata metadata) {
        super(Transaction.class, metadata);
    }

}

