package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;

@Entity
public class Reponse extends AuditModel{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idResp;
	
	private String codeResp;
	
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "CINEMATIQUE")
    private Cinematique cinematique;

	public Long getIdResp() {
		return idResp;
	}

	public void setIdResp(Long idResp) {
		this.idResp = idResp;
	}

	public String getCodeResp() {
		return codeResp;
	}

	public void setCodeResp(String codeResp) {
		this.codeResp = codeResp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Cinematique getCinematique() {
		return cinematique;
	}

	public void setCinematique(Cinematique cinematique) {
		this.cinematique = cinematique;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Reponse [idResp=" + idResp + ", codeResp=" + codeResp + ", message=" + message + ", cinematique="
				+ cinematique + "]";
	}
	
	
}
