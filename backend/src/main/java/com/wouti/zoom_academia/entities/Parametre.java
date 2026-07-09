package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


@Entity
public class Parametre extends AuditModel {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long idParametre;
	
    private String codeParametre;
    
    private String parametres;
    
    @ManyToOne
    @JoinColumn(name = "SERVICE")
    private Service service;

	public Long getIdParametre() {
		return idParametre;
	}

	public void setIdParametre(Long idParametre) {
		this.idParametre = idParametre;
	}

	public String getCodeParametre() {
		return codeParametre;
	}

	public void setCodeParametre(String codeParametre) {
		this.codeParametre = codeParametre;
	}

	public String getParametres() {
		return parametres;
	}

	public void setParametres(String parametres) {
		this.parametres = parametres;
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
		return "Parametre [idParametre=" + idParametre + ", codeParametre=" + codeParametre + ", parametres="
				+ parametres + ", service=" + service + "]";
	}

    
	
    
    
}