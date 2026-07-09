package com.wouti.zoom_academia.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.ProduitStructure;
import com.wouti.zoom_academia.repositories.ProduitStructureRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/produit-structure")
public class ProduitStructureController {

	private static final Logger log = Logger.getLogger(ProduitStructureController.class.getName());

	@Autowired
	public ProduitStructureRepository produitStructureRepository;


	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

	@PostMapping("/save")
	public Response<ProduitStructure> save(@RequestBody ProduitStructure produitStructure) {

		ProduitStructure result = null;
		try {
			produitStructure.setCodeProduitStructure(AppUtils.generateProduitStructureCode());
			result = produitStructureRepository.save(produitStructure);
			log.info("ProduitStructure enregistré avec success " + result.toString());

			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}

			return new Response<>(result, HttpStatus.OK, "ProduitStructure enregistré avec succes");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du ProduitStructure", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du ProduitStructure");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<ProduitStructure>> findAllQueryDsl(
			@QuerydslPredicate(root = ProduitStructure.class) com.querydsl.core.types.Predicate predicate) {

		Iterable<ProduitStructure> result = null;
		try {
			result = produitStructureRepository.findAll(predicate);
			log.info("ProduitStructures récupérés avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "ProduitStructures récupéré avec success");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des ProduitStructures", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED,
					"Echec lors de la récupération des ProduitStructures");
		}
	}

	@PostMapping("/update")
	public Response<ProduitStructure> update(@RequestBody ProduitStructure produitStructure) {

		ProduitStructure result = null;
		try {
			result = produitStructureRepository.saveAndFlush(produitStructure);
			log.info("ProduitStructure mise à jour avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "ProduitStructure mise a jour avec succes");
		} catch (Exception e) {
			log.error("Echec lors de la mise a jour du ProduitStructure", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du ProduitStructure");
		}
	}

	  @GetMapping("/findByStructure/{codeStructure}")
		public Response<List<ProduitStructure>> findByStructure(@PathVariable String codeStructure) {

		  List<ProduitStructure> result = null;
			try {
				result = produitStructureRepository.findProduitStructureByStructure(codeStructure);
				log.info("ProduitStructure récupéré avec success " + result.toString());
				return new Response<>(result, HttpStatus.OK, "ProduitStructure récupéré avec succes");
			} catch (Exception e) {

				log.error("Echec lors de la récupération du ProduitStructure ", e);
				return new Response<>(result, HttpStatus.NOT_MODIFIED,
						"Echec lors de la récupération du ProduitStructure");
			}
		}


		@GetMapping("/findByStructureAndType/{codeStructure}/{codeTypeCategorie}")
			public Response<List<ProduitStructure>> findByStructureAndType(@PathVariable String codeStructure,@PathVariable String codeTypeCategorie) {

			  List<ProduitStructure> result = null;
				try {
					result = produitStructureRepository.findByStructureAndType(codeStructure,codeTypeCategorie);
					//
					for (ProduitStructure produitStructure : result) {
						log.info("Produit" + produitStructure.getProduit().getImage().toString());
						if (produitStructure.getProduit().getImage() != null) {
							Path path = Paths.get(AppUtils.PRODUCT_LOGO_DIRECTORY + produitStructure.getProduit().getImage());
							byte[] data = Files.readAllBytes(path);
							Encoder encoder = Base64.getEncoder();
							produitStructure.getProduit().setImage(encoder.encodeToString(data));
						}
					}

					log.info("ProduitStructure récupérés avec success " + result.toString());
					return new Response<>(result, HttpStatus.OK, "Produit récupéré avec succes");
				} catch (Exception e) {

					log.error("Echec lors de la récupération du ProduitStructure ", e);
					return new Response<>(result, HttpStatus.NOT_MODIFIED,
							"Echec lors de la récupération du ProduitStructure");
				}
			}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo) {
		List<String> response;
		response = new ArrayList<>();

		if (deleteVo != null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {

					ProduitStructure produitStructure = produitStructureRepository.findByCodeProduitStructure(code);

					if (produitStructure != null) {
						produitStructure.setDeleted(true);
						produitStructure.setDeleteDate(deleteVo.getDeleteDate());
						produitStructure.setDeleterCode(deleteVo.getDeleterCode());

						produitStructureRepository.saveAndFlush(produitStructure);
						response.add(produitStructure.getCodeProduitStructure());
					}
				}
				log.info("ProduitStructures supprimé avec success " + response.toString());
				return new Response<>(response, HttpStatus.OK, "ProduitStructures supprimés avec succes");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des ProduitStructures", e);
				return new Response<>(response, HttpStatus.NOT_MODIFIED,
						"Echec lors de la suppression des ProduitStructures");
			}

		}
		return null;
	}

	@PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.PRODUCT_LOGO_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		} catch (Exception e) {
			return "error = " + e;
		}

	}

	@GetMapping("/logo")
	public Response<String> getLogo(@RequestParam String imageUrl) {
		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.PRODUCT_LOGO_DIRECTORY);
			if (result.contains("error")){

				return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation de l image");

			}else {

				return new Response<>(result,HttpStatus.OK, "Logo recuperer");
			}

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation  l image");

		}
	}

}
