package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum TypeService implements Serializable{

	ORIENTATION_PROFFESSIONNELLE("Orientation Professionnelle"),
	SIMULATION_EFFORTS("Simulation des efforts"),
	ANALYSE_PREDICTIVE("Analyse Predictive"),
	ASSISTANT_VIRTUEL("Assistant Virtuel"),
	DETECTION_FRAUDE("Detection de fraude");


	private String label;

	public static TypeService getValueOf(String str) {
        for (TypeService theme : TypeService.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private TypeService(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
