package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QTypeCategorie;
import com.wouti.zoom_academia.entities.TypeCategorie;

public interface TypeCategorieRepository extends JpaRepository<TypeCategorie, Long>, QuerydslPredicateExecutor<TypeCategorie>,QuerydslBinderCustomizer<QTypeCategorie>{

  @Override
	default public void customize(QuerydslBindings bindings, QTypeCategorie root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from TypeCategorie s where s.isDeleted = false and s.codeTypeCategorie = ?1")
	public TypeCategorie findByCodeTypeCategorie(String codeTypeCategorie);
}
