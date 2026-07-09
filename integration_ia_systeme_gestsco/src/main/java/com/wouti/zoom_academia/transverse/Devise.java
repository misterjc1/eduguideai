package com.wouti.zoom_academia.transverse;

public enum Devise {

    Franc_CFA("franc cfa"),
	Euro("euro"),
	Dollar("dollar");


	private String label;

	public static Devise convert(String str) {
        for (Devise devise : Devise.values()) {
            if (devise.toString().equalsIgnoreCase(str) ){
                return devise;
            }
        }
        return null;
    }

	private Devise(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
