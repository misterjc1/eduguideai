package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Inscrit;
import com.wouti.zoom_academia.entities.Liaison;
import com.wouti.zoom_academia.entities.QLiaison;
import com.wouti.zoom_academia.entities.Utilisateur;
import com.wouti.zoom_academia.transverse.StatutLiaison;

public interface LiaisonRepository extends JpaRepository<Liaison, Long>, QuerydslPredicateExecutor<Liaison>,QuerydslBinderCustomizer<QLiaison>{
	@Override
	default public void customize(QuerydslBindings bindings, QLiaison root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from Liaison s where s.isDeleted = false and s.codeLiaison = ?1")
	public Liaison findByCodeLiaison(String codeLiaison);
	
	@Query("SELECT l.utilisateur FROM Liaison l WHERE l.inscrit.matricule = ?1")
	List<Utilisateur> findUtilisateursByMatriculeInscrit(String matricule);
	
	//@Query("SELECT l.inscrit FROM Liaison l WHERE l.utilisateur.codeUtilisateur = ?1")
	@Query("SELECT l.inscrit FROM Liaison l WHERE l.utilisateur.codeUtilisateur = ?1 AND l.statutLiaison = ?2")
	List<Inscrit> findLiaisonByCodeUtilisateur(String codeUtilisateur, StatutLiaison statutLiaison);



}