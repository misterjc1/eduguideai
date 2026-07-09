package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wouti.zoom_academia.entities.Session;
import com.wouti.zoom_academia.entities.Utilisateur;
import com.wouti.zoom_academia.repositories.SessionRepository;
import com.wouti.zoom_academia.repositories.UtilisateurRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.CredentialVo;
import com.wouti.zoom_academia.vo.DeleteVo;
import com.wouti.zoom_academia.vo.LoginResponse;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private static final Logger log = Logger.getLogger(UtilisateurController.class.getName());

    @Autowired
    public UtilisateurRepository utilisateurRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/signin")
    public Response<LoginResponse> signin(@RequestBody CredentialVo credentials) {
        
        LoginResponse loginResponse = null;
        
        try {
            log.info("Tentative de connexion pour: " + credentials.getUsername());
            
            Utilisateur user = utilisateurRepository.findByUsername(credentials.getUsername());
            
            if (user == null) {
                log.warn("Utilisateur non trouvé: " + credentials.getUsername());
                return new Response<>(null, HttpStatus.UNAUTHORIZED, "Identifiants incorrects");
            }
            
            if (!user.getPassword().equals(credentials.getPassword())) {
                log.warn("Mot de passe incorrect pour: " + credentials.getUsername());
                return new Response<>(null, HttpStatus.UNAUTHORIZED, "Identifiants incorrects");
            }
            
            if (user.getActif() != null && !user.getActif()) {
                log.warn("Compte désactivé: " + credentials.getUsername());
                return new Response<>(null, HttpStatus.FORBIDDEN, "Compte désactivé");
            }
            
            // ⭐ CRÉER UNE SESSION
            Session session = new Session();
            session.setCodeSession(AppUtils.generateSessionCode());
            session.setStartDate(new Date());
            session.setUtilisateur(user);
            session.setStatut(true);
            session.setDeleted(false);
            session.setCreationDate(new Date());
            session.setCreatorCode("system");
            sessionRepository.save(session);
            
            // ⭐ RÉCUPÉRER LES RÔLES DU PROFIL
            String role = "";
            if (user.getProfil() != null) {
                role = user.getProfil().getRole() != null ? user.getProfil().getRole() : "";
            }
            
            // Générer un token
            String token = UUID.randomUUID().toString();
            
            // Masquer les données sensibles
            user.setPassword(null);
            user.setPin(null);
            
            // ⭐ CRÉER LA RÉPONSE AVEC TOUS LES CHAMPS
            loginResponse = new LoginResponse(
                token, 
                user, 
                role, 
                session.getCodeSession(),
                "" // referenceExterne vide pour l'instant
            );
            
            log.info("Connexion réussie pour: " + credentials.getUsername());
            return new Response<>(loginResponse, HttpStatus.OK, "Connexion réussie");
            
        } catch (Exception e) {
            log.error("Erreur lors de la connexion pour: " + credentials.getUsername(), e);
            return new Response<>(null, HttpStatus.INTERNAL_SERVER_ERROR, 
                "Erreur lors de la connexion: " + e.getMessage());
        }
    }

    // ⭐ ENDPOINT SIGNUP (Inscription)
    @PostMapping("/signup")
    public Response<Utilisateur> signup(@RequestBody Utilisateur utilisateur) {
        
        Utilisateur result = null;
        
        try {
            log.info("Tentative d'inscription pour: " + utilisateur.getUsername());
            
            // Vérifier si le username existe déjà
            Utilisateur existing = utilisateurRepository.findByUsername(utilisateur.getUsername());
            if (existing != null) {
                log.warn("Username déjà existant: " + utilisateur.getUsername());
                return new Response<>(null, HttpStatus.CONFLICT, 
                    "Ce nom d'utilisateur existe déjà");
            }
            
            // Initialiser les champs
            utilisateur.setCreationDate(new Date());
            utilisateur.setCreatorCode("system");
            utilisateur.setCodeUtilisateur(AppUtils.generateUtilisateurCode());
            utilisateur.setActif(true);
            utilisateur.setDeleted(false);
            
            result = utilisateurRepository.save(utilisateur);
            
            // Masquer le mot de passe avant de renvoyer
            result.setPassword(null);
            result.setPin(null);
            
            log.info("Utilisateur inscrit avec succès: " + result.getUsername());
            return new Response<>(result, HttpStatus.OK, "Inscription réussie");
            
        } catch (Exception e) {
            log.error("Erreur lors de l'inscription", e);
            return new Response<>(null, HttpStatus.INTERNAL_SERVER_ERROR, 
                "Erreur lors de l'inscription: " + e.getMessage());
        }
    }

    // ENDPOINT SAVE
    @PostMapping("/save")
    public Response<Utilisateur> save(@RequestBody Utilisateur utilisateur) {

        Utilisateur result = null;

        try {
            // Vérifier si le username existe déjà
            if (utilisateur.getUsername() != null) {
                Utilisateur existing = utilisateurRepository.findByUsername(utilisateur.getUsername());
                if (existing != null) {
                    return new Response<>(null, HttpStatus.CONFLICT, "Ce nom d'utilisateur existe déjà : " + utilisateur.getUsername());
                }
            }

            utilisateur.setCreationDate(new Date());
            utilisateur.setCreatorCode("system");
            utilisateur.setCodeUtilisateur(AppUtils.generateUtilisateurCode());
            utilisateur.setActif(true);
            utilisateur.setDeleted(false);

            result = utilisateurRepository.save(utilisateur);
            
            log.info("Utilisateur enregistré avec succès: " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Utilisateur enregistré avec succès");
            
        } catch (Exception e) {
            log.error("Echec lors de l'enregistrement de l'utilisateur", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, 
                "Echec lors de l'enregistrement de l'utilisateur");
        }
    }

    // ENDPOINT FIND ALL
    @GetMapping("/findAll")
    @ResponseBody
    public Response<Iterable<Utilisateur>> findAllQueryDsl(
        @QuerydslPredicate(root = Utilisateur.class) com.querydsl.core.types.Predicate predicate) {
        
        Iterable<Utilisateur> result = null;
        
        try {
            result = utilisateurRepository.findAll(predicate);
            
            // Masquer les mots de passe
            result.forEach(u -> {
                u.setPassword(null);
                u.setPin(null);
            });
            
            log.info("Utilisateurs récupérés avec succès");
            return new Response<>(result, HttpStatus.OK, "Utilisateurs récupérés avec succès");
            
        } catch (Exception e) {
            log.error("Echec lors de la récupération des utilisateurs", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, 
                "Echec lors de la récupération des utilisateurs");
        }
    }

    // ENDPOINT UPDATE
    @PostMapping("/update")
    public Response<Utilisateur> update(@RequestBody Utilisateur utilisateur) {

        Utilisateur result = null;

        try {
            utilisateur.setUpdateDate(new Date());
            utilisateur.setUpdaterCode("system");

            // Preserve existing password if not provided in request (API masks it on reads)
            if (utilisateur.getPassword() == null && utilisateur.getIdUtilisateur() != null) {
                utilisateurRepository.findById(utilisateur.getIdUtilisateur()).ifPresent(existing -> {
                    utilisateur.setPassword(existing.getPassword());
                });
            }

            result = utilisateurRepository.saveAndFlush(utilisateur);
            
            log.info("Utilisateur mis à jour avec succès: " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Utilisateur mis à jour avec succès");
            
        } catch (Exception e) {
            log.error("Echec lors de la mise à jour de l'utilisateur", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, 
                "Echec lors de la mise à jour de l'utilisateur");
        }
    }

    // ENDPOINT DELETE
    @PostMapping("/delete")
    public Response<List<String>> delete(@RequestBody DeleteVo deleteVo) {
        
        List<String> response = new ArrayList<>();
        
        if (deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Utilisateur utilisateur = utilisateurRepository.findByCodeUtilisateur(code);
                    if (utilisateur != null) {
                        utilisateur.setDeleted(true);
                        utilisateur.setDeleteDate(deleteVo.getDeleteDate());
                        utilisateur.setDeleterCode(deleteVo.getDeleterCode());
                        utilisateurRepository.saveAndFlush(utilisateur);
                        response.add(utilisateur.getCodeUtilisateur());
                    }
                }
                
                log.info("Utilisateurs supprimés avec succès: " + response.toString());
                return new Response<>(response, HttpStatus.OK, "Utilisateurs supprimés avec succès");
                
            } catch (Exception e) {
                log.error("Echec lors de la suppression des utilisateurs", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, 
                    "Echec lors de la suppression des utilisateurs");
            }
        }
        
        return new Response<>(response, HttpStatus.BAD_REQUEST, "Aucun code fourni");
    }

    // ENDPOINT FIND BY CODE
    @GetMapping("/findByCode/{codeUtilisateur}")
    public Response<Utilisateur> findByCode(@PathVariable String codeUtilisateur) {
        
        Utilisateur result = null;
        
        try {
            result = utilisateurRepository.findByCodeUtilisateur(codeUtilisateur);
            
            if (result != null) {
                result.setPassword(null);
                result.setPin(null);
            }
            
            log.info("Utilisateur récupéré avec succès: " + result);
            return new Response<>(result, HttpStatus.OK, "Utilisateur récupéré avec succès");
            
        } catch (Exception e) {
            log.error("Echec lors de la récupération de l'utilisateur", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, 
                "Echec lors de la récupération de l'utilisateur");
        }
    }
}