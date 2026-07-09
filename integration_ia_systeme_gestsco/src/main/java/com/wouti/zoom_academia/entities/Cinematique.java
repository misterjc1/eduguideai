package com.wouti.zoom_academia.entities;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Cinematique extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idCinematique;
	
	private String codeCinematique;
	
    private String intitule;
    
    private String image;

    @JoinColumn(name = "TYPECINEMATIQUE")
    @ManyToOne
    private TypeCinematique typeCinematique;
    
    @JoinColumn(name = "TYPESERVICE")
    @ManyToOne
    private Service typeService;
    

    @JoinColumn(name = "CINEMATIQUEMERE")
    @ManyToOne
    private Cinematique cinematiqueMere;


	public Long getIdCinematique() {
		return idCinematique;
	}


	public void setIdCinematique(Long idCinematique) {
		this.idCinematique = idCinematique;
	}


	public String getCodeCinematique() {
		return codeCinematique;
	}


	public void setCodeCinematique(String codeCinematique) {
		this.codeCinematique = codeCinematique;
	}


	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public TypeCinematique getTypeCinematique() {
		return typeCinematique;
	}


	public void setTypeCinematique(TypeCinematique typeCinematique) {
		this.typeCinematique = typeCinematique;
	}


	public Service getTypeService() {
		return typeService;
	}


	public void setTypeService(Service typeService) {
		this.typeService = typeService;
	}


	public Cinematique getCinematiqueMere() {
		return cinematiqueMere;
	}


	public void setCinematiqueMere(Cinematique cinematiqueMere) {
		this.cinematiqueMere = cinematiqueMere;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Cinematique [idCinematique=" + idCinematique + ", codeCinematique=" + codeCinematique + ", intitule="
				+ intitule + ", image=" + image + ", typeCinematique=" + typeCinematique + ", typeService="
				+ typeService + ", cinematiqueMere=" + cinematiqueMere + "]";
	}


	
}
