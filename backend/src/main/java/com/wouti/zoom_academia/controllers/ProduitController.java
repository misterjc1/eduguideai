package com.wouti.zoom_academia.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
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
import com.wouti.zoom_academia.entities.Produit;
import com.wouti.zoom_academia.repositories.ProduitRepository;
import com.wouti.zoom_academia.repositories.TrancheRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/produit")
public class ProduitController {

	private static final Logger log = Logger.getLogger(ProduitController.class.getName());

	@Autowired
	public ProduitRepository produitRepository;

	@Autowired
	public TrancheRepository trancheRepository;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

	@PostMapping("/save")
	public Response<Produit> saveA(@RequestBody Produit produit) {

		Produit result = null;
		try {
			produit.setCodeProduit(AppUtils.generateProduitCode());
			if (produit.getImage() != null) {
				if (produit.getImage().length() > 0) {
					this.uploadImage(produit.getImage(), produit.getCodeProduit());
					produit.setImage(produit.getCodeProduit() + ".png");
				}
			}
			result = produitRepository.save(produit);
			log.info("Produit enregistré avec success " + result.toString());

			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}

			return new Response<>(result, HttpStatus.OK, "Produit enregistré avec succes");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du Produit", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du Produit");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Produit>> findAllQueryDsl(
			@QuerydslPredicate(root = Produit.class) com.querydsl.core.types.Predicate predicate) {

		Iterable<Produit> result = null;
		try {
			result = produitRepository.findAll(predicate);
			log.info("Produits récupérés avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "Produits récupéré avec success");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Produits", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED,
					"Echec lors de la récupération des Produits");
		}
	}

	@GetMapping("/findByStructureAndType/{codeStructure}/{codeTypeCategorie}")
		public Response<List<Produit>> findByClientAndType(@PathVariable String codeStructure,@PathVariable String codeTypeCategorie) {

		  List<Produit> result = null;
			try {
				result = produitRepository.findByStructureAndType(codeStructure,codeTypeCategorie);
				log.info("Produits récupérés avec success " + result.toString());
				return new Response<>(result, HttpStatus.OK, "Produit récupéré avec succes");
			} catch (Exception e) {

				log.error("Echec lors de la récupération du Produit ", e);
				return new Response<>(result, HttpStatus.NOT_MODIFIED,
						"Echec lors de la récupération du Produit");
			}
		}


	@PostMapping("/update")
	public Response<Produit> update(@RequestBody Produit produit) {

		Produit result = null;
		Path imagesPath = Paths.get(AppUtils.PRODUCT_LOGO_DIRECTORY + produit.getCodeProduit() + ".png");
		try {

			if (produit.getImage()!=null && produit.getImage().length() > 0) {
				if (produit.getImage().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(produit.getImage(), produit.getCodeProduit());
					produit.setImage(produit.getCodeProduit() + ".png");
				}
			}
			result = produitRepository.saveAndFlush(produit);

			log.info("Produit mise à jour avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "Produit mise a jour avec succes");
		} catch (Exception e) {
			log.error("Echec lors de la mise a jour du Produit", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du Produit");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo) {
		List<String> response;
		response = new ArrayList<>();

		if (deleteVo != null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {

					Produit produit = produitRepository.findByCodeProduit(code);

					if (produit != null) {
						produit.setDeleted(true);
						produit.setDeleteDate(deleteVo.getDeleteDate());
						produit.setDeleterCode(deleteVo.getDeleterCode());

						produitRepository.saveAndFlush(produit);
						response.add(produit.getCodeProduit());
					}
				}
				log.info("Produits supprimé avec success " + response.toString());
				return new Response<>(response, HttpStatus.OK, "Produits supprimés avec succes");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des Produits", e);
				return new Response<>(response, HttpStatus.NOT_MODIFIED,
						"Echec lors de la suppression des Produits");
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

				return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation du logo");

			}else {

				return new Response<>(result,HttpStatus.OK, "Logo recuperer");
			}

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation du logo");

		}
	}

}
