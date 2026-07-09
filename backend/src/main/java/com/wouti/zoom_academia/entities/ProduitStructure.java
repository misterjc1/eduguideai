 package com.wouti.zoom_academia.entities;

 import jakarta.persistence.*;

@Entity
public class ProduitStructure extends AuditModel{

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduitStructure;

    private String codeProduitStructure;
    private String details;
    private Double prixFournisseur;
    private Double prixPropose;



	private int nombreTranche;
    private boolean estDisponible;

    @JoinColumn(name = "PRODUIT")
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "STRUCTURE")
    @ManyToOne
    private Structure structure;

	public long getIdProduitStructure() {
		return idProduitStructure;
	}

	public void setIdProduitStructure(long idProduitStructure) {
		this.idProduitStructure = idProduitStructure;
	}

	public String getCodeProduitStructure() {
		return codeProduitStructure;
	}

	public void setCodeProduitStructure(String codeProduitStructure) {
		this.codeProduitStructure = codeProduitStructure;
	}

	public Double getPrixFournisseur() {
		return prixFournisseur;
	}

	public void setPrixFournisseur(Double prixFournisseur) {
		this.prixFournisseur = prixFournisseur;
	}

	public Double getPrixPropose() {
		return prixPropose;
	}

	public void setPrixPropose(Double prixPropose) {
		this.prixPropose = prixPropose;
	}

	public int getNombreTranche() {
		return nombreTranche;
	}

	public void setNombreTranche(int nombreTranche) {
		this.nombreTranche = nombreTranche;
	}

	public boolean isEstDisponible() {
		return estDisponible;
	}

	public void setEstDisponible(boolean estDisponible) {
		this.estDisponible = estDisponible;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}



	@Override
	public String toString() {
		return "ProduitStructure [idProduitStructure=" + idProduitStructure + ", codeProduitStructure="
				+ codeProduitStructure + ", details=" + details + ", prixFournisseur=" + prixFournisseur
				+ ", prixPropose=" + prixPropose + ",  nombreTranche="
				+ nombreTranche + ", estDisponible=" + estDisponible + ", produit=" + produit + ", structure="
				+ structure + "]";
	}



}
