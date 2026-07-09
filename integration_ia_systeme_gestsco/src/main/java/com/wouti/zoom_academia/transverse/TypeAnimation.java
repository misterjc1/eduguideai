package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypeAnimation implements Serializable {

	PUBLICITE("Publicite"),
	LOGO("Logo"),
	ICONEMENU("Icone Menu");

	private String label;

	public static Sexe getValueOf(String str) {
        for (Sexe theme : Sexe.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private TypeAnimation(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
