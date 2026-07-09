package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Categorie;
import com.wouti.zoom_academia.entities.QCategorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>, QuerydslPredicateExecutor<Categorie>,QuerydslBinderCustomizer<QCategorie>{

  @Override
	default public void customize(QuerydslBindings bindings, QCategorie root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from Categorie s where s.isDeleted = false and s.codeCategorie = ?1")
	public Categorie findByCodeCategorie(String codeCategorie);


	@Query("select s from Categorie s Join s.categorieMere cm where s.isDeleted = false and cm.codeCategorie = ?1")
	public List<Categorie> findCategorieMere(String codeCategorie);


	@Query("select distinct ps.produit.categorie from ProduitStructure ps JOIN ps.structure s JOIN ps.produit p JOIN p.categorie ca JOIN ca.typeCategorie tca where ps.isDeleted = false and ca.isDeleted = false and s.codeStructure= ?1  and tca.codeTypeCategorie = ?2 ")
	public List<Categorie> findByStructureAndType(String codeStructure,String codeTypeCategorie);
}