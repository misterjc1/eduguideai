package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;

@Entity
public class TypeCategorie extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeCategorie;
    private String codeTypeCategorie;
    private String libelle;
    private String description;
    private String defaultCodeTypeSuscription;
    /**
     * @return Long return the idTypeCategorie
     */
    public Long getIdTypeCategorie() {
        return idTypeCategorie;
    }

    /**
     * @param idTypeCategorie the idTypeCategorie to set
     */
    public void setIdTypeCategorie(Long idTypeCategorie) {
        this.idTypeCategorie = idTypeCategorie;
    }

    /**
     * @return String return the codeTypeCategorie
     */
    public String getCodeTypeCategorie() {
        return codeTypeCategorie;
    }

    /**
     * @param codeTypeCategorie the codeTypeCategorie to set
     */
    public void setCodeTypeCategorie(String codeTypeCategorie) {
        this.codeTypeCategorie = codeTypeCategorie;
    }

    /**
     * @return String return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

	public String getDefaultCodeTypeSuscription() {
		return defaultCodeTypeSuscription;
	}

	public void setDefaultCodeTypeSuscription(String defaultCodeTypeSuscription) {
		this.defaultCodeTypeSuscription = defaultCodeTypeSuscription;
	}


}
