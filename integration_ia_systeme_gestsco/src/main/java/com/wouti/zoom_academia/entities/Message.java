package com.wouti.zoom_academia.entities;

import java.util.Date;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.EtatMessage;
import com.wouti.zoom_academia.transverse.TypeMessage;

@Entity
public class Message extends AuditModel {

	@Id
	@GeneratedValue(generator="message_generator")
	@SequenceGenerator(
            name = "message_generator",
            sequenceName = "message_generator"
    )
    private Long id;
	private String codeMessage;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private TypeMessage type;

	@Enumerated(EnumType.STRING)
	@Column(name = "ETAT")
	private EtatMessage etat;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnvoie;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAnnulation;

	@Temporal(TemporalType.DATE)
	private Date date;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeMessage() {
		return codeMessage;
	}

	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public EtatMessage getEtat() {
		return etat;
	}

	public void setEtat(EtatMessage etat) {
		this.etat = etat;
	}


	public Date getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Date dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	public Date getDateAnnulation() {
		return dateAnnulation;
	}

	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
