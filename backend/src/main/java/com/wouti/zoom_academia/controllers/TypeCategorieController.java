package com.wouti.zoom_academia.controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.TypeCategorie;
import com.wouti.zoom_academia.repositories.TypeCategorieRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/type-categorie")
public class TypeCategorieController {

	private static final Logger log = Logger.getLogger(TypeCategorieController.class.getName());



	@Autowired
	public TypeCategorieRepository typeCategorieRepository;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

	@PostMapping("/save")
	public Response<TypeCategorie> save(@RequestBody TypeCategorie typeCategorie){

		TypeCategorie result = null;
		try {

			typeCategorie.setCodeTypeCategorie(AppUtils.generateTypeCategorieCode());
			result = typeCategorieRepository.save(typeCategorie);
			log.info("Type de catégorie enregistré avec succès " + result.toString());

			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}


			return new Response<>(result,HttpStatus.OK, "Type de catégorie enregistré avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du type de catégorie", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du type de catégorie");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<TypeCategorie>> findAllQueryDsl(
	@QuerydslPredicate(root = TypeCategorie.class) com.querydsl.core.types.Predicate predicate){

		Iterable<TypeCategorie> result = null;
		try {
			result = typeCategorieRepository.findAll(predicate,Sort.by(Sort.Direction.ASC, "idTypeCategorie"));
			log.info("Types de catégorie récupérés avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Types de catégorie récupérés avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des types de catégorie", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des types de catégorie");
		}
	}

	@PostMapping("/update")
	public Response<TypeCategorie> update(@RequestBody TypeCategorie typeCategorie) {

		TypeCategorie result = null;
		try {
			result = typeCategorieRepository.saveAndFlush(typeCategorie);
			log.info("Type de catégorie mis à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Type de catégorie mis à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour du type de catégorie", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du type de catégorie");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {
					TypeCategorie typeCategorie = typeCategorieRepository.findByCodeTypeCategorie(code);
					if (typeCategorie!= null) {
						typeCategorie.setDeleted(true);
						typeCategorie.setDeleteDate(deleteVo.getDeleteDate());
						typeCategorie.setDeleterCode(deleteVo.getDeleterCode());
						typeCategorieRepository.saveAndFlush(typeCategorie);
						response.add(typeCategorie.getCodeTypeCategorie());
					}
				}
				log.info("Types de catégorie supprimés avec succèss "+response.toString());
				return new Response<>(response,HttpStatus.OK, "Types de catégorie supprimés avec succès");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des types de catégorie", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des types de catégorie");
			}
		}
		return null;
	}

//	@GetMapping("/categories/{type}")
//	public Response<List<Categorie>> findCategorieProducts(@PathVariable String type) {
//
//		List<Categorie> listOfCategories = new ArrayList<Categorie>();
//			try {
//				listOfCategories = typeCategorieRepository.findCategorie(type);
//				log.info("Catégories récupérés avec success " + listOfCategories.toString());
//				return new Response<List<Categorie>>(listOfCategories,HttpStatus.OK, "Catégories récupérées avec success");
//			} catch (Exception e) {
//				log.error("Echec lors de la récupération des catégories", e);
//				return new Response<List<Categorie>>(listOfCategories,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des catégories");
//			}
//	}
//
//	@GetMapping("/categories/sans-mere/{type}")
//	public Response<List<Categorie>> findCategorieSansMereProducts(@PathVariable String type) {
//
//		List<Categorie> listOfCategories = new ArrayList<Categorie>();
//			try {
//				listOfCategories = typeCategorieRepository.findCategorieSansMere(type, "aucun");
//				log.info("Catégories récupérés avec success " + listOfCategories.toString());
//				return new Response<List<Categorie>>(listOfCategories,HttpStatus.OK, "Catégories récupérées avec success");
//			} catch (Exception e) {
//				log.error("Echec lors de la récupération des catégories", e);
//				return new Response<List<Categorie>>(listOfCategories,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des catégories");
//			}
//	}
}
