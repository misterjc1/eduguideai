package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Prompt;
import com.wouti.zoom_academia.entities.QPrompt;


public interface PromptRepository extends JpaRepository<Prompt, Long>, QuerydslPredicateExecutor<Prompt>,QuerydslBinderCustomizer<QPrompt>{

	  @Override
		default public void customize(QuerydslBindings bindings, QPrompt root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Prompt s where s.isDeleted = false and s.codePrompt = ?1")
		public Prompt findByCodePrompt(String codePrompt);


		


	}
