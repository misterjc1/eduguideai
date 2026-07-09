package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Categorie;
import com.wouti.zoom_academia.entities.Cinematique;
import com.wouti.zoom_academia.entities.QCinematique;
import com.wouti.zoom_academia.transverse.Niveau;

public interface CinematiqueRepository extends JpaRepository<Cinematique, Long>, QuerydslPredicateExecutor<Cinematique>,QuerydslBinderCustomizer<QCinematique>{

	  @Override
		default public void customize(QuerydslBindings bindings, QCinematique root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Cinematique s where s.isDeleted = false and s.codeCinematique = ?1")
		public Cinematique findByCodeCinematique(String codeCinematique);


		@Query("select s from Cinematique s Join s.cinematiqueMere cm where s.isDeleted = false and cm.codeCinematique = ?1")
		public List<Cinematique> findCinematiqueMere(String codeCinematique);
		
		@Query("select c from Cinematique c where c.isDeleted = false and c.typeCinematique.codeTypeCinematique = ?1")
		public List<Cinematique> findByCodeTypeCinematique(String type);
		

//		@Query("select distinct ps.produit.categorie from ProduitStructure ps JOIN ps.structure s JOIN ps.produit p JOIN p.categorie ca JOIN ca.typeCategorie tca where ps.isDeleted = false and ca.isDeleted = false and s.codeStructure= ?1  and tca.codeTypeCategorie = ?2 ")
//		public List<Categorie> findByStructureAndType(String codeStructure,String codeTypeCategorie);
		
		@Query("select s from Cinematique s JOIN s.typeCinematique cm where s.isDeleted = false and cm.isDeleted = false and cm.niveau=?1")
		public List<Cinematique> findByNiveau(Niveau niveau);

	}
