package com.wouti.zoom_academia.transverse;

public enum StatutLiaison {
	//ACTIVE("Activé"),
		//DESACTIVE("Désactivé");
	
		VALIDE("Validé"),
		REJETE("Rejeté");

		private String label;

		public static StatutLiaison convert(String str) {
	        for (StatutLiaison statutLiaison : StatutLiaison.values()) {
	            if (statutLiaison.toString().equalsIgnoreCase(str) ){
	                return statutLiaison;
	            }
	        }
	        return null;
	}

		private StatutLiaison(String label) {
			this.setLabel(label);
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
}
