package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.TemplatePrompt;
import com.wouti.zoom_academia.entities.QTemplatePrompt;


public interface TemplatePromptRepository extends JpaRepository<TemplatePrompt, Long>, QuerydslPredicateExecutor<TemplatePrompt>,QuerydslBinderCustomizer<QTemplatePrompt>{

	  @Override
		default public void customize(QuerydslBindings bindings, QTemplatePrompt root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from TemplatePrompt s where s.isDeleted = false and s.codeTemplatePrompt = ?1")
		public TemplatePrompt findByCodeTemplatePrompt(String codeTemplatePrompt);





	}

