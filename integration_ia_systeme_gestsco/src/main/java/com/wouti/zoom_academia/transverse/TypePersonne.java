package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypePersonne implements Serializable {

	ELEVE("Eleve"),
	ETUDIANT("Etudiant"),
	TUTEUR("Tuteur"),
	PARENT("Parent"),
	AGENT("Agent");


	private String label;

	public static TypePersonne getValueOf(String str) {
        for (TypePersonne theme : TypePersonne.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private TypePersonne(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
