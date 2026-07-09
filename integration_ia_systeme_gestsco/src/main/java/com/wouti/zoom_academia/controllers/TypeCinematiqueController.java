package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.TypeCinematique;
import com.wouti.zoom_academia.repositories.TypeCinematiqueRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/typeCinematique")
public class TypeCinematiqueController {

    private static final Logger log = Logger.getLogger(TypeCinematiqueController.class.getName());

    @Autowired
    public TypeCinematiqueRepository typeCinematiqueRepository;

    @PostMapping("/save")
    public Response<TypeCinematique> save(@RequestBody TypeCinematique typeCinematique){

        TypeCinematique result = null;
        try {
        	typeCinematique.setCreationDate(new Date());
        	typeCinematique.setCreatorCode("system");
            typeCinematique.setCodeTypeCinematique(AppUtils.generateTypeCinematiqueCode());
        	result = typeCinematiqueRepository.save(typeCinematique);
            log.info("TypeCinematique enregistrée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TypeCinematique enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la typeCinematique", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la typeCinematique");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<TypeCinematique>> findAllQueryDsl(
        @QuerydslPredicate(root = TypeCinematique.class) com.querydsl.core.types.Predicate predicate){

        Iterable<TypeCinematique> result = null;
        try {
            result = typeCinematiqueRepository.findAll(predicate);
            log.info("TypeCinematiques récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TypeCinematiques récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des typeCinematiques", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des typeCinematiques");
        }
    }

    @PostMapping("/update")
    public Response<TypeCinematique> update(@RequestBody TypeCinematique typeCinematique) {

        TypeCinematique result = null;
        try {
            result = typeCinematiqueRepository.saveAndFlush(typeCinematique);
            log.info("TypeCinematique mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TypeCinematique mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la typeCinematique", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la typeCinematique");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    TypeCinematique typeCinematique = typeCinematiqueRepository.findByCodeTypeCinematique(code);
                    if (typeCinematique != null) {
                        typeCinematique.setDeleted(true);
                        typeCinematique.setDeleteDate(deleteVo.getDeleteDate());
                        typeCinematique.setDeleterCode(deleteVo.getDeleterCode());
                        typeCinematiqueRepository.saveAndFlush(typeCinematique);
                        response.add(typeCinematique.getCodeTypeCinematique());
                    }
                }
                log.info("TypeCinematiques supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "TypeCinematiques supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des typeCinematiques", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des typeCinematiques");
            }
        }
        return null;
    }

    @GetMapping("/findByCode/{codeTypeCinematique}")
    public Response<TypeCinematique> findByCode(@PathVariable String codeTypeCinematique) {

        TypeCinematique result = null;
        try {
            result = typeCinematiqueRepository.findByCodeTypeCinematique(codeTypeCinematique);
            log.info("TypeCinematique récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "TypeCinematique récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la typeCinematique", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la typeCinematique");
        }
    }
}

