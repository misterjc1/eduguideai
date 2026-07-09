package com.wouti.zoom_academia.entities;

import jakarta.persistence.*;


import com.wouti.zoom_academia.transverse.Statut;
//import com.wouti.zoom_academia.transverse.StatutLiaison;
import com.wouti.zoom_academia.transverse.StatutLiaison;

@Entity
public class Liaison extends AuditModel{

		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long idLiaison;
		private String codeLiaison;
		private String motif;
		private StatutLiaison statutLiaison;
		private String commentaire;
		
		@ManyToOne
		@JoinColumn(name="UTILISATEUR")
		private Utilisateur utilisateur;

		
		@ManyToOne
		@JoinColumn(name="INSCRIT")
		private Inscrit inscrit;


		public Long getIdLiaison() {
			return idLiaison;
		}


		public void setIdLiaison(Long idLiaison) {
			this.idLiaison = idLiaison;
		}


		public String getCodeLiaison() {
			return codeLiaison;
		}


		public void setCodeLiaison(String codeLiaison) {
			this.codeLiaison = codeLiaison;
		}


		public String getMotif() {
			return motif;
		}


		public void setMotif(String motif) {
			this.motif = motif;
		}


		public StatutLiaison getStatutLiaison() {
			return statutLiaison;
		}


		public void setStatutLiaison(StatutLiaison statutLiaison) {
			this.statutLiaison = statutLiaison;
		}


		public String getCommentaire() {
			return commentaire;
		}


		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}


		public Utilisateur getUtilisateur() {
			return utilisateur;
		}


		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}


		public Inscrit getInscrit() {
			return inscrit;
		}


		public void setInscrit(Inscrit inscrit) {
			this.inscrit = inscrit;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		
		
	
}
