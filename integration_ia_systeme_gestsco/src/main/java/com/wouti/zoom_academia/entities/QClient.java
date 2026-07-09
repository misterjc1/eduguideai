package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClient is a Querydsl query type for Client
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClient extends EntityPathBase<Client> {

    private static final long serialVersionUID = 2141663154L;

    public static final QClient client = new QClient("client");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath adresse = createString("adresse");

    public final StringPath adresse2 = createString("adresse2");

    public final StringPath adresse3 = createString("adresse3");

    public final StringPath banque = createString("banque");

    public final StringPath codeAgence = createString("codeAgence");

    public final StringPath codeAssocie = createString("codeAssocie");

    public final StringPath codeClient = createString("codeClient");

    public final StringPath codeEnroleur = createString("codeEnroleur");

    public final StringPath codeZonier = createString("codeZonier");

    public final StringPath codeZonierService = createString("codeZonierService");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DatePath<java.util.Date> dateNaissance = createDate("dateNaissance", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final DatePath<java.util.Date> delivreLe = createDate("delivreLe", java.util.Date.class);

    public final StringPath delivrePar = createString("delivrePar");

    public final StringPath email = createString("email");

    public final StringPath firebaseToken = createString("firebaseToken");

    public final NumberPath<Long> idClient = createNumber("idClient", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath lieuNaissance = createString("lieuNaissance");

    public final StringPath nationalite = createString("nationalite");

    public final StringPath nature = createString("nature");

    public final StringPath nom = createString("nom");

    public final StringPath numeroCnib = createString("numeroCnib");

    public final StringPath numeroCompte = createString("numeroCompte");

    public final StringPath pin = createString("pin");

    public final StringPath prenom = createString("prenom");

    public final StringPath profession = createString("profession");

    public final StringPath service = createString("service");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath telephone = createString("telephone");

    public final StringPath telephone2 = createString("telephone2");

    public final EnumPath<com.wouti.zoom_academia.transverse.Sexe> type = createEnum("type", com.wouti.zoom_academia.transverse.Sexe.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QClient(String variable) {
        super(Client.class, forVariable(variable));
    }

    public QClient(Path<? extends Client> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClient(PathMetadata metadata) {
        super(Client.class, metadata);
    }

}

