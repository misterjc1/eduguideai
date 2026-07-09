package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.LignePrompt;
import com.wouti.zoom_academia.entities.QLignePrompt;

public interface LignePromptRepository extends JpaRepository<LignePrompt, Long>, QuerydslPredicateExecutor<LignePrompt>,QuerydslBinderCustomizer<QLignePrompt>{

	  @Override
		default public void customize(QuerydslBindings bindings, QLignePrompt root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from LignePrompt s where s.isDeleted = false and s.codeLignePrompt = ?1")
		public LignePrompt findByCodeLignePrompt(String codeLignePrompt);


		


	}