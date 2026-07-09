package com.wouti.zoom_academia.controllers;



import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.wouti.zoom_academia.entities.Service;
import com.wouti.zoom_academia.repositories.ServiceRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;




@RestController
@RequestMapping("/service")
public class ServiceController {

	private static final Logger log = Logger.getLogger(ServiceController.class.getName());
	
	@Autowired
	public ServiceRepository serviceRepository;


	@PostMapping("/save")
	public Response<Service> save(@RequestBody Service service){

		Service result = null;
		try {

			service.setCodeService(AppUtils.generateServiceCode());
			if (service.getLogo() != null) {
				if (service.getLogo().length() > 0) {
					this.uploadImage(service.getLogo(), service.getCodeService());
					service.setLogo(service.getCodeService() + ".png");
				}
			}
			result = serviceRepository.save(service);
			log.info("service enregistré avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "service enregistré avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du service", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la service");
		}
	}
	
	
	@PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.SERV_LOGO_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		} catch (Exception e) {
			return "error = " + e;
		}

	}
	
	@GetMapping("/logo")
	public Response<String> getLogo(@RequestParam String imageUrl) {
		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.SERV_LOGO_DIRECTORY);
			if (result.contains("error")){

				return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation du logo");

			}else {

				return new Response<>(result,HttpStatus.OK, "Logo recuperer");
			}

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation du logo");

		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Service>> findAllQueryDsl(
	@QuerydslPredicate(root = Service.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Service> result = null;
		try {
			result = serviceRepository.findAll(predicate);
			log.info("Service récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Service récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Service", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Service");
		}
	}

	@PostMapping("/update")
	public Response<Service> update(@RequestBody Service service) {

		Service result = null;
		Path imagesPath = Paths.get(AppUtils.SERV_LOGO_DIRECTORY + service.getCodeService() + ".png");
		try {
			if (service.getLogo()!=null && service.getLogo().length() > 0) {
				if (service.getLogo().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(service.getLogo(), service.getCodeService());
					service.setLogo(service.getCodeService() + ".png");
				}
			}
			result = serviceRepository.saveAndFlush(service);
			log.info("service mise à jour avec succèss " + result.toString());
			return new Response<>(result,HttpStatus.OK, "service mise à jour avec succès");
		} catch (Exception e) {
			log.error("Echec lors de la mise à jour de la service", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour de service");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();
        if (deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Service service = serviceRepository.findByCodeService(code);
                    if (service != null) {
                    	service.setDeleted(true);
                    	service.setDeleteDate(deleteVo.getDeleteDate());
                    	service.setDeleterCode(deleteVo.getDeleterCode());
                    	serviceRepository.saveAndFlush(service);
                        response.add(service.getCodeService());
                    }
                }
                log.info("service supprimés avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "service supprimés avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des service", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des service");
            }
        }
        return new Response<>(response, HttpStatus.NOT_MODIFIED, "Aucun code fourni pour suppression");
    }
}


