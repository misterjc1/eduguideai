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
import com.wouti.zoom_academia.entities.Categorie;
import com.wouti.zoom_academia.entities.Inscrit;
import com.wouti.zoom_academia.entities.Liaison;
import com.wouti.zoom_academia.entities.Utilisateur;
import com.wouti.zoom_academia.repositories.LiaisonRepository;
import com.wouti.zoom_academia.transverse.StatutLiaison;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;


@RestController
@RequestMapping("/liaison")
public class LiaisonController {
	
	private static final Logger log = Logger.getLogger(LiaisonController.class.getName());

    @Autowired
    public LiaisonRepository liaisonRepository;
    
    @Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

    @PostMapping("/save")
    public Response<Liaison> save(@RequestBody Liaison liaison){

        Liaison result = null;
        try {
        	liaison.setCreationDate(new Date());
        	liaison.setCreatorCode("system");
        	liaison.setCodeLiaison(AppUtils.generateLiaisonCode());
            result = liaisonRepository.save(liaison);

            // Piste d'audit — non bloquante
            try {
                AuditVo auditVo = new AuditVo();
                auditVo.setAuteur(liaison.getCreatorCode());
                auditVo.setDate(new Date());
                auditVo.setDescription("Liaison enregistrée avec succès " + result.toString());
                ObjectMapper objectMapper = new ObjectMapper();
                String auditJSON = objectMapper.writeValueAsString(auditVo);
                RequestEntity<String> requestEntity = RequestEntity.post(new URL(AppUtils.SAVE_AUDIT).toURI())
                        .contentType(MediaType.APPLICATION_JSON).body(auditJSON);
                ZoomAcademia.restTemplate().exchange(requestEntity, String.class);
            } catch (Exception auditEx) {
                log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
            }

            return new Response<>(result, HttpStatus.OK, "Liaison enregistrée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de la liaison", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la liaison");
        }
    }
    
    
    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Liaison>> findAllQueryDsl(
        @QuerydslPredicate(root = Liaison.class) com.querydsl.core.types.Predicate predicate){

        Iterable<Liaison> result = null;
        try {
            result = liaisonRepository.findAll(predicate);
            log.info("Liaisons récupérées avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Liaisons récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des Liaisons", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Liaisons");
        }
    }
    
//    @GetMapping("/findByUtilisateur/{ codeUtilisateur }")
//    @ResponseBody
//	public Response<Iterable<Liaison>> findByUtilisateur(@PathVariable String codeUtilisateur){
//
//		Iterable<Liaison> result = null;
//		System.out.println(codeUtilisateur);
//		try {
//			result = liaisonRepository.findAll(codeUtilisateur);
//			log.info("Objectifs de l'agent récupérées avec succèss "+result.toString());
//			return new Response<Iterable<Liaison>>(result,HttpStatus.OK, "Insrits du tuteur récupérés avec succèss");
//		} catch (Exception e) {
//			log.error("Echec lors de la récupération des objectifs", e);
//			return new Response<Iterable<Liaison>>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Inscrits");
//		}
//	}
    
    @PostMapping("/update")
    public Response<Liaison> update(@RequestBody Liaison Liaison) {

        Liaison result = null;
        try {
            result = liaisonRepository.saveAndFlush(Liaison);
            log.info("Liaison mise à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Liaison mise à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de la Liaison", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de la Liaison");
        }
    }
    
    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();

        if(deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Liaison liaison = liaisonRepository.findByCodeLiaison(code);
                    if (liaison != null) {
                        liaison.setDeleted(true);
                        liaison.setDeleteDate(deleteVo.getDeleteDate());
                        liaison.setDeleterCode(deleteVo.getDeleterCode());
                        liaisonRepository.saveAndFlush(liaison);
                        response.add(liaison.getCodeLiaison());
                    }
                }
                log.info("Liaisons supprimées avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Liaisons supprimées avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des Liaisons", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des Liaisons");
            }
        }
        return null;
    }
    
    @GetMapping("/findByCode/{codeLiaison}")
    public Response<Liaison> findByCode(@PathVariable String codeLiaison) {

        Liaison result = null;
        try {
            result = liaisonRepository.findByCodeLiaison(codeLiaison);
            log.info("Liaison récupérée avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Liaison récupérée avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de la Liaison", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de la Liaison");
        }
    }
    
//    @GetMapping("/findByLiaison/{codeLiaison}")
//    public Response<List<Inscrit>> findByLiaison(@PathVariable String codeLiaison) {
//        try {
//            List<Inscrit> result = liaisonRepository.findByLiaison(codeLiaison);
//            if (!result.isEmpty()) {
//                log.info("Inscrits récupérés avec succès pour la liaison: " + codeLiaison);
//                return new Response<>(result, HttpStatus.OK, "Inscrits récupérés avec succès");
//            } else {
//                log.info("Aucun inscrit trouvé pour la liaison: " + codeLiaison);
//                return new Response<>(result, HttpStatus.OK, "Aucun inscrit trouvé pour cette liaison");
//            }
//        } catch (Exception e) {
//            log.error("Echec lors de la récupération des inscrits pour la liaison", e);
//            return new Response<>(null, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des inscrits");
//        }
//    }
    
    @GetMapping("/findUtilisateursByMatricule/{matricule}")
    public Response<List<Utilisateur>> findUtilisateursByMatricule(@PathVariable String matricule) {
        List<Utilisateur> result = null;
        try {
            result = liaisonRepository.findUtilisateursByMatriculeInscrit(matricule);
            if (!result.isEmpty()) {
                log.info("Utilisateurs récupérés avec succès pour le matricule: " + matricule);
                return new Response<>(result, HttpStatus.OK, "Utilisateurs récupérés avec succès");
            } else {
                log.info("Aucun utilisateur trouvé pour le matricule: " + matricule);
                return new Response<>(result, HttpStatus.OK, "Aucun utilisateur trouvé pour ce matricule");
            }
        } catch (Exception e) {
            log.error("Échec lors de la récupération des utilisateurs pour le matricule: " + matricule, e);
            return new Response<>(null, HttpStatus.NOT_MODIFIED, "Échec lors de la récupération des utilisateurs");
        }
    }
    
//    @GetMapping("/findLiaisonByCodeUtilisateur/{codeUtilisateur}")
//    public Response<List<Inscrit>> findLiaisonByCodeUtilisateur(@PathVariable String codeUtilisateur) {
//        List<Inscrit> result = null;
//        try {
//            result = liaisonRepository.findLiaisonByCodeUtilisateur(codeUtilisateur);
//            if (!result.isEmpty()) {
//                log.info("Inscrits récupérés avec succès pour le code utilisateur: " + codeUtilisateur);
//                return new Response<>(result, HttpStatus.OK, "Inscrits récupérés avec succès");
//            } else {
//                log.info("Aucun inscrit trouvé pour le code utilisateur: " + codeUtilisateur);
//                return new Response<>(result, HttpStatus.OK, "Aucun inscrit trouvé pour ce code utilisateur");
//            }
//        } catch (Exception e) {
//            log.error("Échec lors de la récupération des inscrits pour le code utilisateur: " + codeUtilisateur, e);
//            return new Response<>(null, HttpStatus.NOT_MODIFIED, "Échec lors de la récupération des inscrits");
//        }
//    }
    
    @GetMapping("/findLiaisonByCodeUtilisateur/{codeUtilisateur}/{statut}")
    public Response<List<Inscrit>> findLiaisonByCodeUtilisateur(
            @PathVariable String codeUtilisateur,
            @PathVariable StatutLiaison statut) {
        List<Inscrit> result = null;
        try {
            result = liaisonRepository.findLiaisonByCodeUtilisateur(codeUtilisateur, statut);
            if (!result.isEmpty()) {
                log.info("Inscrits récupérés avec succès pour le code utilisateur: " + codeUtilisateur + " et statut: " + statut);
                return new Response<>(result, HttpStatus.OK, "Inscrits récupérés avec succès");
            } else {
                log.info("Aucun inscrit trouvé pour le code utilisateur: " + codeUtilisateur + " et statut: " + statut);
                return new Response<>(result, HttpStatus.OK, "Aucun inscrit trouvé pour ce code utilisateur et statut");
            }
        } catch (Exception e) {
            log.error("Échec lors de la récupération des inscrits pour le code utilisateur: " + codeUtilisateur + " et statut: " + statut, e);
            return new Response<>(null, HttpStatus.NOT_MODIFIED, "Échec lors de la récupération des inscrits");
        }
    }

    
}
