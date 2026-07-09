package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfil is a Querydsl query type for Profil
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfil extends EntityPathBase<Profil> {

    private static final long serialVersionUID = -1775404509L;

    public static final QProfil profil = new QProfil("profil");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeProfil = createString("codeProfil");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath description = createString("description");

    public final NumberPath<Long> idProfil = createNumber("idProfil", Long.class);

    public final StringPath intitule = createString("intitule");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath role = createString("role");

    public final BooleanPath statut = createBoolean("statut");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final ListPath<Utilisateur, QUtilisateur> utilisateurs = this.<Utilisateur, QUtilisateur>createList("utilisateurs", Utilisateur.class, QUtilisateur.class, PathInits.DIRECT2);

    public QProfil(String variable) {
        super(Profil.class, forVariable(variable));
    }

    public QProfil(Path<? extends Profil> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfil(PathMetadata metadata) {
        super(Profil.class, metadata);
    }

}

