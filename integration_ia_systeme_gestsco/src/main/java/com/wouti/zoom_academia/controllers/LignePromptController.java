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

import com.wouti.zoom_academia.entities.LignePrompt;
import com.wouti.zoom_academia.repositories.LignePromptRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/lignePrompt")
public class LignePromptController {

	private static final Logger log = Logger.getLogger(LignePromptController.class.getName());
	
	@Autowired
	public LignePromptRepository lignePromptRepository;


	@PostMapping("/save")
	public Response<LignePrompt> save(@RequestBody LignePrompt lignePrompt){

		LignePrompt result = null;
		try {

			lignePrompt.setCodeLignePrompt(AppUtils.generateLignePromptCode());
			lignePrompt.setCreationDate(new Date());
			lignePrompt.setCreatorCode("system");
			result = lignePromptRepository.save(lignePrompt);
			
			log.info("LignePrompt enregistrée avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "LignePrompt enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la lignePrompt", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la lignePrompt");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<LignePrompt>> findAllQueryDsl(
	@QuerydslPredicate(root = LignePrompt.class) com.querydsl.core.types.Predicate predicate){

		Iterable<LignePrompt> result = null;
		try {
			result = lignePromptRepository.findAll(predicate);
			log.info("LignePrompt récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "LignePrompt récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des LignePrompt", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des LignePrompt");
		}
	}

	@PostMapping("/update")
	public ResponseEntity<Response<LignePrompt>> update(@RequestBody LignePrompt lignePrompt) {
        LignePrompt result = null;
        try {
            lignePrompt.setUpdateDate(new Date()); // Update the updateDate
            result = lignePromptRepository.saveAndFlush(lignePrompt);
            log.info("LignePrompts mis à jour avec succès : " + result.toString());
            return new ResponseEntity<>(new Response<>(result, HttpStatus.OK, "LignePrompts mis à jour avec succès"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Échec lors de la mise à jour des lignePrompts", e);
            return new ResponseEntity<>(new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de la mise à jour des lignePrompts"), HttpStatus.NOT_MODIFIED);
        }
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();
        if (deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    LignePrompt lignePrompt = lignePromptRepository.findByCodeLignePrompt(code);
                    if (lignePrompt != null) {
                    	lignePrompt.setDeleted(true);
                    	lignePrompt.setDeleteDate(deleteVo.getDeleteDate());
                    	lignePrompt.setDeleterCode(deleteVo.getDeleterCode());
                    	lignePromptRepository.saveAndFlush(lignePrompt);
                        response.add(lignePrompt.getCodeLignePrompt());
                    }
                }
                log.info("lignePrompt supprimés avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "lignePrompt supprimés avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des lignePrompt", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des lignePrompt");
            }
        }
        return new Response<>(response, HttpStatus.NOT_MODIFIED, "Aucun code fourni pour suppression");
    }
}

