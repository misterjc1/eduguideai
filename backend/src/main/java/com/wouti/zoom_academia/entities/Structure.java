package com.wouti.zoom_academia.entities;


import jakarta.persistence.*;


@Entity
public class Structure extends AuditModel {

    private static final long serialVersionUID = 3776337715121418208L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStructure;
    private String codeStructure;
    private String libelle;
    private String raisonSociale;
    private String nomResponsable;
    private String telephone1;
    private String telephone2;
    private String heureOuverture;
    private String heureFermeture;
    private String codeZonier;
    private String typeStructure;
    private String regimeImposition;
    private String natureStructure;
    private String siteWeb;
    private String faxe;
    private String email;
    private String adresse;
    private String rib;
    private String sigle;
    private String logo;
    private String compteRecouvrement;
    private double balance;
    private Double seuilTransaction;

    /**
     * @return Long return the idStructure
     */
    public Long getIdStructure() {
        return idStructure;
    }

    /**
     * @param idStructure the idStructure to set
     */
    public void setIdStructure(Long idStructure) {
        this.idStructure = idStructure;
    }

    /**
     * @return String return the codeStructure
     */
    public String getCodeStructure() {
        return codeStructure;
    }

    /**
     * @param codeStructure the codeStructure to set
     */
    public void setCodeStructure(String codeStructure) {
        this.codeStructure = codeStructure;
    }

    public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
     * @return String return the raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * @param raisonSociale the raisonSociale to set
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * @return String return the nomResponsable
     */
    public String getNomResponsable() {
        return nomResponsable;
    }

    /**
     * @param nomResponsable the nomResponsable to set
     */
    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    /**
     * @return String return the telephone1
     */
    public String getTelephone1() {
        return telephone1;
    }

    /**
     * @param telephone1 the telephone1 to set
     */
    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    /**
     * @return String return the telephone2
     */
    public String getTelephone2() {
        return telephone2;
    }

    /**
     * @param telephone2 the telephone2 to set
     */
    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    /**
     * @return String return the heureOuverture
     */
    public String getHeureOuverture() {
        return heureOuverture;
    }

    /**
     * @param heureOuverture the heureOuverture to set
     */
    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    /**
     * @return String return the heureFermeture
     */
    public String getHeureFermeture() {
        return heureFermeture;
    }

    /**
     * @param heureFermeture the heureFermeture to set
     */
    public void setHeureFermeture(String heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    /**
     * @return String return the codeZonier
     */
    public String getCodeZonier() {
        return codeZonier;
    }

    /**
     * @param codeZonier the codeZonier to set
     */
    public void setCodeZonier(String codeZonier) {
        this.codeZonier = codeZonier;
    }

    /**
     * @return String return the typeStructure
     */
    public String getTypeStructure() {
        return typeStructure;
    }

    /**
     * @param typeStructure the typeStructure to set
     */
    public void setTypeStructure(String typeStructure) {
        this.typeStructure = typeStructure;
    }

    /**
     * @return String return the regimeImposition
     */
    public String getRegimeImposition() {
        return regimeImposition;
    }

    /**
     * @param regimeImposition the regimeImposition to set
     */
    public void setRegimeImposition(String regimeImposition) {
        this.regimeImposition = regimeImposition;
    }

    /**
     * @return String return the natureStructure
     */
    public String getNatureStructure() {
        return natureStructure;
    }

    /**
     * @param natureStructure the natureStructure to set
     */
    public void setNatureStructure(String natureStructure) {
        this.natureStructure = natureStructure;
    }

    /**
     * @return String return the siteWeb
     */
    public String getSiteWeb() {
        return siteWeb;
    }

    /**
     * @param siteWeb the siteWeb to set
     */
    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    /**
     * @return String return the faxe
     */
    public String getFaxe() {
        return faxe;
    }

    /**
     * @param faxe the faxe to set
     */
    public void setFaxe(String faxe) {
        this.faxe = faxe;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return String return the RIB
     */

    /**
     * @return String return the sigle
     */
    public String getSigle() {
        return sigle;
    }

    public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	/**
     * @param sigle the sigle to set
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * @return String return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return String return the compteRecouvrement
     */
    public String getCompteRecouvrement() {
        return compteRecouvrement;
    }

    /**
     * @param compteRecouvrement the compteRecouvrement to set
     */
    public void setCompteRecouvrement(String compteRecouvrement) {
        this.compteRecouvrement = compteRecouvrement;
    }

    /**
     * @return double return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

	public Double getSeuilTransaction() {
		return seuilTransaction;
	}

	public void setSeuilTransaction(Double seuilTransaction) {
		this.seuilTransaction = seuilTransaction;
	}



}
