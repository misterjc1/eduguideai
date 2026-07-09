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
import com.querydsl.core.types.OrderSpecifier;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.QStructure;
import com.wouti.zoom_academia.entities.Structure;
import com.wouti.zoom_academia.repositories.StructureRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/structure")
public class StructureController {

	private static final Logger log = Logger.getLogger(StructureController.class.getName());

	@Autowired
	public StructureRepository structureRepository;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;



	QStructure qStructure = QStructure.structure;
	OrderSpecifier<Long> orderSpecifier = qStructure.idStructure.asc();

	@PostMapping("/save")
	public Response<Structure> save(@RequestBody Structure structure){



		Structure result = null;
		try {
			structure.setCodeStructure(AppUtils.generateStructureCode());
			if (structure.getLogo() != null) {
				if (structure.getLogo().length() > 0) {
					this.uploadImage(structure.getLogo(), structure.getCodeStructure());
					structure.setLogo(structure.getCodeStructure() + ".png");
				}
			}
			result = structureRepository.save(structure);

////			Creation du compte associe
//			Compte compte = new Compte();
//			compte.setTypeCompte(TypeCompte.COMPTE_COURANT_STRUCTURE);
//			compte.setOwnerCode(result.getCodeStructure());
//			compte.setCreationDate(structure.getCreationDate());
//			compte.setCreatorCode(structure.getCreatorCode());
//			compteController.save(compte);

//			Piste d'audit
			log.info("Structure enregistrée avec succès " + result.toString());
			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}

			return new Response<>(result,HttpStatus.OK, "Structure enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la structure", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la structure");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Structure>> findAllQueryDsl(
	@QuerydslPredicate(root = Structure.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Structure> result = null;
		try {
			result = structureRepository.findAll(predicate,new OrderSpecifier[]{orderSpecifier});
			log.info("Structures récupérées avec succès "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Structures récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des structures", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des structures");
		}
	}


	@GetMapping("/findByType/{codeTypeCategorie}")
		public Response<List<Structure>> findByType(@PathVariable String codeTypeCategorie) {

		  List<Structure> result = null;
			try {
				result = structureRepository.findByType(codeTypeCategorie);
				log.info("Structures récupérés avec success " + result.toString());
				return new Response<>(result, HttpStatus.OK, "Structures récupérés avec succes");
			} catch (Exception e) {

				log.error("Echec lors de la récupération des Structures ", e);
				return new Response<>(result, HttpStatus.NOT_MODIFIED,
						"Echec lors de la récupération des Structures");
			}
		}


	@PostMapping("/update")
	public Response<Structure> update(@RequestBody Structure structure) {

		Structure result = null;
		Path imagesPath = Paths.get(AppUtils.STRUCTURE_DIRECTORY + structure.getCodeStructure() + ".png");
		try {
			if (structure.getLogo()!=null && structure.getLogo().length() > 0) {
				if (structure.getLogo().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(structure.getLogo(), structure.getCodeStructure());
					structure.setLogo(structure.getCodeStructure() + ".png");
				}
			}
			result = structureRepository.saveAndFlush(structure);
			log.info("Structure mise à jour avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Structure mise à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour de la structure", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour de la structure");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {
					Structure structure = structureRepository.findByCodeStructure(code);
					if (structure!= null) {
						structure.setDeleted(true);
						structure.setDeleteDate(deleteVo.getDeleteDate());
						structure.setDeleterCode(deleteVo.getDeleterCode());
						structureRepository.saveAndFlush(structure);
						response.add(structure.getCodeStructure());
					}
				}
				String message = (deleteVo.getCodes().length > 1) ? "Structures supprimées avec succès" : "Structure supprimée avec succès";
				log.info(message+response.toString());
				return new Response<>(response,HttpStatus.OK, message);
			} catch (Exception e) {
				log.error("Echec lors de la suppression des structures", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des structures");
			}
		}
		return null;
	}


	@PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.STRUCTURE_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		} catch (Exception e) {
			return "error = " + e;
		}

	}

	@GetMapping("/logo")
	public Response<String> getLogo(@RequestParam String imageUrl) {
		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.STRUCTURE_DIRECTORY);
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
