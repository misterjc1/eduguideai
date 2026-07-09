package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Utilisateur;
import com.wouti.zoom_academia.entities.QUtilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>, QuerydslPredicateExecutor<Utilisateur>,QuerydslBinderCustomizer<QUtilisateur>{

	  @Override
		default public void customize(QuerydslBindings bindings, QUtilisateur root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Utilisateur s where s.isDeleted = false and s.codeUtilisateur = ?1")
		public Utilisateur findByCodeUtilisateur(String codeUtilisateur);


		@Query("select s from Utilisateur s where s.isDeleted = false and s.username = ?1")
	    public Utilisateur findByUsername(String username);


	}

