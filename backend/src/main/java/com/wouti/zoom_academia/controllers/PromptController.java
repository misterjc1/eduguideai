package com.wouti.zoom_academia.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.Prompt;
import com.wouti.zoom_academia.repositories.PromptRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;





@RestController
@RequestMapping("/prompt")
public class PromptController {

    private static final Logger log = Logger.getLogger(PromptController.class.getName());

    @Autowired
    public PromptRepository promptRepository;

    @PostMapping("/save")
    public Response<Prompt> save(@RequestBody Prompt prompt) {
        Prompt result = null;
        try {
			prompt.setCreationDate(new Date());
            prompt.setCodePrompt(AppUtils.generatePromptCode());
            prompt.setDateCreation(new Date());
			prompt.setCreatorCode("system");
            result = promptRepository.save(prompt);
            log.info("Prompt enregistré avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Prompt enregistré avec succès");
        } catch (Exception e) {
            log.error("Échec lors de l'enregistrement du prompt", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de l'enregistrement du prompt");
        }
    }

    @GetMapping("/findAll")
    public Response<List<Prompt>> findAll() {
        List<Prompt> result = null;
        try {
            result = promptRepository.findAll();
            log.info("Prompts récupérés avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Prompts récupérés avec succès");
        } catch (Exception e) {
            log.error("Échec lors de la récupération des prompts", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de la récupération des prompts");
        }
    }

    @PostMapping("/update")
    public Response<Prompt> update(@RequestBody Prompt prompt) {
        Prompt result = null;
        try {
            result = promptRepository.save(prompt);
            log.info("Prompt mis à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Prompt mis à jour avec succès");
        } catch (Exception e) {
            log.error("Échec lors de la mise à jour du prompt", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de la mise à jour du prompt");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Prompt prompt = promptRepository.findByCodePrompt(code);
                    if (prompt != null) {
                        prompt.setDeleted(true);
                        prompt.setDeleteDate(deleteVo.getDeleteDate());
                        prompt.setDeleterCode(deleteVo.getDeleterCode());
                        promptRepository.saveAndFlush(prompt);
                        response.add(prompt.getCodePrompt());
                    }
                }
                log.info("Personnes supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Prompt supprimé avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des personnes", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des prompts");
            }
        }
        return null;
    }
 
}

