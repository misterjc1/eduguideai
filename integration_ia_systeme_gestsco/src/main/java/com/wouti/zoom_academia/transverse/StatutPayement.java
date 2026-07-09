package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum StatutPayement implements Serializable{

    réussie("réussie"),
	echouée("echouée"),
	annulée("annulée");


	private String label;

	public static StatutPayement convert(String str) {
        for (StatutPayement statut : StatutPayement.values()) {
            if (statut.toString().equalsIgnoreCase(str) ){
                return statut;
            }
        }
        return null;
    }

	private StatutPayement(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

