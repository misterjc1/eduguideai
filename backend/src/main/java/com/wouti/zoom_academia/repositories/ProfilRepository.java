package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Profil;
import com.wouti.zoom_academia.entities.QProfil;

public interface ProfilRepository extends JpaRepository<Profil, Long>, 
    QuerydslPredicateExecutor<Profil>, QuerydslBinderCustomizer<QProfil> {

    @Override
    default public void customize(QuerydslBindings bindings, QProfil root) {
        bindings.bind(String.class).first((StringPath path, String value) -> 
            path.containsIgnoreCase(value));
    }

    @Query("select p from Profil p where p.isDeleted = false and p.codeProfil = ?1")
    public Profil findByCodeProfil(String codeProfil);
    
    @Query("select p from Profil p where p.isDeleted = false and p.intitule = ?1")
    public Profil findByIntitule(String intitule);
}