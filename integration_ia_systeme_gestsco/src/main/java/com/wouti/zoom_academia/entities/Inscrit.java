package com.wouti.zoom_academia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inscrit extends AuditModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idInscrit;
	private String codeInscrit;
	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	
//	@OneToMany(mappedBy = "inscrit")
//	private List<Liaison> liaison;

	@ManyToOne
	@JoinColumn(name = "NIVEAU")
	private Niveau niveau;
	
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Long getIdInscrit() {
		return idInscrit;
	}

	public void setIdInscrit(Long idInscrit) {
		this.idInscrit = idInscrit;
	}

	public String getCodeInscrit() {
		return codeInscrit;
	}

	public void setCodeInscrit(String codeInscrit) {
		this.codeInscrit = codeInscrit;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

//	public List<Liaison> getLiaison() {
//		return liaison;
//	}
//
//	public void setLiaison(List<Liaison> liaison) {
//		this.liaison = liaison;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Inscrit [idInscrit=" + idInscrit + ", codeInscrit=" + codeInscrit + ", matricule=" + matricule
				+ ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone + ", niveau="
				+ niveau + "]";
	}
	
	
	
	
}

