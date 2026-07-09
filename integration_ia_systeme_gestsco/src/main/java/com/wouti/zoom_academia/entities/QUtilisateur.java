package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUtilisateur is a Querydsl query type for Utilisateur
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUtilisateur extends EntityPathBase<Utilisateur> {

    private static final long serialVersionUID = -2128719876L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUtilisateur utilisateur = new QUtilisateur("utilisateur");

    public final QAuditModel _super = new QAuditModel(this);

    public final BooleanPath actif = createBoolean("actif");

    public final StringPath codeUtilisateur = createString("codeUtilisateur");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final ListPath<Donnees, QDonnees> donnees = this.<Donnees, QDonnees>createList("donnees", Donnees.class, QDonnees.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> idUtilisateur = createNumber("idUtilisateur", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final ListPath<Liaison, QLiaison> liaisons = this.<Liaison, QLiaison>createList("liaisons", Liaison.class, QLiaison.class, PathInits.DIRECT2);

    public final StringPath nom = createString("nom");

    public final StringPath password = createString("password");

    public final StringPath pin = createString("pin");

    public final StringPath prenom = createString("prenom");

    public final QProfil profil;

    public final ListPath<Prompt, QPrompt> prompts = this.<Prompt, QPrompt>createList("prompts", Prompt.class, QPrompt.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath telephone = createString("telephone");

    public final EnumPath<com.wouti.zoom_academia.transverse.TypeUtilisateur> type = createEnum("type", com.wouti.zoom_academia.transverse.TypeUtilisateur.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public final StringPath username = createString("username");

    public QUtilisateur(String variable) {
        this(Utilisateur.class, forVariable(variable), INITS);
    }

    public QUtilisateur(Path<? extends Utilisateur> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUtilisateur(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUtilisateur(PathMetadata metadata, PathInits inits) {
        this(Utilisateur.class, metadata, inits);
    }

    public QUtilisateur(Class<? extends Utilisateur> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profil = inits.isInitialized("profil") ? new QProfil(forProperty("profil")) : null;
    }

}

