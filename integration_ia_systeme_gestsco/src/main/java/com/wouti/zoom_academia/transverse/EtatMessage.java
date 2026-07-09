package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum EtatMessage implements Serializable {

	SUCCES("Succes"),
	ANNULE("Annule"),
	ECHEC("Echec"),
	EN_ATTENTE("En attente");

	private String label;

	public static EtatMessage getValueOf(String str) {
        for (EtatMessage theme : EtatMessage.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private EtatMessage(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
