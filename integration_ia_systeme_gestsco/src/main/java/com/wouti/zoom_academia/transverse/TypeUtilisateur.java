package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypeUtilisateur implements Serializable{

    TUTEUR("TUTEUR"),
    AGENT("AGENT"),
	INSCRIT("INSCRIT");


	private String label;

	public static TypeUtilisateur convert(String str) {
        for (TypeUtilisateur statut : TypeUtilisateur.values()) {
            if (statut.toString().equalsIgnoreCase(str) ){
                return statut;
            }
        }
        return null;
    }

	private TypeUtilisateur(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

