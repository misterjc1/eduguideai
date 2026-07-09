package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.TemplatePrompt;
import com.wouti.zoom_academia.repositories.TemplatePromptRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/templatePrompt")
public class TemplatePromptController {

    private static final Logger log = Logger.getLogger(TemplatePromptController.class.getName());

    @Autowired
    public TemplatePromptRepository templatePromptRepository;

    @PostMapping("/save")
    public Response<TemplatePrompt> save(@RequestBody TemplatePrompt templatePrompt){

        TemplatePrompt result = null;
        try {
        	templatePrompt.setCreationDate(new Date());
        	templatePrompt.setCreatorCode("system");
            templatePrompt.setCodeTemplatePrompt(AppUtils.generateTemplatePromptCode());
        	result = templatePromptRepository.save(templatePrompt);
            log.info("TemplatePrompt enregistrée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TemplatePrompt enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la templatePrompt", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la templatePrompt");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<TemplatePrompt>> findAllQueryDsl(
        @QuerydslPredicate(root = TemplatePrompt.class) com.querydsl.core.types.Predicate predicate){

        Iterable<TemplatePrompt> result = null;
        try {
            result = templatePromptRepository.findAll(predicate);
            log.info("TemplatePrompts récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TemplatePrompts récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des templatePrompts", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des templatePrompts");
        }
    }

    @PostMapping("/update")
    public Response<TemplatePrompt> update(@RequestBody TemplatePrompt templatePrompt) {

        TemplatePrompt result = null;
        try {
            result = templatePromptRepository.saveAndFlush(templatePrompt);
            log.info("TemplatePrompt mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TemplatePrompt mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la templatePrompt", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la templatePrompt");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    TemplatePrompt templatePrompt = templatePromptRepository.findByCodeTemplatePrompt(code);
                    if (templatePrompt != null) {
                        templatePrompt.setDeleted(true);
                        templatePrompt.setDeleteDate(deleteVo.getDeleteDate());
                        templatePrompt.setDeleterCode(deleteVo.getDeleterCode());
                        templatePromptRepository.saveAndFlush(templatePrompt);
                        response.add(templatePrompt.getCodeTemplatePrompt());
                    }
                }
                log.info("TemplatePrompts supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "TemplatePrompts supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des templatePrompts", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des templatePrompts");
            }
        }
        return null;
    }

    @GetMapping("/findByCode/{codeTemplatePrompt}")
    public Response<TemplatePrompt> findByCode(@PathVariable String codeTemplatePrompt) {

        TemplatePrompt result = null;
        try {
            result = templatePromptRepository.findByCodeTemplatePrompt(codeTemplatePrompt);
            log.info("TemplatePrompt récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TemplatePrompt récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la templatePrompt", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la templatePrompt");
        }
    }
}

