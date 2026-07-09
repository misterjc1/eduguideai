package com.wouti.zoom_academia.entities;

import java.util.Date;

import jakarta.persistence.*;
import com.wouti.zoom_academia.transverse.TypePersonne;

@Entity
public class Personne extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;

    private String codePersonne;

    private String matricule;

    private String nom;

    private String prenom;

    private String email;

    private Date dateNaissance;

    private String lieuNaissance;

    private TypePersonne typePersonne;

    private String telephone1;
    
    private String telephone2;
    
    

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getCodePersonne() {
		return codePersonne;
	}

	public void setCodePersonne(String codePersonne) {
		this.codePersonne = codePersonne;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public TypePersonne getTypePersonne() {
		return typePersonne;
	}

	public void setTypePersonne(TypePersonne typePersonne) {
		this.typePersonne = typePersonne;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", codePersonne=" + codePersonne + ", matricule=" + matricule
				+ ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", dateNaissance=" + dateNaissance
				+ ", lieuNaissance=" + lieuNaissance + ", typePersonne=" + typePersonne + ", telephone1=" + telephone1
				+ ", telephone2=" + telephone2 + ", prompts=" + "]";
	}
    
    
    
	
}