package com.wouti.zoom_academia.entities;


import jakarta.persistence.*;


@Entity
public class Produit extends AuditModel {

    private static final long serialVersionUID = 7911726455129108569L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String codeProduit;
    private String nom;
    private String description;
    private String Image;


    @JoinColumn(name="CATEGORIE")
    @ManyToOne
	private Categorie categorie;


	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImage() {
		return Image;
	}


	public void setImage(String image) {
		Image = image;
	}





}
