package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QTransaction;
import com.wouti.zoom_academia.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, QuerydslPredicateExecutor<Transaction>,QuerydslBinderCustomizer<QTransaction>{


	 @Override
	 default public void customize(QuerydslBindings bindings, QTransaction root) {
			bindings.bind(String.class).first(
					(StringPath path, String value) -> path.containsIgnoreCase(value));

	  }

		@Query("select t from Transaction t where t.isDeleted = false and t.codeTransaction = ?1")
		public Transaction findByCodeTransaction(String codeTransaction);

		
		@Query("select t from Transaction t where t.isDeleted = false and t.typePaiement = 1 and numeroCompte = ?1")
		public List<Transaction> findRechargeByAgent(String numeroCompte);
		
		@Query("select t from Transaction t where t.isDeleted = false and t.typePaiement = 2 and numeroCompte = ?1")
		public List<Transaction> findRetraitByAgent(String numeroCompte);








}
