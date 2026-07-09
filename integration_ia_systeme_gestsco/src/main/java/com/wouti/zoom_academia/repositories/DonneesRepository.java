package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Donnees;
import com.wouti.zoom_academia.entities.QDonnees;

public interface DonneesRepository extends JpaRepository<Donnees, Long>, QuerydslPredicateExecutor<Donnees>,QuerydslBinderCustomizer<QDonnees>{

	  @Override
		default public void customize(QuerydslBindings bindings, QDonnees root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Donnees s where s.isDeleted = false and s.codeDonnee = ?1")
		public Donnees findByCodeDonnee(String codeDonnee);





	}

