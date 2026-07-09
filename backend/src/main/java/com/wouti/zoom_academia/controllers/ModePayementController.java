package com.wouti.zoom_academia.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.ModePayement;
import com.wouti.zoom_academia.entities.Transaction;
import com.wouti.zoom_academia.repositories.ModePayementRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;


@RestController
@RequestMapping("/mode-de-payement")
public class ModePayementController {


	private static final Logger log = Logger.getLogger(ModePayementController.class.getName());

	//CORIS MONEY
	private static final String COMO_CLIENT_ID = "CMTEST";
	private static final String COMO_SECRET = "$2a$10$TasnCPxIpJnlEsqnTpEJTO0GGXiiYJSjouyRR6wfd2AzMBGP6Qz36";
	private static final String COMO_PV = "0031682086";
	private static final String COMO_BASE_URL = "https://testbed.corismoney.com/external/v1/api/";
	private static final String COMO_SEND_OTP_URL = COMO_BASE_URL+"send-code-otp?codePays=226&telephone=";
	private static final String COMO_PAIEMENT_BIEN_URL = COMO_BASE_URL+"operations/paiement-bien?codePays=226&telephone=";

	@Autowired
	public ModePayementRepository modePayementRepository;
	@Autowired
	public TransactionController transactionController;

	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;


	@PostMapping("/save")
	public Response<ModePayement> saveA(@RequestBody ModePayement modePayement){

		ModePayement result = null;
		try {
			modePayement.setCodeModePayement(AppUtils.generateModePayementCode());
			if (modePayement.getLogo() != null) {
				if (modePayement.getLogo().length() > 0) {
					this.uploadImage(modePayement.getLogo(), modePayement.getCodeModePayement());
					modePayement.setLogo(modePayement.getCodeModePayement() + ".png");
				}
			}
			result = modePayementRepository.save(modePayement);
			log.info("Mode de payement enregistré avec success " + result.toString());

			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}


			return new Response<>(result,HttpStatus.OK, "Mode de payement enregistré avec succes");

		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement du mode de payement", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du mode de payement");
		}

	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<ModePayement>> findAllQueryDsl(
	@QuerydslPredicate(root = ModePayement.class) com.querydsl.core.types.Predicate predicate){

		Iterable<ModePayement> result = null;
		try {
			result = modePayementRepository.findAll(predicate);
			log.info("Modes de payement récupérés avec success "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Modes de payement récupérés avec success");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des modes de payement", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des modes de payement");
		}
	}

	@PostMapping("/update")
	public Response<ModePayement> update(@RequestBody ModePayement modePayement) {

		ModePayement result = null;
		Path imagesPath = Paths.get(AppUtils.MODE_PAYEMENT_DIRECTORY + modePayement.getCodeModePayement() + ".png");
		try {
			if (modePayement.getLogo() != null) {
				if (modePayement.getLogo().length() > 40) {
					try {
						Files.delete(imagesPath);
						System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
					} catch (Exception e) {
						log.error("Image non trouvée !");
					}
					this.uploadImage(modePayement.getLogo(), modePayement.getCodeModePayement());
					modePayement.setLogo(modePayement.getCodeModePayement() + ".png");
				}
			}
			result = modePayementRepository.saveAndFlush(modePayement);
			log.info("Mode de payement mise à jour avec success " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Mode de payement mise a jour avec succes");
		} catch (Exception e) {
			log.error("Echec lors de la mise a jour du mode de payement", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour du mode de payement");
		}
	}

	@PostMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam String imageValue, String imageName)
	{
		try
		{
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(imageValue);
			FileUtils.writeByteArrayToFile(new File(AppUtils.MODE_PAYEMENT_DIRECTORY + imageName + ".png"), bytes);
			return "success";
		}
		catch(Exception e)
		{
			return "error = "+e;
		}

	}

	@GetMapping("/logo")
	public Response<String> getLogo(@RequestParam String imageUrl)
	{

		String result = "";
		try {
			result= AppUtils.getImage(imageUrl, AppUtils.MODE_PAYEMENT_DIRECTORY);
			if (result.contains("error")){

				return new Response<>(result, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation du logo");

			}else {

				return new Response<>(result,HttpStatus.OK, "Logo recuperer");
			}

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de la recuperation du logo");

		}
	}

	@GetMapping("/como/sendOTP/{telephone}")
	public Response<String> sendCoMoOTP(@PathVariable String telephone) {

		String hashParam = DigestUtils.sha256Hex("226"+telephone+COMO_SECRET);

		// Création d'un objet de type HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("clientId", COMO_CLIENT_ID);
		headers.set("hashParam", hashParam);

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		try {

		String response = ZoomAcademia.restTemplate().postForObject(COMO_SEND_OTP_URL+telephone, entity, String.class);
		return new Response<>(response, HttpStatus.OK, "OTP envoye avec sucess");

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de l'appel de API");

		}
	}

	@PostMapping("/como/pay/{codeOTP}")
	public Response<Object> payByCoMo(@PathVariable String codeOTP,@RequestBody Transaction transaction) {

		String hashParam = DigestUtils.sha256Hex("226"+transaction.getNumeroCompte()+COMO_PV+transaction.getMontant()+codeOTP+COMO_SECRET);

		// Création d'un objet de type HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("clientId", COMO_CLIENT_ID);
		headers.set("hashParam", hashParam);

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		try {

			Object response = ZoomAcademia.restTemplate().postForObject(COMO_PAIEMENT_BIEN_URL+transaction.getNumeroCompte()+"&codePv="+COMO_PV+"&montant="+transaction.getMontant()+"&codeOTP="+codeOTP, entity, Object.class);

			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode objectNode = objectMapper.convertValue(response, ObjectNode.class);


			int code = objectNode.get("code").asInt();
			String message = objectNode.get("message").asText();
			String transactionId = objectNode.get("transactionId").asText();
			switch (code) {
			case 0:


				 transaction.setReferencePayement(transactionId);
				 transaction.setDateTransaction(new Date());
				 Response<Transaction> resp = transactionController.save(transaction);
				 if (resp.getStatus()==HttpStatus.OK ) {
					 return new Response<>(response, HttpStatus.OK, "Paiement effectue avec sucess");
				 }else {
					 return new Response<>(response, HttpStatus.NOT_MODIFIED, "Erreur lors de l'enregistrement du paiement");
				 }


			default:
				return new Response<>(response, HttpStatus.NOT_MODIFIED, "Erreur lors du paiement : "+message);
			}

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de l'appel de API");

		}
	}

	@PostMapping("/fakePaiement")
	public Response<Object> fakePaiement(@RequestBody Transaction transaction) {

		Object response = null;
		try {
			transaction.setReferencePayement(AppUtils.generateRandomNumber(8));
			 transaction.setDateTransaction(new Date());
			 Response<Transaction> resp = transactionController.save(transaction);
			 if (resp.getStatus()==HttpStatus.OK ) {
				 return new Response<>(response, HttpStatus.OK, "Paiement effectue avec sucess");
			 }else {
				 return new Response<>(response, HttpStatus.NOT_MODIFIED, "Erreur lors de l'enregistrement du paiement");
			 }

		} catch (Exception e) {
			return new Response<>("error = " + e, HttpStatus.NOT_MODIFIED, "Echec lors de l'appel de API");

		}
	}

}
