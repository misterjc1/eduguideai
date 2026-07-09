package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Client;
import com.wouti.zoom_academia.entities.QClient;

/**
 * ClientRepository
 */
public interface ClientRepository extends JpaRepository<Client, Long>, QuerydslPredicateExecutor<Client>,QuerydslBinderCustomizer<QClient>{

    @Override
	default public void customize(QuerydslBindings bindings, QClient root) {
		bindings.bind(String.class).first(
				(StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Query("select c from Client c where c.isDeleted = false and c.codeClient = ?1")
	public Client findByCodeClient(String codeClient);

	@Query("select c from Client c where c.isDeleted = false and c.telephone = ?1")
	public Client findClientByTelephone(String telephone);



    public Boolean existsByTelephone(String telephone);
    public Boolean existsByCodeAssocie(String codeAssocie);

}