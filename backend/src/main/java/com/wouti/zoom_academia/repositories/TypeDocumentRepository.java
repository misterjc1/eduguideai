package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QTypeDocument;
import com.wouti.zoom_academia.entities.TypeDocument;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long>, QuerydslPredicateExecutor<TypeDocument>,QuerydslBinderCustomizer<QTypeDocument>{

  @Override
	default public void customize(QuerydslBindings bindings, QTypeDocument root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from TypeDocument s where s.isDeleted = false and s.codeTypeDocument = ?1")
	public TypeDocument findByCodeTypeDocument(String codeTypeDocument);

	@Query("select c from TypeDocument c where c.isDeleted = false and c.typeSouscription.codeTypeSouscription = ?1")
	public List<TypeDocument> findByCodeTypeSouscription(String type);
}
