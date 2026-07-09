package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum Statut implements Serializable{

	ACTIVE("Activé"),
	DESACTIVE("Désactivé");


	private String label;

	public static Statut convert(String str) {
        for (Statut statut : Statut.values()) {
            if (statut.toString().equalsIgnoreCase(str) ){
                return statut;
            }
        }
        return null;
}

	private Statut(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
