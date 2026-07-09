package com.wouti.zoom_academia.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.entities.Niveau;
import com.wouti.zoom_academia.repositories.NiveauRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/niveau")
public class NiveauController {
	private static final Logger log = Logger.getLogger(NiveauController.class.getName());

    @Autowired
    public NiveauRepository niveauRepository;
    
    @Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

    @PostMapping("/save")
    public Response<Niveau> save(@RequestBody Niveau niveau){

        Niveau result = null;
        try {
        	niveau.setCreationDate(new Date());
        	niveau.setCreatorCode("system");
        	niveau.setCodeNiveau(AppUtils.generateNiveau());
            result = niveauRepository.save(niveau);

            // Piste d'audit — non bloquante
            try {
                AuditVo auditVo = new AuditVo();
                auditVo.setAuteur(niveau.getCreatorCode());
                auditVo.setDate(new Date());
                auditVo.setDescription("Niveau enregistré avec succès " + result.toString());
                ObjectMapper objectMapper = new ObjectMapper();
                String auditJSON = objectMapper.writeValueAsString(auditVo);
                RequestEntity<String> requestEntity = RequestEntity.post(new URL(AppUtils.SAVE_AUDIT).toURI())
                        .contentType(MediaType.APPLICATION_JSON).body(auditJSON);
                ZoomAcademia.restTemplate().exchange(requestEntity, String.class);
            } catch (Exception auditEx) {
                log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
            }

            return new Response<>(result, HttpStatus.OK, "Niveau enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement du niveau", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du niveau");
        }
    }
    
    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Niveau>> findAllQueryDsl(
        @QuerydslPredicate(root = Niveau.class) com.querydsl.core.types.Predicate predicate){

        Iterable<Niveau> result = null;
        try {
            result = niveauRepository.findAll(predicate);
            log.info("Niveaus récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Niveaus récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des Niveaus", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Niveaus");
        }
    }
    

    @PostMapping("/update")
    public Response<Niveau> update(@RequestBody Niveau Niveau) {

        Niveau result = null;
        try {
            result = niveauRepository.saveAndFlush(Niveau);
            log.info("Niveau mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Niveau mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la Niveau", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour du Niveau");
        }
    }
    
    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Niveau niveau = niveauRepository.findByCodeNiveau(code);
                    if (niveau != null) {
                        niveau.setDeleted(true);
                        niveau.setDeleteDate(deleteVo.getDeleteDate());
                        niveau.setDeleterCode(deleteVo.getDeleterCode());
                        niveauRepository.saveAndFlush(niveau);
                        response.add(niveau.getCodeNiveau());
                    }
                }
                log.info("Niveaus supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Niveaus supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des Niveaus", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des Niveaus");
            }
        }
        return null;
    }
    
    @GetMapping("/findByCode/{codeNiveau}")
    public Response<Niveau> findByCode(@PathVariable String codeNiveau) {

        Niveau result = null;
        try {
            result = niveauRepository.findByCodeNiveau(codeNiveau);
            log.info("Niveau récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Niveau récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération du Niveau", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération du Niveau");
        }
    }
}
