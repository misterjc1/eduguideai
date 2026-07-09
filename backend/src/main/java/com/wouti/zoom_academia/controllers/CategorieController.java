package com.wouti.zoom_academia.controllers;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.entities.Categorie;
import com.wouti.zoom_academia.repositories.CategorieRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;



@RestController
@RequestMapping("/categorie")
public class CategorieController {

	private static final Logger log = Logger.getLogger(CategorieController.class.getName());

	@Autowired
	public CategorieRepository categorieRepository;


	@PostMapping("/save")
	public Response<Categorie> save(@RequestBody Categorie categorie){

		Categorie result = null;
		try {

			categorie.setCodeCategorie(AppUtils.generateCategorieCode());
			if (categorie.getImage() != null) {
				if (categorie.getImage().length() > 0) {
					this.uploadImage(categorie.getImage(), categorie.getCodeCategorie());
					categorie.setImage(categorie.getCodeCategorie() + ".png");
				}
			}
			result = categorieRepository.save(categorie);
			log.info("Catégorie enregistrée avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Catégorie enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la catégorie", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la catégorie");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Categorie>> findAllQueryDsl(
	@QuerydslPredicate(root = Categorie.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Categorie> result = null;
		try {
			result = categorieRepository.findAll(predicate);
			log.info("Catégories récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Catégories récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des catégories", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des catégories");
		}
	}

	@PostMapping("/update")
	public Response<Categorie> update(@RequestBody Categorie categorie) {

		Categorie result = null;
		Path imagesPath = Paths.get(AppUtils.CATEGORIE_LOGO_DIRECTORY + categorie.getCodeCategorie() + ".png");
		try {
			if (categorie.getImage()!=null && categorie.getImage().length() > 0) {
				if (categorie.getImage().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(categorie.getImage(), categorie.getCodeCategorie());
					categorie.setImage(categorie.getCodeCategorie() + ".png");
				}
			}
			result = categorieRepository.saveAndFlush(categorie);
			log.info("Catégorie mise à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Catégorie mise à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour de la catégorie", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour de la catégorie");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {
					Categorie categorie = categorieRepository.findByCodeCategorie(code);
					if (categorie!= null) {
						categorie.setDeleted(true);
						categorie.setDeleteDate(deleteVo.getDeleteDate());
						categorie.setDeleterCode(deleteVo.getDeleterCode());
						categorieRepository.saveAndFlush(categorie);
						response.add(categorie.getCodeCategorie());
					}
				}
				log.info("Catégories supprimés avec succèss "+response.toString());
				return new Response<>(response,HttpStatus.OK, "Catégories supprimées avec succès");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des catégories", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des catégories");
			}
		}
		return null;
	}

	@GetMapping("/categories-fille/{codeCategorie}")
	public Response<List<Categorie>> findCategorieFille(@PathVariable String codeCategorie) {

		List<Categorie> listOfCategories = new ArrayList<>();
			try {
				listOfCategories = categorieRepository.findCategorieMere(codeCategorie);
				log.info("Categories récupérés avec success " + listOfCategories.toString());
				return new Response<>(listOfCategories,HttpStatus.OK, "Categories récupérées avec success");
			} catch (Exception e) {
				log.error("Echec lors de la récupération des Categories", e);
				return new Response<>(listOfCategories,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Categories");
			}
	}

	@GetMapping("/findByStructureAndType/{codeStructure}/{codeTypeCategorie}")
	public Response<List<Categorie>> findByClientAndType(@PathVariable String codeStructure,@PathVariable String codeTypeCategorie) {

	  List<Categorie> result = null;
		try {
			result = categorieRepository.findByStructureAndType(codeStructure,codeTypeCategorie);
			for (Categorie categorie : result) {
				if (categorie.getImage() != null) {
					Path path = Paths.get(AppUtils.CATEGORIE_LOGO_DIRECTORY + categorie.getImage());
					if (Files.exists(path)) {
						byte[] data = Files.readAllBytes(path);
						Encoder encoder = Base64.getEncoder();
						categorie.setImage(encoder.encodeToString(data));
					}

				}
			}
			log.info("Categories récupéréss avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "Categories récupérées avec succes");
		} catch (Exception e) {

			log.error("Echec lors de la récupération des Categories ", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED,
					"Echec lors de la récupération des Categories");
		}
	}

	@PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.CATEGORIE_LOGO_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		} catch (Exception e) {
			return "error = " + e;
		}

	}

	@GetMapping("/logo")
	public Response<String> getLogo(@RequestParam String imageUrl) {
		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.CATEGORIE_LOGO_DIRECTORY);
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
