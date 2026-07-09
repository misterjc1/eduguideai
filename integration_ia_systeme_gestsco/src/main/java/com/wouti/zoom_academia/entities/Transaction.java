package com.wouti.zoom_academia.entities;

import java.util.Date;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.StatutPayement;
import com.wouti.zoom_academia.transverse.TypePaiement;


@Entity
public class  Transaction extends AuditModel{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String codeTransaction;
	private Double montant;
	private String numeroCompte;
	private StatutPayement statutPayement;
	private String referencePayement;
	private String modePayement;
	private String libelle;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTransaction;

	private TypePaiement typePaiement;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeTransaction() {
		return codeTransaction;
	}

	public void setCodeTransaction(String codeTransaction) {
		this.codeTransaction = codeTransaction;
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



	public StatutPayement getStatutPayement() {
		return statutPayement;
	}

	public void setStatutPayement(StatutPayement statutPayement) {
		this.statutPayement = statutPayement;
	}

	public String getReferencePayement() {
		return referencePayement;
	}

	public void setReferencePayement(String referencePayement) {
		this.referencePayement = referencePayement;
	}

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public String getModePayement() {
		return modePayement;
	}

	public void setModePayement(String modePayement) {
		this.modePayement = modePayement;
	}

	public TypePaiement getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(TypePaiement typePaiement) {
		this.typePaiement = typePaiement;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", codeTransaction=" + codeTransaction + ", montant=" + montant
				+ ", numeroCompte=" + numeroCompte + ", statutPayement=" + statutPayement + ", referencePayement="
				+ referencePayement + ", modePayement=" + modePayement + ", dateTransaction=" + dateTransaction
				+ ", typePaiement=" + typePaiement +  "]";
	}



}
