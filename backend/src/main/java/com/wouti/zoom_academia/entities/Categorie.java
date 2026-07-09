package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


@Entity
public class Categorie extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;
    private String codeCategorie;
    private String libelle;
    private String description;
    private String image;

    @JoinColumn(name = "TYPECATEGORIE")
    @ManyToOne
    private TypeCategorie typeCategorie;


    @JoinColumn(name = "CATEGORIEMERE")
    @ManyToOne
    private Categorie categorieMere;


	public Long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getCodeCategorie() {
		return codeCategorie;
	}
	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeCategorie getTypeCategorie() {
		return typeCategorie;
	}
	public void setTypeCategorie(TypeCategorie typeCategorie) {
		this.typeCategorie = typeCategorie;
	}
	public Categorie getCategorieMere() {
		return categorieMere;
	}
	public void setCategorieMere(Categorie categorieMere) {
		this.categorieMere = categorieMere;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}





}
