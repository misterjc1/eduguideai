package com.wouti.zoom_academia.entities;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class Tranche extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTranche;
    private String codeTranche;
    private String intitule;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private boolean estActif;
    private Double montantTtc;
    private int duree;


	public Long getIdTranche() {
		return idTranche;
	}

	public void setIdTranche(Long idTranche) {
		this.idTranche = idTranche;
	}

	public String getCodeTranche() {
		return codeTranche;
	}

	public void setCodeTranche(String codeTranche) {
		this.codeTranche = codeTranche;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isEstActif() {
		return estActif;
	}

	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}

	public Double getMontantTtc() {
		return montantTtc;
	}

	public void setMontantTtc(Double montantTtc) {
		this.montantTtc = montantTtc;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}





}
