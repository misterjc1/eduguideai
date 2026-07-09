// LOCAL
export const HTTP_HEADER = 'http://';
export const IP_ADDRESS = 'localhost:';
export const PORT = '8181';

// PROD (décommenter pour la production)
// export const HTTP_HEADER = 'http://';
// export const IP_ADDRESS = '51.77.245.237:';
// export const PORT = '7180';

// Parametre
export const FIND_ALL_PARAMETRE = HTTP_HEADER + IP_ADDRESS + PORT + '/parametre/findAll?isDeleted=false';
export const ADD_PARAMETRE = HTTP_HEADER + IP_ADDRESS + PORT + '/parametre/save';
export const DELETE_PARAMETRE = HTTP_HEADER + IP_ADDRESS + PORT + '/parametre/delete';
export const UPDATE_PARAMETRE = HTTP_HEADER + IP_ADDRESS + PORT + '/parametre/update';
export const FIND_PARAMETRE_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/parametre/findAll?codeParametre=';

// Utilisateur
export const GENERATE_PIN = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/generatePin';
export const GENERATE_PINUTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/generatePin';
export const FIND_ALL_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findAll?isDeleted=false';
export const SAVE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/save';
export const DELETE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/delete';
export const UPDATE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/update';
export const FIND_UTILISATEUR_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findAll?codeUtilisateur=';

// Prompt
export const FIND_ALL_PROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/prompt/findAll?isDeleted=false';
export const SAVE_PROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/prompt/save';
export const DELETE_PROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/prompt/delete';
export const UPDATE_PROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/prompt/update';
export const FIND_PROMPT_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/prompt/findAll?codePrompt=';

// Liaison
export const FIND_ALL_LIAISON = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/findAll?isDeleted=false';
export const SAVE_LIAISON = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/save';
export const DELETE_LIAISON = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/delete';
export const UPDATE_LIAISON = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/update';
export const FIND_LIAISON_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/findAll?codeLiaison=';
export const FIND_ALL_LIAISON_VALIDE = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/findAll?statutLiaison=VALIDE';
export const FIND_LIAISON_BY_CODE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/liaison/findLiaisonByCodeUtilisateur/';

// Niveau
export const FIND_ALL_NIVEAUINSCRIT = HTTP_HEADER + IP_ADDRESS + PORT + '/niveau/findAll?isDeleted=false';
export const SAVE_NIVEAU = HTTP_HEADER + IP_ADDRESS + PORT + '/niveau/save';
export const DELETE_NIVEAU = HTTP_HEADER + IP_ADDRESS + PORT + '/niveau/delete';
export const UPDATE_NIVEAU = HTTP_HEADER + IP_ADDRESS + PORT + '/niveau/update';
export const FIND_NIVEAU_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/niveau/findAll?codeNiveau=';

// Inscrit
export const FIND_ALL_INSCRIT = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/findAll?isDeleted=false';
export const SAVE_INSCRIT = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/save';
export const DELETE_INSCRIT = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/delete';
export const UPDATE_INSCRIT = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/update';
export const FIND_INSCRIT_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/findAll?codeInscrit=';
export const FIND_INSCRIT_BY_MATRICULE = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/findAll?matricule=';
export const FIND_NOTES_BY_MATRICULE = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/findNotes?matricule=';

// Donnees
export const FIND_ALL_DONNEES = HTTP_HEADER + IP_ADDRESS + PORT + '/donnees/findAll?isDeleted=false';
export const SAVE_DONNEES = HTTP_HEADER + IP_ADDRESS + PORT + '/donnees/save';
export const DELETE_DONNEES = HTTP_HEADER + IP_ADDRESS + PORT + '/donnees/delete';
export const UPDATE_DONNEES = HTTP_HEADER + IP_ADDRESS + PORT + '/donnees/update';
export const FIND_DONNEES_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/donnees/findAll?codeDonnees=';

// Cinematique
export const FIND_ALL_CINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/findAll?isDeleted=false';
export const SAVE_CINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/save';
export const DELETE_CINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/delete';
export const UPDATE_CINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/update';
export const FIND_CINEMATIQUE_BY_NIVEAU = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/findByNiveau/';
export const FIND_CINEMATIQUE_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/findAll?codeCinematique=';
export const FIND_CINEMATIQUE_IMAGE = HTTP_HEADER + IP_ADDRESS + PORT + '/cinematique/image?imageUrl=';
export const NIVEAU1 = "NIVEAU1";
export const NIVEAU2 = "NIVEAU2";
export const NIVEAU3 = "NIVEAU3";

// Template Prompt
export const FIND_ALL_TEMPLATEPROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/templatePrompt/findAll?isDeleted=false';
export const SAVE_TEMPLATEPROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/templatePrompt/save';
export const DELETE_TEMPLATEPROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/templatePrompt/delete';
export const UPDATE_TEMPLATEPROMPT = HTTP_HEADER + IP_ADDRESS + PORT + '/templatePrompt/update';
export const FIND_TEMPLATEPROMPT_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/templatePrompt/findAll?codeTemplatePrompt=';

// Reponse
export const FIND_ALL_REPONSE = HTTP_HEADER + IP_ADDRESS + PORT + '/reponse/findAll?isDeleted=false';
export const SAVE_REPONSE = HTTP_HEADER + IP_ADDRESS + PORT + '/reponse/save';
export const DELETE_REPONSE = HTTP_HEADER + IP_ADDRESS + PORT + '/reponse/delete';
export const UPDATE_REPONSE = HTTP_HEADER + IP_ADDRESS + PORT + '/reponse/update';
export const FIND_REPONSE_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/reponse/findAll?codeReponse=';

// Type Cinematique
export const FIND_ALL_TYPECINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/typeCinematique/findAll?isDeleted=false';
export const SAVE_TYPECINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/typeCinematique/save';
export const DELETE_TYPECINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/typeCinematique/delete';
export const UPDATE_TYPECINEMATIQUE = HTTP_HEADER + IP_ADDRESS + PORT + '/typeCinematique/update';
export const FIND_TYPECINEMATIQUE_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/typeCinematique/findAll?codeTypeCinematique=';

// Service (Noyau Global)
export const FIND_ALL_SERVICES = HTTP_HEADER + IP_ADDRESS + PORT + '/service/findAll?isDeleted=false';
export const SAVE_SERVICES = HTTP_HEADER + IP_ADDRESS + PORT + '/service/save';
export const DELETE_SERVICES = HTTP_HEADER + IP_ADDRESS + PORT + '/service/delete';
export const UPDATE_SERVICES = HTTP_HEADER + IP_ADDRESS + PORT + '/service/update';
export const FIND_SERVICES_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/service/findAll?codeService=';
export const FIND_SERVICES_LOGO = HTTP_HEADER + IP_ADDRESS + PORT + '/service/logo?imageUrl=';
export const AFFICHER_SERVICES_LOGO = HTTP_HEADER + IP_ADDRESS + PORT + '/service/logo?codeService=';

// Enums
export const FIND_ALL_NIVEAU = HTTP_HEADER + IP_ADDRESS + PORT + '/enum/niveau';
export const FIND_ALL_VARIABLES = HTTP_HEADER + IP_ADDRESS + PORT + '/enum/variable';
export const FIND_ALL_TYPESERVICES = HTTP_HEADER + IP_ADDRESS + PORT + '/enum/typeService';
export const FIND_ALL_STATUT_LIAISON = HTTP_HEADER + IP_ADDRESS + PORT + '/enum/statutLiaison';
export const FIND_ALL_TYPE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/enum/typeUtilisateur';

// Dashboard
export const DASHBOARD_STATS = HTTP_HEADER + IP_ADDRESS + PORT + '/dashboard/stats';

// Chatbot / Assistant Virtuel
export const CHATBOT_ASK        = HTTP_HEADER + IP_ADDRESS + PORT + '/chatbot/ask';
export const CHATBOT_QUESTIONS  = HTTP_HEADER + IP_ADDRESS + PORT + '/chatbot/predefinedQuestions';

// IA — nouveaux endpoints Gemini
export const AI_ANALYSE         = HTTP_HEADER + IP_ADDRESS + PORT + '/ai/analyse';
export const AI_ORIENTATION     = HTTP_HEADER + IP_ADDRESS + PORT + '/ai/orientation';
export const AI_SIMULATION      = HTTP_HEADER + IP_ADDRESS + PORT + '/ai/simulation';
export const AI_FRAUD_ANALYSIS  = HTTP_HEADER + IP_ADDRESS + PORT + '/ai/fraud-analysis';

// Career / Orientation existant
export const CAREER_RECOMMENDATIONS = HTTP_HEADER + IP_ADDRESS + PORT + '/career/generateCareerRecommendations';

// Simulation existant
export const SIMULATION_EFFORT = HTTP_HEADER + IP_ADDRESS + PORT + '/simulation/generateEffortResults';
