package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.LignePersonne;
import com.wouti.zoom_academia.repositories.LignePersonneRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/lignePersonne")
public class LignePersonneController {

    private static final Logger log = Logger.getLogger(LignePersonneController.class.getName());

    @Autowired
    public LignePersonneRepository lignePersonneRepository;

    @PostMapping("/save")
    public Response<LignePersonne> save(@RequestBody LignePersonne lignePersonne){

        LignePersonne result = null;
        try {
        	lignePersonne.setCreationDate(new Date());
        	lignePersonne.setCreatorCode("system");
            lignePersonne.setCodeLignePersonne(AppUtils.generateLignePersonneCode());
        	result = lignePersonneRepository.save(lignePersonne);
            log.info("LignePersonne enregistrée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "LignePersonne enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la lignePersonne", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la lignePersonne");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<LignePersonne>> findAllQueryDsl(
        @QuerydslPredicate(root = LignePersonne.class) com.querydsl.core.types.Predicate predicate){

        Iterable<LignePersonne> result = null;
        try {
            result = lignePersonneRepository.findAll(predicate);
            log.info("LignePersonnes récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "LignePersonnes récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des lignePersonnes", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des lignePersonnes");
        }
    }

    @PostMapping("/update")
    public Response<LignePersonne> update(@RequestBody LignePersonne lignePersonne) {

        LignePersonne result = null;
        try {
            result = lignePersonneRepository.saveAndFlush(lignePersonne);
            log.info("LignePersonne mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "LignePersonne mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la lignePersonne", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la lignePersonne");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    LignePersonne lignePersonne = lignePersonneRepository.findByCodeLignePersonne(code);
                    if (lignePersonne != null) {
                        lignePersonne.setDeleted(true);
                        lignePersonne.setDeleteDate(deleteVo.getDeleteDate());
                        lignePersonne.setDeleterCode(deleteVo.getDeleterCode());
                        lignePersonneRepository.saveAndFlush(lignePersonne);
                        response.add(lignePersonne.getCodeLignePersonne());
                    }
                }
                log.info("LignePersonnes supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "LignePersonnes supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des lignePersonnes", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des lignePersonnes");
            }
        }
        return null;
    }

    @GetMapping("/findByCode/{codeLignePersonne}")
    public Response<LignePersonne> findByCode(@PathVariable String codeLignePersonne) {

        LignePersonne result = null;
        try {
            result = lignePersonneRepository.findByCodeLignePersonne(codeLignePersonne);
            log.info("LignePersonne récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "LignePersonne récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la lignePersonne", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la lignePersonne");
        }
    }
}

