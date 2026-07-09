package com.wouti.zoom_academia.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {



//    @Value("${mt.application.upload.directory}")
//    public static String UPLOAD_DIRECTORY;
//    @Value("${mt.application.audit.url}")
//    public static String AUDIT_URL;
//    @Value("${mt.application.notification.url}")
//    public static String NOTIFICATION_URL;

	//Nouvelle Logique
	//@Value("${mt.application.upload.directory}")
    //private String upload_directory;

    @Value("${mt.application.audit.url}")
    private String auditURL;

    @Value("${mt.application.notification.url}")
    private String notificationURL;


    public static final String FIRE_BASE_KEY = "C:/Dev/keys";
    //public static String UPLOAD_DIRECTORY;


	public static final String UPLOAD_DIRECTORY = "C:/Dev/uploads/Djonan";
	public static final String LOGO_DIRECTORY = "C:\\Users\\jclau\\int_ia";


	public static final String STRUCT_LOGO_DIRECTORY = UPLOAD_DIRECTORY +"/structure/logo/";
	
	public static final String CNMT_LOGO_DIRECTORY = UPLOAD_DIRECTORY +"/cinematique/logo/";
	
	public static final String SERV_LOGO_DIRECTORY = LOGO_DIRECTORY +"/service/logo/";


	public static final String AGENT_DOCUMENTS_DIRECTORY = UPLOAD_DIRECTORY +"/agent/";

	public static final String CATEGORIE_LOGO_DIRECTORY = UPLOAD_DIRECTORY +"/categorie/logo/";

	public static final String PRODUCT_DIRECTORY = UPLOAD_DIRECTORY +"/produits/";

	public static final String PRODUCT_LOGO_DIRECTORY = UPLOAD_DIRECTORY +"/produits/logo";

	public static final String DOCUMENT_DIRECTORY = UPLOAD_DIRECTORY +"/documents/";

	public static final String ANIMATION_DIRECTORY = UPLOAD_DIRECTORY +"/animation/";

	public static final String MODE_PAYEMENT_DIRECTORY = UPLOAD_DIRECTORY +"/mode-de-payement/";

	public static final String STRUCTURE_DIRECTORY = UPLOAD_DIRECTORY +"/structure/";



	//public static final String CODE_STRUCTURE_DJONAN = "ASFACC-DJONAN";
	public static final String CODE_STRUCTURE_DJONAN = "DJOSTR231991021331959";
	public static final String CODE_TYPE_PRIMAIRE_LIVRAISON = "ASFTYC142021721832212";
	public static final String CODE_TYPE_PRIMAIRE_TAXI = "DJOTYC20111217084817";
	public static final String CODE_TYPE_PRIMAIRE_LOCATION = "DJOTYC184151218231809";


	@Value("${mt.application.code}")
	private String appCode;

	public static final String APP_CODE = "IIA";
    public static String NOTIFICATION_URL;
    public static String SAVE_AUDIT;


    public String getAuditURL() {
        return auditURL.trim();
    }



//	@PostConstruct
//    public void init() {
//        APP_CODE = appCode.trim();
//
//        SAVE_AUDIT = getAuditURL() + "/audit/save";
//
//    }

	//1- Ajout du code special
	private static final String USER_CODE = "USR";
	private static final String STRUCTURE_CODE = "STR";
	private static final String PRODUITS_CODE = "PRO";
	private static final String CLIENTS_CODE = "CLI";
	private static final String SOUSCRIPTIONS_CODE = "SUB";
	private static final String TRANSACTIONS_CODE = "TRA";
	private static final String TRANCHES_CODE = "TCH";
	private static final String MODE_PAYEMENT_CODE = "MOD";
	private static final String ECHEANCIER_CODE = "ECH";
	private static final String CATEGORIES_CODE = "CAT";
	private static final String TYPE_CATEGORIES_CODE = "TYC";
	private static final String TYPE_DOCUMENT_CODE = "TDO";
	private static final String TYPE_SOUSCRIPTIONS_CODE = "TYS";
	private static final String DOCUMENTS_CODE = "DOC";
	private static final String IMAGE_CODE = "IMG";
	private static final String MESSAGE_CODE= "MSG";
	private static final String MESSAGEMODEL_CODE = "MOD";
	private static final String CONTRATVENTE_CODE = "CRV";
	private static final String ECONSTAT_CODE = "ECS";
	private static final String DEMANDE_CODE = "DMD";
	private static final String PRODUIT_STRUTURE_CODE = "PRS";
	private static final String COMPTE_CODE = "ACC";
	private static final String REMISE_CODE = "REM";
	private static final String SURCHARGE_CODE = "SUR";
	private static final String AGENT_CODE = "AGT";
	//private static final String PERSONNE_CODE = "PER";
	private static final String TERMINAL_CODE = "TER";
	private static final String TERMINAL_AFFECTATION_CODE = "TAF";
	private static final String TRAJECTOIRE = "TRJ";
	private static final String POSITION = "PST";
	private static final String ZONE_COUVERTURE_CODE = "ZNC";
	private static final String ENGIN_CODE = "ENG";
	private static final String ANIMATION_CODE = "ANC";
	private static final String EVALUATION_CODE = "EVC";
	
	//private static final String USER_CODE = "USR";
	//private static final String CATEGORIES_CODE = "CAT";
	private static final String PERSONNE_CODE = "PERS";
	private static final String PROMPT_CODE = "PROM";
	private static final String NOTIFICATION_CODE = "NOTE";
	private static final String LIGNEPROMPT_CODE = "LPROM";
	private static final String SERVICE_CODE = "SERV";
	private static final String CINEMATIQUE_CODE = "CNMT";
	private static final String PARAMETRE_CODE = "PARAM";
	private static final String LIGNEPERSONNE_CODE = "LPERS";
	private static final String TYPECINEMATIQUE_CODE = "TCNMT";
	private static final String TEMPLATEPROMPT_CODE = "TPROM";
	
	private static final String UTILISATEUR_CODE = "USR";
	private static final String LIAISON_CODE = "LS";
	private static final String INSCRIT_CODE = "INS";
	private static final String DONNEES_CODE = "DNN";
	private static final String REPONSE_CODE = "REP";
	private static final String NIVEAU_CODE = "NIV";

	private static final String PROFIL_CODE = "PRF";
	private static final String SESSION_CODE = "SES";
	// Ajouter ces méthodes
	public static String generateProfilCode() {
	    return APP_CODE.concat(PROFIL_CODE).concat(randomString(10));
	}

	public static String generateSessionCode() {
	    return APP_CODE.concat(SESSION_CODE).concat(randomString(10));
	}





	//URL pour Audit
//	private static final String  AUDIT_HTTP_HEADER = "http://";
//	private static final String  AUDIT_IP_ADDRESS = "localhost:";
//	private static final String  AUDIT_PORT = "9595";
//	public static final String  SAVE_AUDIT = AUDIT_HTTP_HEADER + AUDIT_IP_ADDRESS + AUDIT_PORT+ "/audit/save";
	//public static String  SAVE_AUDIT = AUDIT_URL+ "/audit/save";

	//URL pour notification
	//private static final String  NOTIFICATION_HTTP_HEADER = "http://";
	//private static final String  NOTIFICATION_IP_ADDRESS = "localhost:";
	//private static final String  NOTIFICATION_PORT = "8181";
	//public static final String  SEND_NOTIFICATION = NOTIFICATION_HTTP_HEADER + NOTIFICATION_IP_ADDRESS + NOTIFICATION_PORT + "/notification/submitjob";
	public static final String  SEND_NOTIFICATION = NOTIFICATION_URL+ "/notification/submitjob";
	  @Value("${mt.application.notification.sender}")
	    public static String SENDER_NAME;





    private static final String SPECIAL_CHARACTERS = "!@#$%&*";
    private static final String UPPER_CASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";

    public static final Random random = new Random();



    public static String randomString(int length) {
		StringBuilder randomString = new StringBuilder();
		int val;
		for (int i = 0; i < length; ++i) {
			val = random.nextInt(25);
			randomString.append(val);
		}
		return randomString.toString().toUpperCase();
	}


	public static String generateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }


	public static String generateUserCode() {
		return APP_CODE.concat(USER_CODE).concat(randomString(10));
	}
	public static String getRandomNumber(int digCount) {
	    StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++) {
			sb.append((char)('0' + random.nextInt(10)));
		}
	    return sb.toString();
	}

	//2- Creation de la fonction de generation du code
	public static String generateAgentPin(){
		return getRandomNumber(4);
	}

	public static String generateCodeEngin() {
		return APP_CODE.concat(ENGIN_CODE).concat(randomString(10));
	}



	public static String generateZoneCouvertureCode() {
		return APP_CODE.concat(ZONE_COUVERTURE_CODE).concat(randomString(10));
	}
	public static String generatePositionCode() {
		return APP_CODE.concat(POSITION).concat(randomString(10));
	}

	public static String generateTrajectoireCode() {
		return APP_CODE.concat(TRAJECTOIRE).concat(randomString(10));
	}

	public static String generateAgentCode() {
		return APP_CODE.concat(AGENT_CODE).concat(randomString(10));
	}
	public static String generateTerminal() {
		return APP_CODE.concat(TERMINAL_CODE).concat(randomString(10));
	}
	public static String generateTerminalAffect() {
		return APP_CODE.concat(TERMINAL_AFFECTATION_CODE).concat(randomString(10));
	}
	public static String generatePersonne() {
		return APP_CODE.concat(PERSONNE_CODE).concat(randomString(10));
	}

	public static String generateProduitCode() {
		return APP_CODE.concat(PRODUITS_CODE).concat(randomString(10));
	}

	public static String generateClientCode() {
		return APP_CODE.concat(CLIENTS_CODE).concat(randomString(10));
	}

	public static String generateSouscriptionCode() {
		return APP_CODE.concat(SOUSCRIPTIONS_CODE).concat(randomString(10));
	}

	public static String generateTransactionCode() {
		return APP_CODE.concat(TRANSACTIONS_CODE).concat(randomString(10));
	}

	public static String generateTrancheCode() {
		return APP_CODE.concat(TRANCHES_CODE).concat(randomString(10));
	}

	public static String generateModePayementCode() {
		return APP_CODE.concat(MODE_PAYEMENT_CODE).concat(randomString(10));
	}

	public static String generateEcheancierCode() {
		return APP_CODE.concat(ECHEANCIER_CODE).concat(randomString(10));
	}

	public static String generateCategorieCode() {
		return APP_CODE.concat(CATEGORIES_CODE).concat(randomString(10));
	}

	public static String generateTypeCategorieCode() {
		return APP_CODE.concat(TYPE_CATEGORIES_CODE).concat(randomString(10));
	}

	public static String generateTypeDocumentCode() {
		return APP_CODE.concat(TYPE_DOCUMENT_CODE).concat(randomString(10));
	}

	public static String generateTypeSouscriptionCode() {
		return APP_CODE.concat(TYPE_SOUSCRIPTIONS_CODE).concat(randomString(10));
	}

	public static String generateDocumentCode() {
		return APP_CODE.concat(DOCUMENTS_CODE).concat(randomString(10));
	}

	public static String generateStructureCode() {
		return APP_CODE.concat(STRUCTURE_CODE).concat(randomString(10));
	}

	public static String generateImageCode() {
		return APP_CODE.concat(IMAGE_CODE).concat(randomString(10));
	}

	public static String generateMessageCode() {
		return APP_CODE.concat(MESSAGE_CODE).concat(randomString(10));
	}

	public static String generateMessageModelCode() {
		return APP_CODE.concat(MESSAGEMODEL_CODE).concat(randomString(10));
	}

	public static String generateContratVenteCode() {
		return APP_CODE.concat(CONTRATVENTE_CODE).concat(randomString(10));
	}

	public static String generateEconstatCode() {
		return APP_CODE.concat(ECONSTAT_CODE).concat(randomString(10));
	}

	public static String generateDemandeRetraitCode() {
		return APP_CODE.concat(DEMANDE_CODE).concat(randomString(10));
	}

	public static String generateProduitStructureCode() {
		return APP_CODE.concat(PRODUIT_STRUTURE_CODE).concat(randomString(10));
	}

	public static String generateCompteCode() {
		return APP_CODE.concat(COMPTE_CODE).concat(randomString(10));
	}

	public static String generateRemiseCode() {
		return APP_CODE.concat(REMISE_CODE).concat(randomString(10));
	}

	public static String generateSurchargeCode() {
		return APP_CODE.concat(SURCHARGE_CODE).concat(randomString(10));
	}

	public static String generateAnimationCode() {
		return APP_CODE.concat(ANIMATION_CODE).concat(randomString(10));
	}

	public static String generateEvaluationCode() {
		return APP_CODE.concat(EVALUATION_CODE).concat(randomString(10));
	}
	
	
	
	public static String generatePersonneCode() {
		return APP_CODE.concat(PERSONNE_CODE).concat(randomString(10));
	}
	public static String generatePromptCode() {
		return APP_CODE.concat(PROMPT_CODE).concat(randomString(10));
	}
	
	public static String generateNotificationCode() {
		return APP_CODE.concat(NOTIFICATION_CODE).concat(randomString(10));
	}
	
	public static String generateLignePromptCode() {
		return APP_CODE.concat(LIGNEPROMPT_CODE).concat(randomString(10));
	}
	
	public static String generateServiceCode() {
		return APP_CODE.concat(SERVICE_CODE).concat(randomString(10));
	}
	
	public static String generateCinematiqueCode() {
		return APP_CODE.concat(CINEMATIQUE_CODE).concat(randomString(10));
	}
	
	public static String generateParametreCode() {
		return APP_CODE.concat(PARAMETRE_CODE).concat(randomString(10));
	}
	public static String generateLignePersonneCode() {
		return APP_CODE.concat(LIGNEPERSONNE_CODE).concat(randomString(10));
	}
	public static String generateTypeCinematiqueCode() {
		return APP_CODE.concat(TYPECINEMATIQUE_CODE).concat(randomString(10));
	}
	public static String generateTemplatePromptCode() {
		return APP_CODE.concat(TEMPLATEPROMPT_CODE).concat(randomString(10));
	}
	public static String generateDonneesCode() {
		return APP_CODE.concat(DONNEES_CODE).concat(randomString(10));
	}
	public static String generateUtilisateurCode() {
		return APP_CODE.concat(UTILISATEUR_CODE).concat(randomString(10));
	}
	public static String generateInscritCode() {
		return APP_CODE.concat(INSCRIT_CODE).concat(randomString(10));
	}
	public static String generateLiaisonCode() {
		return APP_CODE.concat(LIAISON_CODE).concat(randomString(10));
	}
	public static String generateReponseCode() {
		return APP_CODE.concat(REPONSE_CODE).concat(randomString(10));
	}
	public static String generateNiveau() {
		return APP_CODE.concat(NIVEAU_CODE).concat(randomString(10));
	}
	
	public static String getImage( String name , String directory)
	{
		try
		{
			Path path = Paths.get(directory + name);
			byte[] data = Files.readAllBytes(path);
			Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(data);

		}
		catch(Exception e)
		{
			return  "error = "+e;

		}
	}

}
