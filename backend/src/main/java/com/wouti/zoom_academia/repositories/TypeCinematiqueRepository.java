package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.TypeCinematique;
import com.wouti.zoom_academia.entities.QTypeCinematique;


public interface TypeCinematiqueRepository extends JpaRepository<TypeCinematique, Long>, QuerydslPredicateExecutor<TypeCinematique>,QuerydslBinderCustomizer<QTypeCinematique>{

	  @Override
		default public void customize(QuerydslBindings bindings, QTypeCinematique root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from TypeCinematique s where s.isDeleted = false and s.codeTypeCinematique = ?1")
		public TypeCinematique findByCodeTypeCinematique(String codeTypeCinematique);





	}

