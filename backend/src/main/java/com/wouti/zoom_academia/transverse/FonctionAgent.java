package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum FonctionAgent implements Serializable{

	DRIVER("Driver"),
	DELIVER("Deliver");



	private String label;

	public static FonctionAgent convert(String str) {
        for (FonctionAgent statut : FonctionAgent.values()) {
            if (statut.toString().equalsIgnoreCase(str) ){
                return statut;
            }
        }
        return null;
}

	private FonctionAgent(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
