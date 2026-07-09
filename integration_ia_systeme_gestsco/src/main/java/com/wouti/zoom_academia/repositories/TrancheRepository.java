package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QTranche;
import com.wouti.zoom_academia.entities.Tranche;


public interface TrancheRepository extends JpaRepository<Tranche, Long>, QuerydslPredicateExecutor<Tranche>,QuerydslBinderCustomizer<QTranche> {

	@Override
	default public void customize(QuerydslBindings bindings, QTranche root) {
		bindings.bind(String.class).first(
				(StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Override
	@Query("select t from Tranche t where t.isDeleted = false")
	public List<Tranche> findAll();

	@Query("select t from Tranche t where t.isDeleted = false and t.codeTranche = ?1")
	public Tranche findByCodeTranche(String codeTranche);

}
