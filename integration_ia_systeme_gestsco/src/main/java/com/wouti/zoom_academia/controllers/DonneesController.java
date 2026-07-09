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

import com.wouti.zoom_academia.entities.Donnees;
import com.wouti.zoom_academia.repositories.DonneesRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/donnees")
public class DonneesController {

    private static final Logger log = Logger.getLogger(DonneesController.class.getName());

    @Autowired
    public DonneesRepository donneesRepository;

    @PostMapping("/save")
    public Response<Donnees> save(@RequestBody Donnees donnees){

        Donnees result = null;
        try {
        	donnees.setCreationDate(new Date());
        	donnees.setCreatorCode("system");
            donnees.setCodeDonnee(AppUtils.generateDonneesCode());
        	result = donneesRepository.save(donnees);
            log.info("Donnees enregistrée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Donnees enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la donnees", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la donnees");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Donnees>> findAllQueryDsl(
        @QuerydslPredicate(root = Donnees.class) com.querydsl.core.types.Predicate predicate){

        Iterable<Donnees> result = null;
        try {
            result = donneesRepository.findAll(predicate);
            log.info("Donneess récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Donneess récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des donneess", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des donneess");
        }
    }

    @PostMapping("/update")
    public Response<Donnees> update(@RequestBody Donnees donnees) {

        Donnees result = null;
        try {
            result = donneesRepository.saveAndFlush(donnees);
            log.info("Donnees mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Donnees mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la donnees", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la donnees");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Donnees donnees = donneesRepository.findByCodeDonnee(code);
                    if (donnees != null) {
                        donnees.setDeleted(true);
                        donnees.setDeleteDate(deleteVo.getDeleteDate());
                        donnees.setDeleterCode(deleteVo.getDeleterCode());
                        donneesRepository.saveAndFlush(donnees);
                        response.add(donnees.getCodeDonnee());
                    }
                }
                log.info("Donneess supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Donneess supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des donneess", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des donneess");
            }
        }
        return null;
    }

    @GetMapping("/findByCode/{codeDonnees}")
    public Response<Donnees> findByCode(@PathVariable String codeDonnees) {

        Donnees result = null;
        try {
            result = donneesRepository.findByCodeDonnee(codeDonnees);
            log.info("Donnees récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Donnees récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la donnees", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la donnees");
        }
    }
}


