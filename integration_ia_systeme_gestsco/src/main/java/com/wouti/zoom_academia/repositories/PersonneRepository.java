package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Personne;
import com.wouti.zoom_academia.entities.QPersonne;


public interface PersonneRepository extends JpaRepository<Personne, Long>, QuerydslPredicateExecutor<Personne>,QuerydslBinderCustomizer<QPersonne>{

	  @Override
		default public void customize(QuerydslBindings bindings, QPersonne root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Personne s where s.isDeleted = false and s.codePersonne = ?1")
		public Personne findByCodePersonne(String codePersonne);





	}

