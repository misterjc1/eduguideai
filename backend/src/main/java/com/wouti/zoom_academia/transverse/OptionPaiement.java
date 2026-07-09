package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum OptionPaiement implements Serializable {


	CASH("Cash"),
	MOBILE_MONEY("Mobile Money"),
	CREDITS_CARD("Carte de crédits"),
	LIBRE("Libre"),
	JOURNALIER("Journalier"),
	HEBDOMADAIRE("Hebdomadaire"),
	MENSUELLE("Mensuelle"),
	BIMENSUELLE("Bimensuelle"),
	TRIMESTRIELLE("Trismestrielle");

	private String label;


	public static OptionPaiement getValueOf(String str) {
        for (OptionPaiement theme : OptionPaiement.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private OptionPaiement(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
