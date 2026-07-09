package com.wouti.zoom_academia.vo;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


public class AuditVo  {

    private String auteur;
    private String description;

	@Temporal(TemporalType.TIMESTAMP)
    private Date date;

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
