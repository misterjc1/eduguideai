package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.Personne;
import com.wouti.zoom_academia.repositories.PersonneRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/personne")
public class PersonneController {

    private static final Logger log = Logger.getLogger(PersonneController.class.getName());

    @Autowired
    public PersonneRepository personneRepository;

    @PostMapping("/save")
    public Response<Personne> save(@RequestBody Personne personne){

        Personne result = null;
        try {
        	personne.setCreationDate(new Date());
        	personne.setCreatorCode("system");
            personne.setCodePersonne(AppUtils.generatePersonneCode());
        	result = personneRepository.save(personne);
            log.info("Personne enregistrée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Personne enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la personne", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la personne");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Personne>> findAllQueryDsl(
        @QuerydslPredicate(root = Personne.class) com.querydsl.core.types.Predicate predicate){

        Iterable<Personne> result = null;
        try {
            result = personneRepository.findAll(predicate);
            log.info("Personnes récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Personnes récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des personnes", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des personnes");
        }
    }

    @PostMapping("/update")
    public Response<Personne> update(@RequestBody Personne personne) {

        Personne result = null;
        try {
            result = personneRepository.saveAndFlush(personne);
            log.info("Personne mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Personne mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la personne", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la personne");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Personne personne = personneRepository.findByCodePersonne(code);
                    if (personne != null) {
                        personne.setDeleted(true);
                        personne.setDeleteDate(deleteVo.getDeleteDate());
                        personne.setDeleterCode(deleteVo.getDeleterCode());
                        personneRepository.saveAndFlush(personne);
                        response.add(personne.getCodePersonne());
                    }
                }
                log.info("Personnes supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Personnes supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des personnes", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des personnes");
            }
        }
        return null;
    }

    @GetMapping("/findByCode/{codePersonne}")
    public Response<Personne> findByCode(@PathVariable String codePersonne) {

        Personne result = null;
        try {
            result = personneRepository.findByCodePersonne(codePersonne);
            log.info("Personne récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Personne récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la personne", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la personne");
        }
    }
}

