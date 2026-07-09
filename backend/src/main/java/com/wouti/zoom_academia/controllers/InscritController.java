package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.Inscrit;
import com.wouti.zoom_academia.entities.Note;
import com.wouti.zoom_academia.repositories.InscritRepository;
import com.wouti.zoom_academia.repositories.NoteRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/inscrit")
public class InscritController {

    private static final Logger log = Logger.getLogger(InscritController.class.getName());

    private final InscritRepository inscritRepository;
    private final NoteRepository noteRepository;

    public InscritController(InscritRepository inscritRepository, NoteRepository noteRepository) {
        this.inscritRepository = inscritRepository;
        this.noteRepository = noteRepository;
    }

    @PostMapping("/save")
    public Response<Inscrit> save(@RequestBody Inscrit inscrit) {
        Inscrit result = null;
        try {
            inscrit.setCreationDate(new Date());
            inscrit.setCreatorCode("system");
            inscrit.setCodeInscrit(AppUtils.generateInscritCode());
            result = inscritRepository.save(inscrit);
            log.info("Inscrit enregistré avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Inscrit enregistré avec succès");
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de l'Inscrit", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de l'Inscrit");
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Inscrit>> findAllQueryDsl(
            @QuerydslPredicate(root = Inscrit.class) com.querydsl.core.types.Predicate predicate) {
        Iterable<Inscrit> result = null;
        try {
            result = inscritRepository.findAll(predicate);
            log.info("Inscrits récupérés avec succès");
            return new Response<>(result, HttpStatus.OK, "Inscrits récupérés avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des Inscrits", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Inscrits");
        }
    }

    @PostMapping("/update")
    public Response<Inscrit> update(@RequestBody Inscrit inscrit) {
        Inscrit result = null;
        try {
            result = inscritRepository.saveAndFlush(inscrit);
            log.info("Inscrit mis à jour avec succès " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Inscrit mis à jour avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de l'Inscrit", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la mise à jour de l'Inscrit");
        }
    }

    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo) {
        List<String> response = new ArrayList<>();
        if (deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Inscrit inscrit = inscritRepository.findByCodeInscrit(code);
                    if (inscrit != null) {
                        inscrit.setDeleted(true);
                        inscrit.setDeleteDate(deleteVo.getDeleteDate());
                        inscrit.setDeleterCode(deleteVo.getDeleterCode());
                        inscritRepository.saveAndFlush(inscrit);
                        response.add(inscrit.getCodeInscrit());
                    }
                }
                log.info("Inscrits supprimés avec succès");
                return new Response<>(response, HttpStatus.OK, "Inscrits supprimés avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des Inscrits", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des Inscrits");
            }
        }
        return new Response<>(response, HttpStatus.BAD_REQUEST, "Aucun code fourni");
    }

    @GetMapping("/findByCode/{codeInscrit}")
    public Response<Inscrit> findByCode(@PathVariable String codeInscrit) {
        Inscrit result = null;
        try {
            result = inscritRepository.findByCodeInscrit(codeInscrit);
            return new Response<>(result, HttpStatus.OK, "Inscrit récupéré avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération de l'Inscrit", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de l'Inscrit");
        }
    }

    @GetMapping("/findByMatricule/{matricule}")
    public Response<Inscrit> findByMatricule(@PathVariable String matricule) {
        Inscrit result = null;
        try {
            result = inscritRepository.findByMatricule(matricule);
            return new Response<>(result, HttpStatus.OK, "Inscrit récupéré avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération par matricule", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la récupération par matricule");
        }
    }

    /** Retourne les notes d'un étudiant par son matricule. */
    @GetMapping("/findNotes")
    public Response<List<Note>> findNotes(@RequestParam String matricule) {
        List<Note> notes = null;
        try {
            notes = noteRepository.findByMatriculeAndIsDeletedFalse(matricule);
            return new Response<>(notes, HttpStatus.OK, "Notes récupérées avec succès");
        } catch (Exception e) {
            log.error("Echec lors de la récupération des notes pour le matricule: " + matricule, e);
            return new Response<>(notes, HttpStatus.INTERNAL_SERVER_ERROR, "Echec lors de la récupération des notes");
        }
    }
}
