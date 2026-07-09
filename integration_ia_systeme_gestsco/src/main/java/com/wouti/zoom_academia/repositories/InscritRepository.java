package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Inscrit;
import com.wouti.zoom_academia.entities.QInscrit;

public interface InscritRepository extends JpaRepository<Inscrit, Long>, QuerydslPredicateExecutor<Inscrit>,QuerydslBinderCustomizer<QInscrit>{
	@Override
	default public void customize(QuerydslBindings bindings, QInscrit root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from Inscrit s where s.isDeleted = false and s.codeInscrit = ?1")
	public Inscrit findByCodeInscrit(String codeInscrit);
	
	@Query("select s from Inscrit s where s.isDeleted = false and s.matricule = ?1")
	public Inscrit findByMatricule(String matricule);
}
