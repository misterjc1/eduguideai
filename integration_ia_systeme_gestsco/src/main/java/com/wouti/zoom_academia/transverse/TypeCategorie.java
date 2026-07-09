package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypeCategorie implements Serializable{
	
	ASSISTANCE("Assistance"),
	ANALYSE("Analyse");


	private String label;

	public static TypeCategorie getValueOf(String str) {
        for (TypeCategorie theme : TypeCategorie.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private TypeCategorie(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
