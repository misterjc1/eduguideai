package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.ProduitStructure;
import com.wouti.zoom_academia.entities.QProduitStructure;

public interface ProduitStructureRepository extends JpaRepository<ProduitStructure, Long>, QuerydslPredicateExecutor<ProduitStructure>,QuerydslBinderCustomizer<QProduitStructure> {

	@Override
	default public void customize(QuerydslBindings bindings, QProduitStructure root) {
		bindings.bind(String.class).first(
				(StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Override
	@Query("select p from ProduitStructure p where p.isDeleted = false")
	public List<ProduitStructure> findAll();

	@Query("select p from ProduitStructure p where p.isDeleted = false and p.codeProduitStructure = ?1")
	public ProduitStructure findByCodeProduitStructure(String codeProduitStructure);


	@Query("select p from ProduitStructure p JOIN p.structure s where p.isDeleted = false  and s.codeStructure = ?1")
	public List<ProduitStructure> findProduitStructureByStructure(String codeStructure);


	@Query("select ps from ProduitStructure ps JOIN ps.structure s JOIN ps.produit p JOIN p.categorie ca where ps.isDeleted = false and s.codeStructure= ?1  and ca.codeCategorie = ?2")
	public List<ProduitStructure> findByStructureAndType(String codeStructure,String codeTypeCategorie);


}
