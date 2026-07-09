package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Parametre;
import com.wouti.zoom_academia.entities.QParametre;



public interface ParametreRepository extends JpaRepository<Parametre, Long>, QuerydslPredicateExecutor<Parametre>,QuerydslBinderCustomizer<QParametre>{

	  @Override
		default public void customize(QuerydslBindings bindings, QParametre root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Parametre s where s.isDeleted = false and s.codeParametre = ?1")
		public Parametre findByCodeParametre(String codeParametre);


		


	}

