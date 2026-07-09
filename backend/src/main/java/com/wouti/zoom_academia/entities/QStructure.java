package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStructure is a Querydsl query type for Structure
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStructure extends EntityPathBase<Structure> {

    private static final long serialVersionUID = -1698425812L;

    public static final QStructure structure = new QStructure("structure");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath adresse = createString("adresse");

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final StringPath codeStructure = createString("codeStructure");

    public final StringPath codeZonier = createString("codeZonier");

    public final StringPath compteRecouvrement = createString("compteRecouvrement");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath email = createString("email");

    public final StringPath faxe = createString("faxe");

    public final StringPath heureFermeture = createString("heureFermeture");

    public final StringPath heureOuverture = createString("heureOuverture");

    public final NumberPath<Long> idStructure = createNumber("idStructure", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath libelle = createString("libelle");

    public final StringPath logo = createString("logo");

    public final StringPath natureStructure = createString("natureStructure");

    public final StringPath nomResponsable = createString("nomResponsable");

    public final StringPath raisonSociale = createString("raisonSociale");

    public final StringPath regimeImposition = createString("regimeImposition");

    public final StringPath rib = createString("rib");

    public final NumberPath<Double> seuilTransaction = createNumber("seuilTransaction", Double.class);

    public final StringPath sigle = createString("sigle");

    public final StringPath siteWeb = createString("siteWeb");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath telephone1 = createString("telephone1");

    public final StringPath telephone2 = createString("telephone2");

    public final StringPath typeStructure = createString("typeStructure");

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QStructure(String variable) {
        super(Structure.class, forVariable(variable));
    }

    public QStructure(Path<? extends Structure> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStructure(PathMetadata metadata) {
        super(Structure.class, metadata);
    }

}

