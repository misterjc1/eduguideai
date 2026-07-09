package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Produit;
import com.wouti.zoom_academia.entities.QProduit;

public interface ProduitRepository extends JpaRepository<Produit, Long>, QuerydslPredicateExecutor<Produit>,QuerydslBinderCustomizer<QProduit> {

	@Override
	default public void customize(QuerydslBindings bindings, QProduit root) {
		bindings.bind(String.class).first(
				(StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Override
	@Query("select p from Produit p where p.isDeleted = false")
	public List<Produit> findAll();

	@Query("select p from Produit p where p.isDeleted = false and p.codeProduit = ?1")
	public Produit findByCodeProduit(String codeProduit);

	@Query("select distinct ps.produit from ProduitStructure ps  JOIN ps.structure s JOIN ps.produit p JOIN p.categorie ca JOIN ca.typeCategorie tca where ps.isDeleted = false and s.codeStructure= ?1  and tca.codeTypeCategorie = ?2")
	public List<Produit> findByStructureAndType(String codeStructure, String codeTypeCategorie);


}
