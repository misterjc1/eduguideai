package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


@Entity
public class Donnees extends AuditModel{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idDonnee;
	
	private String codeDonnee;
	
	private Parametre codeParametre;
	
	private String intituleD;
	
	private String valeurD;
	
	private String fichierJoint;
	
	@ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    private Utilisateur utilisateur;

	public Long getIdDonnee() {
		return idDonnee;
	}

	public void setIdDonnee(Long idDonnee) {
		this.idDonnee = idDonnee;
	}

	public String getCodeDonnee() {
		return codeDonnee;
	}

	public void setCodeDonnee(String codeDonnee) {
		this.codeDonnee = codeDonnee;
	}

	public Parametre getCodeParametre() {
		return codeParametre;
	}

	public void setCodeParametre(Parametre codeParametre) {
		this.codeParametre = codeParametre;
	}

	public String getIntituleD() {
		return intituleD;
	}

	public void setIntituleD(String intituleD) {
		this.intituleD = intituleD;
	}

	public String getValeurD() {
		return valeurD;
	}

	public void setValeurD(String valeurD) {
		this.valeurD = valeurD;
	}

	public String getFichierJoint() {
		return fichierJoint;
	}

	public void setFichierJoint(String fichierJoint) {
		this.fichierJoint = fichierJoint;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Donnees [idDonnee=" + idDonnee + ", codeDonnee=" + codeDonnee + ", codeParametre=" + codeParametre
				+ ", intituleD=" + intituleD + ", valeurD=" + valeurD + ", fichierJoint=" + fichierJoint
				+ ", utilisateur=" + utilisateur + "]";
	}
	
	
	
	
}
