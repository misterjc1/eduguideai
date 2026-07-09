package com.wouti.zoom_academia.transverse;

import java.io.Serializable;

public enum LignePromptEnum implements Serializable {

	PROMPT1("Prompt 1"),
	PROMPT2("Prompt 2"),
	PROMPT3("Prompt 3");


	private String label;

	public static LignePromptEnum getValueOf(String str) {
        for (LignePromptEnum theme : LignePromptEnum.values()) {
            if (theme.getLabel().equals(str)) {
                return theme;
            }
        }
        return null;
    }


	private LignePromptEnum(String label) {
		this.setLabel(label);
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
