package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.LignePersonne;
import com.wouti.zoom_academia.entities.QLignePersonne;


public interface LignePersonneRepository extends JpaRepository<LignePersonne, Long>, QuerydslPredicateExecutor<LignePersonne>,QuerydslBinderCustomizer<QLignePersonne>{

	  @Override
		default public void customize(QuerydslBindings bindings, QLignePersonne root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from LignePersonne s where s.isDeleted = false and s.codeLignePersonne = ?1")
		public LignePersonne findByCodeLignePersonne(String codeLignePersonne);





	}

