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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.TypeDocument;
import com.wouti.zoom_academia.repositories.TypeDocumentRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/type-document")
public class TypeDocumentController {

	private static final Logger log = Logger.getLogger(TypeDocumentController.class.getName());

	@Autowired
	public TypeDocumentRepository typeDocumentRepository;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

	@PostMapping("/save")
	public Response<TypeDocument> save(@RequestBody TypeDocument typeDocument){

		TypeDocument result = null;
		try {
			typeDocument.setCodeTypeDocument(AppUtils.generateTypeDocumentCode());
			result = typeDocumentRepository.save(typeDocument);
			log.info("Type de document enregistré avec succès " + result.toString());

			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}


			return new Response<>(result,HttpStatus.OK, "Type de document enregistré avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du type de document", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du type de document");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<TypeDocument>> findAllQueryDsl(
	@QuerydslPredicate(root = TypeDocument.class) com.querydsl.core.types.Predicate predicate){

		Iterable<TypeDocument> result = null;
		try {
			result = typeDocumentRepository.findAll(predicate);
			log.info("Types de document récupérés avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Types de document récupérés avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des types de document", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des types de document");
		}
	}

	@PostMapping("/update")
	public Response<TypeDocument> update(@RequestBody TypeDocument typeDocument) {

		TypeDocument result = null;
		try {
			result = typeDocumentRepository.saveAndFlush(typeDocument);
			log.info("Type de document mis à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Type de document mis à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour du type de document", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du type de document");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {
					TypeDocument typeDocument = typeDocumentRepository.findByCodeTypeDocument(code);
					if (typeDocument!= null) {
						typeDocument.setDeleted(true);
						typeDocument.setDeleteDate(deleteVo.getDeleteDate());
						typeDocument.setDeleterCode(deleteVo.getDeleterCode());
						typeDocumentRepository.saveAndFlush(typeDocument);
						response.add(typeDocument.getCodeTypeDocument());
					}
				}
				log.info("Types de document supprimés avec succèss "+response.toString());
				return new Response<>(response,HttpStatus.OK, "Types de document supprimés avec succès");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des types de document", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des types de document");
			}
		}
		return null;
	}



	@GetMapping("/type-document-of-souscription/{type}")
	public Response<List<TypeDocument>> findTypeDocumentOfSouscription(@PathVariable String type) {

		List<TypeDocument> listOfTypeDocuments = new ArrayList<>();
			try {
				listOfTypeDocuments = typeDocumentRepository.findByCodeTypeSouscription(type);
				log.info("Types de documents récupérés avec success " + listOfTypeDocuments.toString());
				return new Response<>(listOfTypeDocuments,HttpStatus.OK, "Type de documents récupérées avec success");
			} catch (Exception e) {
				log.error("Echec lors de la récupération des types de documents", e);
				return new Response<>(listOfTypeDocuments,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des types de documents");
			}
	}
}
