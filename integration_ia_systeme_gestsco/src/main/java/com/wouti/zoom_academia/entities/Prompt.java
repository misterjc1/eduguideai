package com.wouti.zoom_academia.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Prompt extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPrompt;

    private String codePrompt;
    
    private String message;

    private Date dateCreation;
    
    

    @ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "template_prompt_id")
    private TemplatePrompt templatePrompt;

    
    @OneToMany(mappedBy = "prompt")
    private List<Notification> notification;
    
    @OneToMany(mappedBy = "prompt")
    private List<LignePrompt> lignePrompt;

	public Long getIdPrompt() {
		return idPrompt;
	}

	public void setIdPrompt(Long idPrompt) {
		this.idPrompt = idPrompt;
	}

	public String getCodePrompt() {
		return codePrompt;
	}

	public void setCodePrompt(String codePrompt) {
		this.codePrompt = codePrompt;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public TemplatePrompt getTemplatePrompt() {
		return templatePrompt;
	}

	public void setTemplatePrompt(TemplatePrompt templatePrompt) {
		this.templatePrompt = templatePrompt;
	}

	public List<Notification> getNotification() {
		return notification;
	}

	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}

	public List<LignePrompt> getLignePrompt() {
		return lignePrompt;
	}

	public void setLignePrompt(List<LignePrompt> lignePrompt) {
		this.lignePrompt = lignePrompt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Prompt [idPrompt=" + idPrompt + ", codePrompt=" + codePrompt + ", message=" + message
				+ ", dateCreation=" + dateCreation + ", utilisateur=" + utilisateur + ", templatePrompt="
				+ templatePrompt + ", notification=" + notification + ", lignePrompt=" + lignePrompt + "]";
	}

	
	
}