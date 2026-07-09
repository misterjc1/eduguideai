package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Reponse;
import com.wouti.zoom_academia.entities.QReponse;

public interface ReponseRepository extends JpaRepository<Reponse, Long>, QuerydslPredicateExecutor<Reponse>,QuerydslBinderCustomizer<QReponse>{

	  @Override
		default public void customize(QuerydslBindings bindings, QReponse root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Reponse s where s.isDeleted = false and s.codeResp = ?1")
		public Reponse findByCodeResp(String codeResp);





	}

