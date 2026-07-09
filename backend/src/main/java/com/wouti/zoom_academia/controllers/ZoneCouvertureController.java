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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Predicate;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.ZoneCouverture;
import com.wouti.zoom_academia.repositories.ZoneCouvertureRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/zoneCouverture")
public class ZoneCouvertureController {

	private static final Logger log = Logger.getLogger(ZoneCouvertureController.class.getName());

	@Autowired
	private ZoneCouvertureRepository zoneCouvertureRepository;

	@Autowired
	private ZoomAcademiaBackApplication ZoomAcademia;

	@PostMapping("/save")
	public Response<ZoneCouverture>save(@RequestBody ZoneCouverture zoneCouverture) {

		ZoneCouverture result = null;
		try {
			zoneCouverture.setCodeZoneCouverture(AppUtils.generateZoneCouvertureCode());
			result = zoneCouvertureRepository.save(zoneCouverture);

		// Piste d'audit — non bloquante
		try {

		} catch (Exception auditEx) {
		    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
		}

			log.info("ZoneCouverture enregistrée avec success "+result.toString());
			return new Response<>(result,HttpStatus.OK, "ZoneCouverture enregistrée avec succes");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du ZoneCouverture", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la ZoneCouverture");
		}

	}

	@GetMapping("/findByCode")
	public Response<ZoneCouverture>findByC(@RequestParam String code) {

		ZoneCouverture result = null;
		try {
			result = zoneCouvertureRepository.findByCode(code);
			log.info("ZoneCouverture recupéré avec success "+result.toString());
			return new Response<>(result,HttpStatus.OK, "ZoneCouverture enregistrée avec succes");
		} catch (Exception e) {
			log.error("Echec lors de recuperation du ZoneCouverture", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la ZoneCouverture");
		}

	}


	@GetMapping("/findAll")
	public Response<Iterable<ZoneCouverture>> findAll( @QuerydslPredicate(root = ZoneCouverture.class) Predicate predicate){

		Iterable<ZoneCouverture> result = null;
		try {
			result = zoneCouvertureRepository.findAll(predicate);
			log.info("ZoneCouverture récupérée avec success "+result.toString());
			return new Response<>(result,HttpStatus.OK, "ZoneCouverture récupérée avec success");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des zoneCouverture", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des zoneCouvertures");
		}
	}


	@PostMapping("/update")
	public Response<ZoneCouverture> updateZoneCouverture(@RequestBody ZoneCouverture zoneCouverture) {
		ZoneCouverture result = null;

		try {
			result = zoneCouvertureRepository.saveAndFlush(zoneCouverture);

			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}

			log.info("Informations du zoneCouverture Modifiée avec success "+result.toString());
			return new Response<>(result,HttpStatus.OK, "ZoneCouverture modifié avec succes");
		} catch (Exception e) {
			log.error("Echec lors de la modification du zoneCouverture", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la modification du zoneCouverture");
		}

	}


    @PostMapping("/delete")
	 public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
			List<String> response;
			response = new ArrayList<>();

			if(deleteVo!= null && deleteVo.getCodes().length > 0) {
				try {
					for (String code : deleteVo.getCodes()) {

						ZoneCouverture zoneCouverture = zoneCouvertureRepository.findByCode(code);

						if (zoneCouverture!= null) {
							zoneCouverture.setDeleted(true);
							zoneCouverture.setDeleteDate(deleteVo.getDeleteDate());
							zoneCouverture.setDeleterCode(deleteVo.getDeleterCode());

							zoneCouvertureRepository.saveAndFlush(zoneCouverture);
							response.add(zoneCouverture.getCodeZoneCouverture());

							// Piste d'audit — non bloquante
							try {

							} catch (Exception auditEx) {
							    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
							}
						}
					}
					log.info("ZoneCouvertures supprimé avec success "+response.toString());
					return new Response<>(response,HttpStatus.OK, "ZoneCouvertures supprimés avec succes");
				} catch (Exception e) {
					log.error("Echec lors de la suppression des ZoneCouvertures", e);
					return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des ZoneCouvertures");
				}


			}
			return null;
		}
}
