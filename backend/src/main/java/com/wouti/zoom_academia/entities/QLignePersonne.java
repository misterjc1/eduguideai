package com.wouti.zoom_academia.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLignePersonne is a Querydsl query type for LignePersonne
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLignePersonne extends EntityPathBase<LignePersonne> {

    private static final long serialVersionUID = 107827942L;

    public static final QLignePersonne lignePersonne = new QLignePersonne("lignePersonne");

    public final QAuditModel _super = new QAuditModel(this);

    public final StringPath codeLignePersonne = createString("codeLignePersonne");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath creatorCode = _super.creatorCode;

    public final DateTimePath<java.util.Date> dateNaissance = createDateTime("dateNaissance", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleterCode = _super.deleterCode;

    public final StringPath email = createString("email");

    public final NumberPath<Long> idLignePersonne = createNumber("idLignePersonne", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final StringPath lieuNaissance = createString("lieuNaissance");

    public final StringPath matricule = createString("matricule");

    public final StringPath nom = createString("nom");

    public final ListPath<Personne, QPersonne> personnes = this.<Personne, QPersonne>createList("personnes", Personne.class, QPersonne.class, PathInits.DIRECT2);

    public final StringPath prenom = createString("prenom");

    //inherited
    public final DateTimePath<java.util.Date> synchronizationDate = _super.synchronizationDate;

    public final StringPath telephone1 = createString("telephone1");

    public final StringPath telephone2 = createString("telephone2");

    public final EnumPath<com.wouti.zoom_academia.transverse.TypePersonne> typePersonne = createEnum("typePersonne", com.wouti.zoom_academia.transverse.TypePersonne.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updaterCode = _super.updaterCode;

    public QLignePersonne(String variable) {
        super(LignePersonne.class, forVariable(variable));
    }

    public QLignePersonne(Path<? extends LignePersonne> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLignePersonne(PathMetadata metadata) {
        super(LignePersonne.class, metadata);
    }

}

