package com.wouti.zoom_academia.entities;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class Notification extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idNotification;
	
	private String codeNotification;
	
	private String libelle;
	
    private String message;
    
    private Date dateCreation;
    
    @ManyToOne
    @JoinColumn(name = "PROMPT")
    private Prompt prompt;

	public Long getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
	}

	public String getCodeNotification() {
		return codeNotification;
	}

	public void setCodeNotification(String codeNotification) {
		this.codeNotification = codeNotification;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Prompt getPrompt() {
		return prompt;
	}

	public void setPrompt(Prompt prompt) {
		this.prompt = prompt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notification [idNotification=" + idNotification + ", codeNotification=" + codeNotification
				+ ", libelle=" + libelle + ", message=" + message + ", dateCreation=" + dateCreation + ", prompt="
				+ prompt + "]";
	}

    
    
	
}