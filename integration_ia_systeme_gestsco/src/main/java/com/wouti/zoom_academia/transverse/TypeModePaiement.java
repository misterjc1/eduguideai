package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypeModePaiement implements Serializable{

    USSD("USSD"),
	API("API");


	private String label;

	public static TypeModePaiement convert(String str) {
        for (TypeModePaiement statut : TypeModePaiement.values()) {
            if (statut.toString().equalsIgnoreCase(str) ){
                return statut;
            }
        }
        return null;
    }

	private TypeModePaiement(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

