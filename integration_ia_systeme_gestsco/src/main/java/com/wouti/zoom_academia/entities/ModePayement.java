package com.wouti.zoom_academia.entities;


import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.TypeModePaiement;


@Entity
public class ModePayement extends AuditModel{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idModePayement;
	private String codeModePayement;
	private String libelle;
	private TypeModePaiement type;
	private String numeroCompte;
	private String parametres;
	private String syntaxe;
	private String lien;
	private String logo;


	public Long getIdModePayement() {
		return idModePayement;
	}
	public void setIdModePayement(Long idModePayement) {
		this.idModePayement = idModePayement;
	}
	public String getCodeModePayement() {
		return codeModePayement;
	}
	public void setCodeModePayement(String codeModePayement) {
		this.codeModePayement = codeModePayement;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public TypeModePaiement getType() {
		return type;
	}
	public void setType(TypeModePaiement type) {
		this.type = type;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public String getParametres() {
		return parametres;
	}
	public void setParametres(String parametres) {
		this.parametres = parametres;
	}
	public String getSyntaxe() {
		return syntaxe;
	}
	public void setSyntaxe(String syntaxe) {
		this.syntaxe = syntaxe;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "ModePayement [idModePayement=" + idModePayement + ", codeModePayement=" + codeModePayement
				+ ", libelle=" + libelle + ", type=" + type + ", numeroCompte=" + numeroCompte + ", parametres="
				+ parametres + ", syntaxe=" + syntaxe + ", lien=" + lien + ", logo=" + logo + "]";
	}

}
