package com.wouti.zoom_academia.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Niveau extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNiveau;
	private String codeNiveau;
	private String libelle;
	private String filiere;
	
	public Long getIdNiveau() {
		return idNiveau;
	}
	public void setIdNiveau(Long idNiveau) {
		this.idNiveau = idNiveau;
	}
	public String getCodeNiveau() {
		return codeNiveau;
	}
	public void setCodeNiveau(String codeNiveau) {
		this.codeNiveau = codeNiveau;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Niveau [idNiveau=" + idNiveau + ", codeNiveau=" + codeNiveau + ", libelle=" + libelle + ", filiere="
				+ filiere + "]";
	}
	
	
}
