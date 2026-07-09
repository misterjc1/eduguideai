package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;

@Entity
public class TypeDocument extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeDocument;

    private String codeTypeDocument;
    private String libelle;

    @JoinColumn(name = "TYPESOUSCRIPTION")
    @ManyToOne
    private TypeSouscription typeSouscription;

    public long getIdTypeDocument() {
        return idTypeDocument;
    }
    public void setIdTypeDocument(long idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }
    public String getCodeTypeDocument() {
        return codeTypeDocument;
    }
    public void setCodeTypeDocument(String codeTypeDocument) {
        this.codeTypeDocument = codeTypeDocument;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeSouscription getTypeSouscription() {
        return typeSouscription;
    }
    public void setTypeSouscription(TypeSouscription typeSouscription) {
        this.typeSouscription = typeSouscription;
    }




}
