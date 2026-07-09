package com.wouti.zoom_academia.controllers;



import java.util.List;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.entities.Notification;
import com.wouti.zoom_academia.repositories.NotificationRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.DeleteVo;






@RestController
@RequestMapping("/notification")
public class NotificationController {

	private static final Logger log = Logger.getLogger(NotificationController.class.getName());
	
	@Autowired
	public NotificationRepository notificationRepository;


	@PostMapping("/save")
	public Response<Notification> save(@RequestBody Notification notification){

		Notification result = null;
		try {

			notification.setCodeNotification(AppUtils.generateNotificationCode());
			notification.setCreationDate(new Date());
			notification.setCreatorCode("system");
			result = notificationRepository.save(notification);
			
			log.info("Notification enregistrée avec succès " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Notification enregistrée avec succès");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la notification", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la notification");
		}
	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Notification>> findAllQueryDsl(
	@QuerydslPredicate(root = Notification.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Notification> result = null;
		try {
			result = notificationRepository.findAll(predicate);
			log.info("Notification récupérées avec succèss "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Notification récupérées avec succèss");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Notification", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Notification");
		}
	}

	@PostMapping("/update")
	public ResponseEntity<Response<Notification>> update(@RequestBody Notification notification) {
        Notification result = null;
        try {
            notification.setUpdateDate(new Date()); // Update the updateDate
            result = notificationRepository.saveAndFlush(notification);
            log.info("Notifications mis à jour avec succès : " + result.toString());
            return new ResponseEntity<>(new Response<>(result, HttpStatus.OK, "Notifications mis à jour avec succès"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Échec lors de la mise à jour des notifications", e);
            return new ResponseEntity<>(new Response<>(result, HttpStatus.NOT_MODIFIED, "Échec lors de la mise à jour des notifications"), HttpStatus.NOT_MODIFIED);
        }
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
        List<String> response = new ArrayList<>();
        if (deleteVo != null && deleteVo.getCodes().length > 0) {
            try {
                for (String code : deleteVo.getCodes()) {
                    Notification notification = notificationRepository.findByCodeNotification(code);
                    if (notification != null) {
                    	notification.setDeleted(true);
                    	notification.setDeleteDate(deleteVo.getDeleteDate());
                    	notification.setDeleterCode(deleteVo.getDeleterCode());
                    	notificationRepository.saveAndFlush(notification);
                        response.add(notification.getCodeNotification());
                    }
                }
                log.info("notification supprimés avec succès " + response.toString());
                return new Response<>(response, HttpStatus.OK, "notification supprimés avec succès");
            } catch (Exception e) {
                log.error("Echec lors de la suppression des notification", e);
                return new Response<>(response, HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des notification");
            }
        }
        return new Response<>(response, HttpStatus.NOT_MODIFIED, "Aucun code fourni pour suppression");
    }
}

