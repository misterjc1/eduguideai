package com.wouti.zoom_academia.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.entities.Cinematique;
import com.wouti.zoom_academia.repositories.CinematiqueRepository;
import com.wouti.zoom_academia.transverse.Niveau;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/cinematique")
public class CinematiqueController {

	private static final Logger log = Logger.getLogger(CinematiqueController.class.getName());

	@Autowired
	public CinematiqueRepository cinematiqueRepository;
	
	// Injection de PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;


	@PostMapping("/save")
	public Response<Cinematique> save(@RequestBody Cinematique cinematique){

		Cinematique result = null;
		try {

			cinematique.setCodeCinematique(AppUtils.generateCinematiqueCode());
			if (cinematique.getImage() != null) {
				if (cinematique.getImage().length() > 0) {
					this.uploadImage(cinematique.getImage(), cinematique.getCodeCinematique());
					cinematique.setImage(cinematique.getCodeCinematique() + ".png");
				}
			}
			result = cinematiqueRepository.save(cinematique);
			log.info("Cinematique enregistrée avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Cinematique enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la cinematique", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la cinematique");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Cinematique>> findAllQueryDsl(
	@QuerydslPredicate(root = Cinematique.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Cinematique> result = null;
		try {
			result = cinematiqueRepository.findAll(predicate);
			log.info("Cinematiques récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Cinematiques récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des cinematiques", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des cinematiques");
		}
	}

	@PostMapping("/update")
	public Response<Cinematique> update(@RequestBody Cinematique cinematique) {

		Cinematique result = null;
		Path imagesPath = Paths.get(AppUtils.CNMT_LOGO_DIRECTORY + cinematique.getCodeCinematique() + ".png");
		try {
			if (cinematique.getImage()!=null && cinematique.getImage().length() > 0) {
				if (cinematique.getImage().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(cinematique.getImage(), cinematique.getCodeCinematique());
					cinematique.setImage(cinematique.getCodeCinematique() + ".png");
				}
			}
			result = cinematiqueRepository.saveAndFlush(cinematique);
			log.info("Cinematique mise à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Cinematique mise à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour de la cinematique", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour de la cinematique");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {
					Cinematique cinematique = cinematiqueRepository.findByCodeCinematique(code);
					if (cinematique!= null) {
						cinematique.setDeleted(true);
						cinematique.setDeleteDate(deleteVo.getDeleteDate());
						cinematique.setDeleterCode(deleteVo.getDeleterCode());
						cinematiqueRepository.saveAndFlush(cinematique);
						response.add(cinematique.getCodeCinematique());
					}
				}
				log.info("Cinematiques supprimés avec succèss "+response.toString());
				return new Response<>(response,HttpStatus.OK, "Cinematique supprimées avec succès");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des cinematique", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des cinematique");
			}
		}
		return null;
	}

	@GetMapping("/cinematiques-fille/{codeCinematique}")
	public Response<List<Cinematique>> findCinematiqueFille(@PathVariable String codeCinematique) {

		List<Cinematique> listOfCinematiques = new ArrayList<>();
			try {
				listOfCinematiques = cinematiqueRepository.findCinematiqueMere(codeCinematique);
				log.info("Cinematiques récupérés avec success " + listOfCinematiques.toString());
				return new Response<>(listOfCinematiques,HttpStatus.OK, "Cinematiques récupérées avec success");
			} catch (Exception e) {
				log.error("Echec lors de la récupération des Cinematiques", e);
				return new Response<>(listOfCinematiques,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Cinematiques");
			}
	}
	
	@GetMapping("/findByNiveau/{niveau}")
	public Response<List<Cinematique>> findByNiveau(@PathVariable Niveau niveau) {

		List<Cinematique> listOfCinematiques = null;
			try {
				listOfCinematiques = cinematiqueRepository.findByNiveau(niveau);
				log.info("Cinematiques récupérés avec success " + listOfCinematiques.toString());
				return new Response<>(listOfCinematiques,HttpStatus.OK, "Cinematiques récupérées avec success");
			} catch (Exception e) {
				log.error("Echec lors de la récupération des Cinematiques", e);
				return new Response<>(listOfCinematiques,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Cinematiques");
			}
	}

	

	@PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.CNMT_LOGO_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		} catch (Exception e) {
			return "error = " + e;
		}

	}

	@GetMapping("/image")
	public Response<String> getImage(@RequestParam String imageUrl) {
		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.CNMT_LOGO_DIRECTORY);
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
