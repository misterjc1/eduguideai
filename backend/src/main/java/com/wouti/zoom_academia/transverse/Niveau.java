package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum Niveau implements Serializable {

	NIVEAU1("Niveau 1"),
	NIVEAU2("Niveau 2"),
	NIVEAU3("Niveau 3");


	private String label;

	public static Niveau getValueOf(String str) {
        for (Niveau theme : Niveau.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private Niveau(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
