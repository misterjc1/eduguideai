package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wouti.zoom_academia.entities.Images;
import com.wouti.zoom_academia.entities.Produit;

public interface ImageRepository extends JpaRepository<Images, Long>{

    @Query("select e from Images e where e.produit = ?1")
	public List<Images> findByProduit(Produit produit);
}
