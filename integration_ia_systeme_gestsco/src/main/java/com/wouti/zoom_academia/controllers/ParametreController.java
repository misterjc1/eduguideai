package com.wouti.zoom_academia.controllers;



import java.util.List;
import java.util.ArrayList;
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

import com.wouti.zoom_academia.entities.Parametre;
import com.wouti.zoom_academia.repositories.ParametreRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;




@RestController
@RequestMapping("/parametre")
public class ParametreController {

	private static final Logger log = Logger.getLogger(ParametreController.class.getName());
	
	@Autowired
	public ParametreRepository parametreRepository;

    @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public Response<Parametre> save(@RequestBody Parametre parametre){

		Parametre result = null;
		try {

			parametre.setCodeParametre(AppUtils.generateParametreCode());
			parametre.setCreationDate(new Date());
			parametre.setCreatorCode("system");
			result = parametreRepository.save(parametre);
			
			log.info("Parametre enregistrée avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Parametre enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la parametre", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la parametre");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Parametre>> findAllQueryDsl(
	@QuerydslPredicate(root = Parametre.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Parametre> result = null;
		try {
			result = parametreRepository.findAll(predicate);
			log.info("Parametre récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Parametre récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Parametre", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Parametre");
		}
	}

	@PostMapping("/update")
	public ResponseEntity<Response<Parametre>> update(@RequestBody Parametre parametre) {
        Parametre result = null;
        try {
            parametre.setUpdateDate(new Date()); // Update the updateDate
            result = parametreRepository.saveAndFlush(parametre);
            log.info("Parametres mis à jour avec succès : " + result.toString());
            return new ResponseEntity<>(new Response<>(result, HttpStatus.OK, "Parametres mis à jour avec succès"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Échec lors de la mise à jour des parametres", e);
            return new ResponseEntity<>(new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de la mise à jour des parametres"), HttpStatus.NOT_MODIFIED);
        }
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();
        if (deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Parametre parametre = parametreRepository.findByCodeParametre(code);
                    if (parametre != null) {
                    	parametre.setDeleted(true);
                    	parametre.setDeleteDate(deleteVo.getDeleteDate());
                    	parametre.setDeleterCode(deleteVo.getDeleterCode());
                    	parametreRepository.saveAndFlush(parametre);
                        response.add(parametre.getCodeParametre());
                    }
                }
                log.info("parametre supprimés avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "parametre supprimés avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des parametre", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des parametre");
            }
        }
        return new Response<>(response, HttpStatus.NOT_MODIFIED, "Aucun code fourni pour suppression");
    }
}

