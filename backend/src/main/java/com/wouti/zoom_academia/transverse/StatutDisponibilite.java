package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum StatutDisponibilite implements Serializable {

	DISPONIBLE("Disponible"),
	INDISPONIBLE("indisponible");


	private String label;

	public static StatutDisponibilite getValueOf(String str) {
        for (StatutDisponibilite theme : StatutDisponibilite.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }




	private StatutDisponibilite(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
