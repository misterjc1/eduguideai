package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum Variables implements Serializable {

	VARIABLE1("typeUser"),
	VARIABLE2("filiere"),
	VARIABLE3("matiere"),
	VARIABLE4("notes"),
	VARIABLE5("nameMetier"),
	VARIABLE6("niveauAcces"),
	VARIABLE7("descriptionMetier"),
	VARIABLE8("statistique");



	private String label;

	public static Variables getValueOf(String str) {
        for (Variables theme : Variables.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private Variables(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}

