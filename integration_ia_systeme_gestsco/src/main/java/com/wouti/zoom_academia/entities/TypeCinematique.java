package com.wouti.zoom_academia.entities;


import java.util.List;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.Niveau;

@Entity
public class TypeCinematique extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idTypeCinematique;
	
	private String codeTypeCinematique;
	
    private Niveau niveau;
    
    private String typeBouton;
    
    private String choixMultiple;
    
	public Long getIdTypeCinematique() {
		return idTypeCinematique;
	}

	public void setIdTypeCinematique(Long idTypeCinematique) {
		this.idTypeCinematique = idTypeCinematique;
	}

	public String getCodeTypeCinematique() {
		return codeTypeCinematique;
	}

	public void setCodeTypeCinematique(String codeTypeCinematique) {
		this.codeTypeCinematique = codeTypeCinematique;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public String getTypeBouton() {
		return typeBouton;
	}

	public void setTypeBouton(String typeBouton) {
		this.typeBouton = typeBouton;
	}

	public String getChoixMultiple() {
		return choixMultiple;
	}

	public void setChoixMultiple(String choixMultiple) {
		this.choixMultiple = choixMultiple;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TypeCinematique [idTypeCinematique=" + idTypeCinematique + ", codeTypeCinematique="
				+ codeTypeCinematique + ", niveau=" + niveau + ", typeBouton=" + typeBouton + ", choixMultiple="
				+ choixMultiple + "]";
	}


	
    
   
}
