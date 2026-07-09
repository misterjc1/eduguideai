package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.ModePayement;
import com.wouti.zoom_academia.entities.QModePayement;


public interface ModePayementRepository extends JpaRepository<ModePayement, Long>, QuerydslPredicateExecutor<ModePayement>,QuerydslBinderCustomizer<QModePayement> {

	@Override
	 default public void customize(QuerydslBindings bindings, QModePayement root) {
			bindings.bind(String.class).first(
					(StringPath path, String value) -> path.containsIgnoreCase(value));
		}

//	  @Query("select c from ModePayement c where c.isDeleted = false and c.codeModePayement = ?1")
//	  public ModePayement findByCodeModePayement(String codeModePayement);
//
//	  @Query("select s from Transaction s where s.isDeleted = false and s.modePayement.codeModePayement = ?1")
//	  public List<Transaction> findModePayementTransactions(String modePayement);

}

