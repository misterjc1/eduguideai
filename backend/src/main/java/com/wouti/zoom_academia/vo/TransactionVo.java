package com.wouti.zoom_academia.vo;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


public class TransactionVo {

	private String codeExterne;
	private String refPaiement;
	private Double montant;
	private String numeroCompte;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datePaiement;

	private String intituleModePaiement;

	public String getCodeExterne() {
		return codeExterne;
	}
	public void setCodeExterne(String codeExterne) {
		this.codeExterne = codeExterne;
	}
	public String getRefPaiement() {
		return refPaiement;
	}
	public void setRefPaiement(String refPaiement) {
		this.refPaiement = refPaiement;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public Date getDatePaiement() {
		return datePaiement;
	}
	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public String getIntituleModePaiement() {
		return intituleModePaiement;
	}
	public void setIntituleModePaiement(String intituleModePaiement) {
		this.intituleModePaiement = intituleModePaiement;
	}
	@Override
	public String toString() {
		return "TransactionVo [codeExterne=" + codeExterne + ", refPaiement=" + refPaiement + ", montant=" + montant
				+ ", numeroCompte=" + numeroCompte + ", datePaiement=" + datePaiement + ", codeModePaiement="
				+ intituleModePaiement + "]";
	}


}
