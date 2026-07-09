package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;

@Entity
public class TypeSouscription extends AuditModel {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeSouscription;

    private String codeTypeSouscription;
    private String libelle;

    public long getIdTypeSouscription() {
        return idTypeSouscription;
    }
    public void setIdTypeSouscription(long idTypeSouscription) {
        this.idTypeSouscription = idTypeSouscription;
    }
    public String getCodeTypeSouscription() {
        return codeTypeSouscription;
    }
    public void setCodeTypeSouscription(String codeTypeSouscription) {
        this.codeTypeSouscription = codeTypeSouscription;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
	@Override
	public String toString() {
		return "TypeSouscription [idTypeSouscription=" + idTypeSouscription + ", codeTypeSouscription="
				+ codeTypeSouscription + ", libelle=" + libelle + "]";
	}



}
