package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypeMessage implements Serializable {

	CONFIRMATION_PAIEMENT("Confirmation_Paiement"),
	RAPPEL_PAIEMENT("Rappel_Paiement");

	private String label;
	public static TypeMessage getValueOf(String str) {
        for (TypeMessage theme : TypeMessage.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }

	private TypeMessage(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
