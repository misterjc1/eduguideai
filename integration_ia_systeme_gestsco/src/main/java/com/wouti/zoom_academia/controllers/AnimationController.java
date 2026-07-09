package com.wouti.zoom_academia.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.entities.Animation;
import com.wouti.zoom_academia.repositories.AnimationRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;




@RestController
@RequestMapping("/animation")
public class AnimationController {

    private static final Logger log = Logger.getLogger(AnimationController.class.getName());

    @Autowired
    public AnimationRepository animationRepository;

    @PostMapping("/save")
	public Response<Animation> save(@RequestBody Animation animation){

    	Animation result = null;
		try {

			animation.setCodeAnimation(AppUtils.generateAnimationCode());
			if (animation.getValue() != null) {
				if (animation.getValue().length() > 0) {
					this.uploadImage(animation.getValue(), animation.getCodeAnimation());
					animation.setValue(animation.getCodeAnimation() + ".png");
				}
			}
			result = animationRepository.save(animation);
			log.info("Animation enregistrée avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Animation enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de l Animation", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la Animation");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Animation>> findAllQueryDsl(
	@QuerydslPredicate(root = Animation.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Animation> result = null;
		try {
			result = animationRepository.findAll(predicate);
			log.info("Animation récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Animations récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Animations", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Animations");
		}
	}

	@GetMapping("/findAllAnimation")
	@ResponseBody
	public Response<List<Animation>>  findAllAnimation (){

		 List<Animation> result = null;
		try {
			result = animationRepository.findAllAnimation();
			for (Animation animation : result) {
				if (animation.getValue() != null) {
					Path path = Paths.get(AppUtils.ANIMATION_DIRECTORY + animation.getValue());
					byte[] data = Files.readAllBytes(path);
					Encoder encoder = Base64.getEncoder();
					animation.setValue(encoder.encodeToString(data));
				}
			}
			log.info("Animations récupérées avec succès"+result.toString());
			return new  Response<>  (result,HttpStatus.OK, "Animations récupérées avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Animations", e);
			return new  Response<> (result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Animations");
		}
	}

	@PostMapping("/update")
	public Response<Animation> update(@RequestBody Animation animation) {

		Animation result = null;
		Path imagesPath = Paths.get(AppUtils.ANIMATION_DIRECTORY + animation.getCodeAnimation() + ".png");
		try {
			if (animation.getValue()!=null && animation.getValue().length() > 0) {
				if (animation.getValue().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(animation.getValue(), animation.getCodeAnimation());
					animation.setValue(animation.getCodeAnimation() + ".png");
				}
			}
			result = animationRepository.saveAndFlush(animation);
			log.info("Animation mise à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Animation mise à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour de l animation", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour de l Animation");
		}
	}

    @PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
			List<String> response;
			response = new ArrayList<>();

			if(deleteVo!= null && deleteVo.getCodes().length > 0) {
				try {
					for (String code : deleteVo.getCodes()) {

						Animation animation = animationRepository.findByCodeAnimation(code);

						if (animation!= null) {
							animation.setDeleted(true);
							animation.setDeleteDate(deleteVo.getDeleteDate());
							animation.setDeleterCode(deleteVo.getDeleterCode());

							animationRepository.saveAndFlush(animation);
							response.add(animation.getCodeAnimation());
						}
					}
					String message = (deleteVo.getCodes().length > 1) ? "Animations supprimés avec succès" : "Animation supprimé avec succès" ;
					log.info(message+response.toString());
					return new Response<>(response,HttpStatus.OK, message);
				} catch (Exception e) {
					log.error("Echec lors de la suppression des Animations", e);
					return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des Animations");
				}


			}
			return null;

		}


    @PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.ANIMATION_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		} catch (Exception e) {
			return "error = " + e;
		}

	}

	@GetMapping("/value")
	public Response<String> getValue(@RequestParam String imageUrl) {
		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.ANIMATION_DIRECTORY);
			if (result.contains("error")){

				return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation de l'image");

			}else {

				return new Response<>(result,HttpStatus.OK, "Image recuperer");
			}

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation de l'image");

		}
	}

	@GetMapping(value = "/logo", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/logo.png");
        InputStream inputStream = resource.getInputStream();
        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }












}
