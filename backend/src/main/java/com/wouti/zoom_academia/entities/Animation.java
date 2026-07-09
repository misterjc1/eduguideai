package com.wouti.zoom_academia.entities;

import com.wouti.zoom_academia.transverse.TypeAnimation;

import jakarta.persistence.*;



@Entity
public class Animation extends AuditModel {


	private static final long serialVersionUID = -8128888995433285152L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimation;
    private String codeAnimation;
    private String Intitule;
    private TypeAnimation type;
    private String value;


	public String getCodeAnimation() {
		return codeAnimation;
	}

	public void setCodeAnimation(String codeAnimation) {
		this.codeAnimation = codeAnimation;
	}

	public String getIntitule() {
		return Intitule;
	}

	public void setIntitule(String intitule) {
		Intitule = intitule;
	}


	public TypeAnimation getType() {
		return type;
	}

	public void setType(TypeAnimation type) {
		this.type = type;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdAnimation() {
		return idAnimation;
	}

	@Override
	public String toString() {
		return "Animation [idAnimation=" + idAnimation + ", codeAnimation=" + codeAnimation + ", Intitule=" + Intitule
				+ ", type=" + type + ", value=" + value + "]";
	}












}