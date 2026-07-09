package com.wouti.zoom_academia.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.transverse.FonctionAgent;
import com.wouti.zoom_academia.transverse.Niveau;
import com.wouti.zoom_academia.transverse.OptionPaiement;
import com.wouti.zoom_academia.transverse.Sexe;
import com.wouti.zoom_academia.transverse.Statut;
import com.wouti.zoom_academia.transverse.StatutDisponibilite;
import com.wouti.zoom_academia.transverse.StatutLiaison;
import com.wouti.zoom_academia.transverse.TypeAnimation;
import com.wouti.zoom_academia.transverse.TypeModePaiement;
import com.wouti.zoom_academia.transverse.TypePaiement;
import com.wouti.zoom_academia.transverse.TypeService;
import com.wouti.zoom_academia.transverse.TypeUtilisateur;
import com.wouti.zoom_academia.transverse.Variables;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.EnumVo;


@RestController
@RequestMapping("/enum")
public class EnumController {
	@Value("${mt.application}")
    private String APP_NAME;



	private static final Logger log = Logger.getLogger(EnumController.class.getName());

   

    @GetMapping("/optionPaiment")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllOptionPaiment() {
        List<EnumVo> result = new ArrayList<>();

        try {
            OptionPaiement[] currentItem;

            // Utilisez une structure de contrôle pour définir currentItem en fonction de APP_CODE.
            switch (APP_NAME) {
                case "Djonan":
                    currentItem = new OptionPaiement[]{OptionPaiement.CASH, OptionPaiement.MOBILE_MONEY, OptionPaiement.CREDITS_CARD };
                    break;
                case "AssurFacil":
                    currentItem = new OptionPaiement[]{OptionPaiement.LIBRE, OptionPaiement.JOURNALIER, OptionPaiement.HEBDOMADAIRE, OptionPaiement.MENSUELLE, OptionPaiement.BIMENSUELLE, OptionPaiement.TRIMESTRIELLE};
                    break;
                default:
                    currentItem = new OptionPaiement[0];
            }

            // Remarque : Vous ne pouvez pas assigner directement un tableau à un autre.
            // Vous devez copier les éléments du tableau source dans le tableau de destination.
            List<OptionPaiement> objets = Arrays.asList(currentItem);

            for (OptionPaiement option : objets) {
                EnumVo val = new EnumVo();
                val.setKey(option.name());
                val.setValue(option.getLabel());
                result.add(val);
            }
            log.info("APPLI C: " + APP_NAME);
            log.info("Option Paiement récupéré avec succès: " + result.toString());
            return new Response<>(result, HttpStatus.OK, "Option Paiement récupéré avec succès");
        } catch (Exception e) {
            log.error("Échec lors de la récupération des Options de Paiement", e);
            return new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de la récupération des Options de Paiement");
        }
    }
    
    
    @GetMapping("/statutLiaison")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllStatutLiaison(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<StatutLiaison> objets = Arrays.asList(StatutLiaison.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("statutLiaison récupérés avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "statutLiaison récupérés avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des statutLiaison", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération de statutLiaison ");
 			}
 	}

    

    @GetMapping("/typeModePaiement")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllTypeModePaiement(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<TypeModePaiement> objets = Arrays.asList(TypeModePaiement.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Type Compte récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Type mode paiement récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Options de Paiement", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Types mode de paiement ");
 			}
 	}
    
    @GetMapping("/typeUtilisateur")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllTypeUtilisateur(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<TypeUtilisateur> objets = Arrays.asList(TypeUtilisateur.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Type Compte récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Type mode paiement récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Options de Paiement", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Types mode de paiement ");
 			}
 	}

    @GetMapping("/sexe")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllSexe(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<Sexe> objets = Arrays.asList(Sexe.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Sexe récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Sexe récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Sexes", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Sexes");
 			}
 	}
    
    
    @GetMapping("/typeService")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllTypeService(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<TypeService> objets = Arrays.asList(TypeService.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("TypeService récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "TypeService récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des TypeServices", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des TypeServices");
 			}
 	}
    
    @GetMapping("/variable")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllVariables(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<Variables> objets = Arrays.asList(Variables.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Variable récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Variables récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Variables", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Variable");
 			}
 	}


    
    @GetMapping("/niveau")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllNiveau(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<Niveau> objets = Arrays.asList(Niveau.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Niveau récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Niveau récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Niveaux", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Sexes");
 			}
 	}
    
    
    @GetMapping("/fonctionAgent")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllAgentFunction(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<FonctionAgent> objets = Arrays.asList(FonctionAgent.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("FonctionAgent récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "FonctionAgent récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération de FonctionAgent", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération  FonctionAgent");
 			}
 	}

    
    @GetMapping("/typeState")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllTypeState(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<Statut> objets = Arrays.asList(Statut.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Statut récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Type Statut récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération de Type Statut", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération  type Statut");
 			}
 	}
    
    @GetMapping("/typeAnimation")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllTypeAnimation(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<TypeAnimation> objets = Arrays.asList(TypeAnimation.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Type Animations récupéré avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Type Animations récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Types Animations", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération  types Animations ");
 			}
 	}


    @GetMapping("/typePaiement")
 	@ResponseBody
 	public Response<List<EnumVo>> findAllTypePaiement(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<TypePaiement> objets = Arrays.asList(TypePaiement.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Type de paiement récupérés avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Type de paiement récupérés avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des Types de paiement", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des types de paiement ");
 			}
 	}

    @GetMapping("/disponibilite")
 	@ResponseBody
 	public Response<List<EnumVo>> findTypeDisponibilite(){

            List<EnumVo> result = new ArrayList<>();

 		try {
            List<StatutDisponibilite> objets = Arrays.asList(StatutDisponibilite.values());

        	log.info(objets.get(0).getLabel());

            for (int i = 0; i < objets.size(); i++) {
            	EnumVo val = new EnumVo();
            	val.setKey(objets.get(i).name());
            	val.setValue(objets.get(i).getLabel());
            	log.info(val);
            	result.add(val);
            }
 				log.info("Etats de l'agent récupérés avec success"+result.toString());
 				return new Response<>(result,HttpStatus.OK, "Etats de l'agent  récupérés avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des etats de l'agent ", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des etats de l'agent  ");
 			}
 	}



}
