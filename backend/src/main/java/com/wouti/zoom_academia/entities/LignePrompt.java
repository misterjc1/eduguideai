package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.LignePromptEnum;

@Entity
public class LignePrompt extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idLignePrompt;
	
	private String codeLignePrompt;
	
    private LignePromptEnum lignePromptEnum;
    
    
    @ManyToOne
    @JoinColumn(name = "PROMPT")
    private Prompt prompt;


	public Long getIdLignePrompt() {
		return idLignePrompt;
	}


	public void setIdLignePrompt(Long idLignePrompt) {
		this.idLignePrompt = idLignePrompt;
	}


	public String getCodeLignePrompt() {
		return codeLignePrompt;
	}


	public void setCodeLignePrompt(String codeLignePrompt) {
		this.codeLignePrompt = codeLignePrompt;
	}


	public LignePromptEnum getLignePromptEnum() {
		return lignePromptEnum;
	}


	public void setLignePromptEnum(LignePromptEnum lignePromptEnum) {
		this.lignePromptEnum = lignePromptEnum;
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
		return "LignePrompt [idLignePrompt=" + idLignePrompt + ", codeLignePrompt=" + codeLignePrompt
				+ ", lignePromptEnum=" + lignePromptEnum + ", prompt=" + prompt + "]";
	}


	
    
	
}