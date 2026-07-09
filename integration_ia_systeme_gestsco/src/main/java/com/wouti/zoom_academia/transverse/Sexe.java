package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum Sexe implements Serializable {

	MASCULIN("Masculin"),
	FEMININ("Feminin");

	private String label;

	public static Sexe getValueOf(String str) {
        for (Sexe theme : Sexe.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private Sexe(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
