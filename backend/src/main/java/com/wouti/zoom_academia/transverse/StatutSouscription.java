package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum StatutSouscription implements Serializable {


	NON_DEBUTE("Non Debute"),
	EN_COURS("En cours"),
	SUSPENDU("Suspendu"),
	ANNULE("Annulé"),
	TERMINE("Terminé"),
	REFUSE("Refusé");

	private String label;

	public static StatutSouscription getValueOf(String str) {
        for (StatutSouscription theme : StatutSouscription.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private StatutSouscription(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
