package com.wouti.zoom_academia.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wouti.zoom_academia.transverse.TypePersonne;
import com.wouti.zoom_academia.transverse.TypeUtilisateur;

@Entity
public class Utilisateur extends AuditModel {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtilisateur;

    private String codeUtilisateur;

    private String nom;

    private String prenom;

    private String email;

    private TypeUtilisateur type;

    private String telephone;
        
    private String pin;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    private Boolean actif = true;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	@ManyToOne
    @JoinColumn(name = "profil")
    private Profil profil;
	
	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur")
    private List<Prompt> prompts;
    
	@JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Donnees> donnees;
    
	@JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Liaison> liaisons;
    
//  @OneToMany(mappedBy = "utilisateur")
//  private List<Inscrit> inscrits;

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getCodeUtilisateur() {
		return codeUtilisateur;
	}

	public void setCodeUtilisateur(String codeUtilisateur) {
		this.codeUtilisateur = codeUtilisateur;
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

	public TypeUtilisateur getType() {
		return type;
	}

	public void setType(TypeUtilisateur type) {
		this.type = type;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public List<Prompt> getPrompts() {
		return prompts;
	}

	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}

	public List<Donnees> getDonnees() {
		return donnees;
	}

	public void setDonnees(List<Donnees> donnees) {
		this.donnees = donnees;
	}

	public List<Liaison> getLiaisons() {
		return liaisons;
	}

	public void setLiaisons(List<Liaison> liaisons) {
		this.liaisons = liaisons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", codeUtilisateur=" + codeUtilisateur + ", nom=" + nom
				+ ", prenom=" + prenom + ", email=" + email + ", type=" + type + ", telephone=" + telephone + ", pin="
				+ pin + ", username=" + username + ", password=" + password + ", actif=" + actif + ", profil=" + profil
				+ ", prompts=" + prompts + ", donnees=" + donnees + ", liaisons=" + liaisons + "]";
	}
    


	
	

}
