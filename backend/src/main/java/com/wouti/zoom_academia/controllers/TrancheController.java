package com.wouti.zoom_academia.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.Tranche;
import com.wouti.zoom_academia.repositories.TrancheRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/tranche")
public class TrancheController {

	private static final Logger log = Logger.getLogger(TrancheController.class.getName());

    @Autowired
    public TrancheRepository trancheRepository;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;


    @PostMapping("/save")
   	public Response<Tranche> saveA(@RequestBody Tranche tranche){

       	Tranche result = null;
    		try {
               tranche.setCodeTranche(AppUtils.generateTrancheCode());
    			result = trancheRepository.save(tranche);
    			log.info("Tranche enregistrée avec success " + result.toString());

				// Piste d'audit — non bloquante
				try {

				} catch (Exception auditEx) {
				    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
				}


    			return new Response<>(result,HttpStatus.OK, "Tranche enregistrée avec succes");
    		} catch (Exception e) {
    			log.error("Echec lors de l'enregistrement du Tranche", e);
    			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du Tranche");
    		}
       }

       @GetMapping("/findAll")
    	@ResponseBody
    	public Response<Iterable<Tranche>> findAllQueryDsl(
    			@QuerydslPredicate(root = Tranche.class) com.querydsl.core.types.Predicate predicate){

               Iterable<Tranche> result = null;
    			try {
    				result = trancheRepository.findAll(predicate);
    				log.info("Tranches récupérés avec success "+result.toString());
    				return new Response<>(result,HttpStatus.OK, "Tranches récupéré avec success");
    			} catch (Exception e) {
    				log.error("Echec lors de la récupération des Tranches", e);
    				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Tranches");
    			}
    	}

    @PostMapping("/update")
   	public Response<Tranche> update(@RequestBody Tranche tranche) {

   		 Tranche result = null;
   			try {
   				result = trancheRepository.saveAndFlush(tranche);
   				log.info("Tranche mise à jour avec success " + result.toString());
   				return new Response<>(result,HttpStatus.OK, "Tranche mise a jour avec succes");
   			} catch (Exception e) {
   				log.error("Echec lors de la mise a jour du Tranche", e);
   				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du Tranche");
   			}
   	}

    @PostMapping("/delete")
   	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
   			List<String> response;
   			response = new ArrayList<>();

   			if(deleteVo!= null && deleteVo.getCodes().length > 0) {
   				try {
   					for (String code : deleteVo.getCodes()) {

   						Tranche tranche = trancheRepository.findByCodeTranche(code);

   						if (tranche!= null) {
   							tranche.setDeleted(true);
   							tranche.setDeleteDate(deleteVo.getDeleteDate());
   							tranche.setDeleterCode(deleteVo.getDeleterCode());

   							trancheRepository.saveAndFlush(tranche);
   							response.add(tranche.getCodeTranche());
   						}
   					}
   					log.info("Tranches supprimé avec success "+response.toString());
   					return new Response<>(response,HttpStatus.OK, "Tranches supprimés avec succes");
   				} catch (Exception e) {
   					log.error("Echec lors de la suppression des Tranches", e);
   					return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des Tranches");
   				}


   			}
   			return null;
   		}
}
