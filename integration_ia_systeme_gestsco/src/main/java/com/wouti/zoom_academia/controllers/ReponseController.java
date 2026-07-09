package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.entities.Reponse;
import com.wouti.zoom_academia.repositories.ReponseRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/reponse")
public class ReponseController {

    private static final Logger log = Logger.getLogger(ReponseController.class.getName());

    @Autowired
    public ReponseRepository reponseRepository;

    @PostMapping("/save")
    public Response<Reponse> save(@RequestBody Reponse reponse){

        Reponse result = null;
        try {
        	reponse.setCreationDate(new Date());
        	reponse.setCreatorCode("system");
            reponse.setCodeResp(AppUtils.generateReponseCode());
        	result = reponseRepository.save(reponse);
            log.info("Reponse enregistrée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Reponse enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la reponse", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la reponse");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Reponse>> findAllQueryDsl(
        @QuerydslPredicate(root = Reponse.class) com.querydsl.core.types.Predicate predicate){

        Iterable<Reponse> result = null;
        try {
            result = reponseRepository.findAll(predicate);
            log.info("Reponses récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Reponses récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des reponses", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des reponses");
        }
    }

    @PostMapping("/update")
    public Response<Reponse> update(@RequestBody Reponse reponse) {

        Reponse result = null;
        try {
            result = reponseRepository.saveAndFlush(reponse);
            log.info("Reponse mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Reponse mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la reponse", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la reponse");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Reponse reponse = reponseRepository.findByCodeResp(code);
                    if (reponse != null) {
                        reponse.setDeleted(true);
                        reponse.setDeleteDate(deleteVo.getDeleteDate());
                        reponse.setDeleterCode(deleteVo.getDeleterCode());
                        reponseRepository.saveAndFlush(reponse);
                        response.add(reponse.getCodeResp());
                    }
                }
                log.info("Reponses supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Reponses supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des reponses", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des reponses");
            }
        }
        return null;
    }

    @GetMapping("/findByCode/{codeReponse}")
    public Response<Reponse> findByCode(@PathVariable String codeReponse) {

        Reponse result = null;
        try {
            result = reponseRepository.findByCodeResp(codeReponse);
            log.info("Reponse récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Reponse récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la reponse", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la reponse");
        }
    }
}


