package com.wouti.zoom_academia.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.wouti.zoom_academia.entities.TypeSouscription;
import com.wouti.zoom_academia.repositories.TypeSouscriptionRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/type-souscription")
public class TypeSouscriptionController {

    private static final Logger log = Logger.getLogger(TypeSouscriptionController.class.getName());

	@Autowired
	public TypeSouscriptionRepository typeSouscriptionRepository;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;


	@PostMapping("/save")
	public Response<TypeSouscription> save(@RequestBody TypeSouscription typeSouscription){

		TypeSouscription result = null;
		try {
			typeSouscription.setCodeTypeSouscription(AppUtils.generateTypeSouscriptionCode());
			result = typeSouscriptionRepository.save(typeSouscription);
			log.info("Type de souscription enregistré avec succès " + result.toString());


			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}



			return new Response<>(result,HttpStatus.OK, "Type de souscription enregistré avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du type de souscription", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du type de souscription");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<TypeSouscription>> findAllQueryDsl(
	@QuerydslPredicate(root = TypeSouscription.class) com.querydsl.core.types.Predicate predicate){

		Iterable<TypeSouscription> result = null;
		try {
			result = typeSouscriptionRepository.findAll(predicate);
			log.info("Types de souscription récupérés avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Types de souscription récupérés avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des types de souscription", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des types de souscription");
		}
	}

	@PostMapping("/update")
	public Response<TypeSouscription> update(@RequestBody TypeSouscription typeSouscription) {

		TypeSouscription result = null;
		try {
			result = typeSouscriptionRepository.saveAndFlush(typeSouscription);
			log.info("Type de souscription mis à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Type de souscription mis à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour du type de souscription", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du type de souscription");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {
					TypeSouscription typeSouscription = typeSouscriptionRepository.findByCodeTypeSouscription(code);
					if (typeSouscription!= null) {
						typeSouscription.setDeleted(true);
						typeSouscription.setDeleteDate(deleteVo.getDeleteDate());
						typeSouscription.setDeleterCode(deleteVo.getDeleterCode());
						typeSouscriptionRepository.saveAndFlush(typeSouscription);
						response.add(typeSouscription.getCodeTypeSouscription());
					}
				}
				log.info("Types de souscription supprimés avec succèss "+response.toString());
				return new Response<>(response,HttpStatus.OK, "Types de souscription supprimés avec succès");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des types de souscription", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des types de souscription");
			}
		}
		return null;
	}

//	@GetMapping("/structures/{typeSouscription}")
//	public Response<List<Structure>> findCategorieProducts(@PathVariable String typeSouscription) {
//
//		List<Structure> listOfStructure = new ArrayList<Structure>();
//			try {
//				listOfStructure = typeSouscriptionRepository.findTypeSouscriptionStructures(typeSouscription);
//				log.info("Stuctures récupérés avec succès " + listOfStructure.toString());
//				return new Response<List<Structure>>(listOfStructure,HttpStatus.OK, "Stuctures récupérées avec success");
//			} catch (Exception e) {
//				log.error("Echec lors de la récupération des Stuctures", e);
//				return new Response<List<Structure>>(listOfStructure,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Stuctures");
//			}
//	}
//
//	@GetMapping("/type-categories/{typeSouscription}")
//	public Response<List<TypeCategorie>> findTypeCategorie(@PathVariable String typeSouscription) {
//
//		List<TypeCategorie> listOfStructure = new ArrayList<TypeCategorie>();
//			try {
//				listOfStructure = typeSouscriptionRepository.findTypeSouscriptionTypeCategorie(typeSouscription);
//				log.info("TypeCategorie récupérés avec succès " + listOfStructure.toString());
//				return new Response<List<TypeCategorie>>(listOfStructure,HttpStatus.OK, "TypeCategorie récupérées avec success");
//			} catch (Exception e) {
//				log.error("Echec lors de la récupération des TypeCategorie", e);
//				return new Response<List<TypeCategorie>>(listOfStructure,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des TypeCategorie");
//			}
//	}

}
