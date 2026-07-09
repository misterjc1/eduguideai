package com.wouti.zoom_academia.entities;


import java.util.List;

import com.wouti.zoom_academia.transverse.TypeService;

import jakarta.persistence.*;


@Entity
public class Service extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long idService;
	
    private String codeService;
    
    
    private TypeService typeService;
    
    private String message;
    
    @Column(length = 5000) // Augmentez si nécessaire pour stocker plus de variables
    private String logo;
    
    
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@OneToOne
    @JoinColumn(name = "templatePrompt_id")
    private TemplatePrompt templatePrompt;
    
    @OneToMany(mappedBy = "service")
    private List<Parametre> parametre;

	public Long getIdService() {
		return idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	public String getCodeService() {
		return codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TemplatePrompt getTemplatePrompt() {
		return templatePrompt;
	}

	public void setTemplatePrompt(TemplatePrompt templatePrompt) {
		this.templatePrompt = templatePrompt;
	}

	public List<Parametre> getParametre() {
		return parametre;
	}

	public void setParametre(List<Parametre> parametre) {
		this.parametre = parametre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Service [idService=" + idService + ", codeService=" + codeService + ", typeService=" + typeService
				+ ", message=" + message + "]";
	}

	

	
	
}

	