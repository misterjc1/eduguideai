package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Niveau;
import com.wouti.zoom_academia.entities.QNiveau;

public interface NiveauRepository extends JpaRepository<Niveau, Long>, QuerydslPredicateExecutor<Niveau>,QuerydslBinderCustomizer<QNiveau>{
	@Override
	default public void customize(QuerydslBindings bindings, QNiveau root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from Niveau s where s.isDeleted = false and s.codeNiveau = ?1")
	public Niveau findByCodeNiveau(String codeNiveau);
}
