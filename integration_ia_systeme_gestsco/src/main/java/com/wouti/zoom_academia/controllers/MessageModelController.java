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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.MessageModel;
import com.wouti.zoom_academia.repositories.MessageModelRepository;
import com.wouti.zoom_academia.transverse.TypeMessage;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;

@RestController
@RequestMapping("/messageModel")
public class MessageModelController {

	private static final Logger log = Logger.getLogger(MessageModel.class.getName());

	@Autowired
    public MessageModelRepository messageModelRepository;


	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;


    @PostMapping("/save")
	public Response<MessageModel> saveA(@RequestBody MessageModel messageModel){

    	MessageModel result = null;
 		try {
            messageModel.setCodeMessageModel(AppUtils.generateMessageModelCode());
 			result = messageModelRepository.save(messageModel);
 			log.info("MessageModel enregistré avec success" + result.toString());

			 // Piste d'audit — non bloquante
			 try {

			 } catch (Exception auditEx) {
			     log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			 }

 			return new Response<>(result,HttpStatus.OK, "MessageModel enregistré avec succes");
 		} catch (Exception e) {
 			log.error("Echec lors de l'enregistrement du MessageModel", e);
 			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du MessageModel");
 		}
    }

    @GetMapping("/findAll")
 	@ResponseBody
 	public Response<Iterable<MessageModel>> findAllQueryDsl(
 			@QuerydslPredicate(root = MessageModel.class) com.querydsl.core.types.Predicate predicate){

            Iterable<MessageModel> result = null;
 			try {
 				result = messageModelRepository.findAll(predicate);
 				log.info("MessageModels récupérés avec success "+result.toString());
 				return new Response<>(result,HttpStatus.OK, "MessageModels récupéré avec success");
 			} catch (Exception e) {
 				log.error("Echec lors de la récupération des MessageModels", e);
 				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des MessageModels");
 			}
 	}

    @GetMapping("/typeMessageModel/{type}")
   	public Response<MessageModel> findMessageModelByType(@PathVariable TypeMessage type){

       	MessageModel result = null;
    		try {
    			result = messageModelRepository.findMessageModelByType(type);
    			log.info("MessageModels récupérées avec success " + result.toString());
    			return new Response<>(result,HttpStatus.OK, "MessageModels récupérées avec succes");
    		} catch (Exception e) {
    			log.error("Echec lors de la récupération des MessageModels", e);
    			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des MessageModels");
    		}
     }

    @PostMapping("/update")
	public Response<MessageModel> update(@RequestBody MessageModel messageModel) {

		 MessageModel result = null;
			try {
				result = messageModelRepository.saveAndFlush(messageModel);
				log.info("MessageModel mise à jour avec success " + result.toString());
				return new Response<>(result,HttpStatus.OK, "MessageModel mise a jour avec succes");
			} catch (Exception e) {
				log.error("Echec lors de la mise a jour du MessageModel", e);
				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du MessageModel");
			}
	}

    @PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
			List<String> response;
			response = new ArrayList<>();

			if(deleteVo!= null && deleteVo.getCodes().length > 0) {
				try {
					for (String code : deleteVo.getCodes()) {

						MessageModel messageModel = messageModelRepository.findByCodeMessageModel(code);

						if (messageModel!= null) {
							messageModel.setDeleted(true);
							messageModel.setDeleteDate(deleteVo.getDeleteDate());
							messageModel.setDeleterCode(deleteVo.getDeleterCode());

							messageModelRepository.saveAndFlush(messageModel);
							response.add(messageModel.getCodeMessageModel());
						}
					}
					log.info("MessageModels supprimé avec success "+response.toString());
					return new Response<>(response,HttpStatus.OK, "MessageModels supprimés avec succes");
				} catch (Exception e) {
					log.error("Echec lors de la suppression des MessageModels", e);
					return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des MessageModels");
				}


			}
			return null;
	}

}
