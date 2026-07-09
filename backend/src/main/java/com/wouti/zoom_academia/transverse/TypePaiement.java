package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypePaiement implements Serializable {

	PAIEMENT("Paiement"),
	RECHARGE("Recharge"),
	RETRAIT("Retrait");


	private String label;

	public static TypePaiement getValueOf(String str) {
        for (TypePaiement theme : TypePaiement.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private TypePaiement(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
