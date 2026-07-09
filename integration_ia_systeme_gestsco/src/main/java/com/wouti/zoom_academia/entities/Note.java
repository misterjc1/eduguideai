package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "note", schema = "ia_schema")
public class Note extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNote;

    private String codeNote;

    /** Matricule dénormalisé pour les requêtes directes par matricule */
    private String matricule;

    private String semestre;

    private String module;

    private String libelle;

    private Double valeur;

    @ManyToOne
    @JoinColumn(name = "inscrit")
    private Inscrit inscrit;

    public Long getIdNote() { return idNote; }
    public void setIdNote(Long idNote) { this.idNote = idNote; }

    public String getCodeNote() { return codeNote; }
    public void setCodeNote(String codeNote) { this.codeNote = codeNote; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Double getValeur() { return valeur; }
    public void setValeur(Double valeur) { this.valeur = valeur; }

    public Inscrit getInscrit() { return inscrit; }
    public void setInscrit(Inscrit inscrit) { this.inscrit = inscrit; }
}
