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
import com.querydsl.core.types.OrderSpecifier;
import com.wouti.zoom_academia.ZoomAcademiaBackApplication;
import com.wouti.zoom_academia.entities.QTransaction;
import com.wouti.zoom_academia.entities.Transaction;
import com.wouti.zoom_academia.repositories.StructureRepository;
import com.wouti.zoom_academia.repositories.TransactionRepository;
import com.wouti.zoom_academia.transverse.OptionPaiement;
import com.wouti.zoom_academia.transverse.StatutPayement;
import com.wouti.zoom_academia.transverse.StatutSouscription;
import com.wouti.zoom_academia.transverse.TypePaiement;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;
import com.wouti.zoom_academia.vo.AuditVo;
import com.wouti.zoom_academia.vo.DeleteVo;
import com.wouti.zoom_academia.vo.TransactionVo;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


	private static final Logger log = Logger.getLogger(TransactionController.class.getName());

	@Autowired
	public TransactionRepository transactionRepository;



	@Autowired
	public ZoomAcademiaBackApplication ZoomAcademia;

	QTransaction qTransaction  = QTransaction.transaction;
	OrderSpecifier<Long> orderSpecifier = qTransaction.id.desc();

	@PostMapping("/save")
	public Response<Transaction> save(@RequestBody Transaction transaction){

		Transaction result = null;

		try {

			transaction.setCodeTransaction(AppUtils.generateTransactionCode());
			transaction.setCreationDate(new Date());
			result = transactionRepository.save(transaction);
			log.info("Transaction enregistré avec success " + result.toString());
			// Piste d'audit — non bloquante
			try {

			} catch (Exception auditEx) {
			    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
			}

			return new Response<>(result,HttpStatus.OK, "Transaction enregistré avec succes");
		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la Transaction", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de la Transaction");


		}
	}



	@PostMapping("/payByMobile")
	public Response<Transaction> payByMobile(@RequestBody TransactionVo transactionVo){

		Transaction result = null;

		try {


			if( transactionVo.getRefPaiement()!=null && transactionVo.getIntituleModePaiement()!= null) {
				Transaction transactionToPay = new Transaction ();
			

				transactionToPay.setModePayement(transactionVo.getIntituleModePaiement());
				transactionToPay.setMontant(transactionVo.getMontant());
				transactionToPay.setNumeroCompte(transactionVo.getNumeroCompte());
				transactionToPay.setReferencePayement(transactionVo.getRefPaiement());
				transactionToPay.setDateTransaction(transactionVo.getDatePaiement());
				transactionToPay.setStatutPayement(StatutPayement.réussie);
				transactionToPay.setTypePaiement(TypePaiement.PAIEMENT);
				transactionToPay.setCreatorCode("MOBILE-SMS");

				return save(transactionToPay);

			}else {
				log.error("Echec lors de l'enregistrement de la Transaction");

				// Piste d'audit — non bloquante
				try {

				} catch (Exception auditEx) {
				    log.warn("Piste d'audit non disponible : " + auditEx.getMessage());
				}

				return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du paiement");
			}


		} catch (Exception e) {
			log.error("Echec lors de l'enregistrement de la Transaction", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement du paiement");


		}

	}

	@GetMapping("/findAll")
	@ResponseBody
	public Response<Iterable<Transaction>> findAllQueryDsl(
			@QuerydslPredicate(root = Transaction.class) com.querydsl.core.types.Predicate predicate){

		Iterable<Transaction> result = null;
		try {
			result = transactionRepository.findAll(predicate,new OrderSpecifier[]{orderSpecifier});
			log.info("Transactions récupérés avec success "+result.toString());
			return new Response<>(result,HttpStatus.OK, "Transactions récupéré avec success");
		} catch (Exception e) {
			log.error("Echec lors de la récupération des Transactions", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la récupération des Transactions");
		}
	}

	@PostMapping("/update")
	public Response<Transaction> update(@RequestBody Transaction transaction) {

		Transaction result = null;
		try {
			result = transactionRepository.saveAndFlush(transaction);
			log.info("Transaction mise à jour avec success " + result.toString());
			return new Response<>(result,HttpStatus.OK, "Transaction mise a jour avec succes");
		} catch (Exception e) {
			log.error("Echec lors de la mise a jour de la Transaction", e);
			return new Response<>(result,HttpStatus.NOT_MODIFIED, "Echec lors de la mise a jour de la Transaction");
		}
	}

	@PostMapping("/delete")
	public Response<List<String>> delete(@RequestBody DeleteVo deleteVo){
		List<String> response;
		response = new ArrayList<>();

		if(deleteVo!= null && deleteVo.getCodes().length > 0) {
			try {
				for (String code : deleteVo.getCodes()) {

					Transaction transaction = transactionRepository.findByCodeTransaction(code);

					if (transaction!= null) {
						transaction.setDeleted(true);
						transaction.setDeleteDate(deleteVo.getDeleteDate());
						transaction.setDeleterCode(deleteVo.getDeleterCode());

						transactionRepository.saveAndFlush(transaction);
						response.add(transaction.getCodeTransaction());
					}
				}
				log.info("Transactions supprimé avec success "+response.toString());
				return new Response<>(response,HttpStatus.OK, "Transactions supprimées avec succes");
			} catch (Exception e) {
				log.error("Echec lors de la suppression des Transactions", e);
				return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de la suppression des Transactions");
			}


		}
		return null;
	}

	
	@GetMapping("/findRechargeByAgent/{numeroCompte}")
	public Response<List<Transaction>> findRechargeByAgent(@PathVariable String numeroCompte) {
		
		List<Transaction> result = null;
		try {
			
			result = transactionRepository.findRechargeByAgent(numeroCompte);
			log.info("Transaction récupéré avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "Transaction récupéré avec succes");
		} catch (Exception e) {

			log.error("Echec lors de la récupération de la Transaction ", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED,
					"Echec lors de la récupération de la Transaction");
		}
	}
	
	@GetMapping("/findRetraitByAgent/{numeroCompte}")
	public Response<List<Transaction>> findRetraitByAgent(@PathVariable String numeroCompte) {
		
		List<Transaction> result = null;
		try {
			
			result = transactionRepository.findRetraitByAgent(numeroCompte);
			log.info("Transaction récupéré avec success " + result.toString());
			return new Response<>(result, HttpStatus.OK, "Transaction récupéré avec succes");
		} catch (Exception e) {

			log.error("Echec lors de la récupération de la Transaction ", e);
			return new Response<>(result, HttpStatus.NOT_MODIFIED,
					"Echec lors de la récupération de la Transaction");
		}
	}




}
