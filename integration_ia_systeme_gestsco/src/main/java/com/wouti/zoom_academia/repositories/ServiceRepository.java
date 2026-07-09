package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QService;
import com.wouti.zoom_academia.entities.Service;



public interface ServiceRepository extends JpaRepository<Service, Long>, QuerydslPredicateExecutor<Service>,QuerydslBinderCustomizer<QService>{

	  @Override
		default public void customize(QuerydslBindings bindings, QService root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Service s where s.isDeleted = false and s.codeService = ?1")
		public Service findByCodeService(String codeService);


		


	}

