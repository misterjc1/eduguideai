package com.wouti.zoom_academia.entities;

import java.util.List;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.Variables;

@Entity
public class TemplatePrompt extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemplatePrompt;
	
	@Column(length = 1000) // Augmentez si nécessaire pour stocker plus de variables
    private String codeTemplatePrompt;
    
    @Column(length = 10000) // Augmentez si nécessaire pour stocker plus de variables
    private String description;

    @Column(length = 1000) // Augmentez si nécessaire pour stocker plus de variables
    private String variables;
    
    
    @OneToMany(mappedBy = "templatePrompt")
    private List<Prompt> prompts;
    
    @OneToOne(mappedBy = "templatePrompt")
    private Service service;

	public Long getIdTemplatePrompt() {
		return idTemplatePrompt;
	}

	public void setIdTemplatePrompt(Long idTemplatePrompt) {
		this.idTemplatePrompt = idTemplatePrompt;
	}

	public String getCodeTemplatePrompt() {
		return codeTemplatePrompt;
	}

	public void setCodeTemplatePrompt(String codeTemplatePrompt) {
		this.codeTemplatePrompt = codeTemplatePrompt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

	public List<Prompt> getPrompts() {
		return prompts;
	}

	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TemplatePrompt [idTemplatePrompt=" + idTemplatePrompt + ", codeTemplatePrompt=" + codeTemplatePrompt
				+ ", description=" + description + ", variables=" + variables + "]";
	}

	
	
}