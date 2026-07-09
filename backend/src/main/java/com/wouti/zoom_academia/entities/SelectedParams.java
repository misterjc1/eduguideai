package com.wouti.zoom_academia.entities;

import java.util.List;


import java.util.List;

public class SelectedParams {
    private String niveau;
    private List<Note> notes;
    private List<String> objectifs;

    // Getters et setters
    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<String> getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(List<String> objectifs) {
        this.objectifs = objectifs;
    }

    // Sous-classe Niveau
    public static class Niveau {
        private String codeNiveau;
        private String libelle;
        private String filiere;

        // Getters et setters
        public String getCodeNiveau() {
            return codeNiveau;
        }

        public void setCodeNiveau(String codeNiveau) {
            this.codeNiveau = codeNiveau;
        }

        public String getLibelle() {
            return libelle;
        }

        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }

        public String getFiliere() {
            return filiere;
        }

        public void setFiliere(String filiere) {
            this.filiere = filiere;
        }
    }

    // Sous-classe Note
    public static class Note {
        private String module;
        private String valeur;

        // Getters et setters
        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getValeur() {
            return valeur;
        }

        public void setValeur(String valeur) {
            this.valeur = valeur;
        }
    }
}
