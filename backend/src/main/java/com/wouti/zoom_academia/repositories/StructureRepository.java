package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QStructure;
import com.wouti.zoom_academia.entities.Structure;

public interface StructureRepository extends JpaRepository<Structure, Long>, QuerydslPredicateExecutor<Structure>,QuerydslBinderCustomizer<QStructure>{

  @Override
	default public void customize(QuerydslBindings bindings, QStructure root) {
		bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
	}

	@Query("select s from Structure s where s.isDeleted = false and s.codeStructure = ?1")
	public Structure findByCodeStructure(String codeStructure);



	@Query("select distinct ps.structure from ProduitStructure ps JOIN ps.produit p JOIN p.categorie ca JOIN ca.typeCategorie tca where ps.isDeleted = false   and tca.codeTypeCategorie = ?1")
	public List<Structure> findByType(String codeTypeCategorie);


}
