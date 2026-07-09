package com.wouti.zoom_academia.entities;

import java.util.Date;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.Sexe;


@Entity
public class Client extends AuditModel {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClient;

    private String codeClient;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String numeroCnib;

    @Temporal(TemporalType.DATE)
    private Date delivreLe;
    private String delivrePar;
    private String nationalite;
    private String telephone2;
    private String codeZonier;
    private String adresse;
    private String adresse2;
    private String adresse3;

    private Sexe type;
    private String nature;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String lieuNaissance;
    private String profession;
    private String service;
    private String codeZonierService;


    private String banque;
    private String codeAgence;
    private String numeroCompte;

    private String codeEnroleur;
    private String codeAssocie;


    private String pin;


	private String firebaseToken;




	public String getFirebaseToken() {
		return firebaseToken;
	}
	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}
    private boolean isActive= false;
	public long getIdClient() {
		return idClient;
	}
	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	public String getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumeroCnib() {
		return numeroCnib;
	}
	public void setNumeroCnib(String numeroCnib) {
		this.numeroCnib = numeroCnib;
	}
	public Date getDelivreLe() {
		return delivreLe;
	}
	public void setDelivreLe(Date delivreLe) {
		this.delivreLe = delivreLe;
	}
	public String getDelivrePar() {
		return delivrePar;
	}
	public void setDelivrePar(String delivrePar) {
		this.delivrePar = delivrePar;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getCodeZonier() {
		return codeZonier;
	}
	public void setCodeZonier(String codeZonier) {
		this.codeZonier = codeZonier;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Sexe getType() {
		return type;
	}
	public void setType(Sexe type) {
		this.type = type;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
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
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getCodeZonierService() {
		return codeZonierService;
	}
	public void setCodeZonierService(String codeZonierService) {
		this.codeZonierService = codeZonierService;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getCodeAgence() {
		return codeAgence;
	}
	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public String getCodeEnroleur() {
		return codeEnroleur;
	}
	public void setCodeEnroleur(String codeEnroleur) {
		this.codeEnroleur = codeEnroleur;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodeAssocie() {
		return codeAssocie;
	}
	public void setCodeAssocie(String codeAssocie) {
		this.codeAssocie = codeAssocie;
	}



	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getAdresse3() {
		return adresse3;
	}
	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", codeClient=" + codeClient + ", nom=" + nom + ", prenom=" + prenom
				+ ", telephone=" + telephone + ", email=" + email + ", numeroCnib=" + numeroCnib + ", delivreLe="
				+ delivreLe + ", delivrePar=" + delivrePar + ", nationalite=" + nationalite + ", telephone2="
				+ telephone2 + ", codeZonier=" + codeZonier + ", adresse=" + adresse + ", adresse2=" + adresse2
				+ ", adresse3=" + adresse3 + ", type=" + type + ", nature=" + nature + ", dateNaissance="
				+ dateNaissance + ", lieuNaissance=" + lieuNaissance + ", profession=" + profession + ", service="
				+ service + ", codeZonierService=" + codeZonierService + ", banque=" + banque + ", codeAgence="
				+ codeAgence + ", numeroCompte=" + numeroCompte + ", codeEnroleur=" + codeEnroleur + ", codeAssocie="
				+ codeAssocie + ", pin=" + pin + ", isActive=" + isActive + "]";
	}


}
