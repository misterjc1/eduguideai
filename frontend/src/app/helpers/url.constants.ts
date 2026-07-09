import { environment } from '../../environments/environment';

// URL de base de l'API, definie par environment.ts (dev) / environment.prod.ts (prod)
export const API_BASE_URL = environment.apiBaseUrl;

// Parametre
export const FIND_ALL_PARAMETRE = API_BASE_URL + '/parametre/findAll?isDeleted=false';
export const ADD_PARAMETRE = API_BASE_URL + '/parametre/save';
export const DELETE_PARAMETRE = API_BASE_URL + '/parametre/delete';
export const UPDATE_PARAMETRE = API_BASE_URL + '/parametre/update';
export const FIND_PARAMETRE_BY_CODE = API_BASE_URL + '/parametre/findAll?codeParametre=';

// Utilisateur
export const GENERATE_PIN = API_BASE_URL + '/utilisateur/generatePin';
export const GENERATE_PINUTILISATEUR = API_BASE_URL + '/utilisateur/generatePin';
export const FIND_ALL_UTILISATEUR = API_BASE_URL + '/utilisateur/findAll?isDeleted=false';
export const SAVE_UTILISATEUR = API_BASE_URL + '/utilisateur/save';
export const DELETE_UTILISATEUR = API_BASE_URL + '/utilisateur/delete';
export const UPDATE_UTILISATEUR = API_BASE_URL + '/utilisateur/update';
export const FIND_UTILISATEUR_BY_CODE = API_BASE_URL + '/utilisateur/findAll?codeUtilisateur=';

// Prompt
export const FIND_ALL_PROMPT = API_BASE_URL + '/prompt/findAll?isDeleted=false';
export const SAVE_PROMPT = API_BASE_URL + '/prompt/save';
export const DELETE_PROMPT = API_BASE_URL + '/prompt/delete';
export const UPDATE_PROMPT = API_BASE_URL + '/prompt/update';
export const FIND_PROMPT_BY_CODE = API_BASE_URL + '/prompt/findAll?codePrompt=';

// Liaison
export const FIND_ALL_LIAISON = API_BASE_URL + '/liaison/findAll?isDeleted=false';
export const SAVE_LIAISON = API_BASE_URL + '/liaison/save';
export const DELETE_LIAISON = API_BASE_URL + '/liaison/delete';
export const UPDATE_LIAISON = API_BASE_URL + '/liaison/update';
export const FIND_LIAISON_BY_CODE = API_BASE_URL + '/liaison/findAll?codeLiaison=';
export const FIND_ALL_LIAISON_VALIDE = API_BASE_URL + '/liaison/findAll?statutLiaison=VALIDE';
export const FIND_LIAISON_BY_CODE_UTILISATEUR = API_BASE_URL + '/liaison/findLiaisonByCodeUtilisateur/';

// Niveau
export const FIND_ALL_NIVEAUINSCRIT = API_BASE_URL + '/niveau/findAll?isDeleted=false';
export const SAVE_NIVEAU = API_BASE_URL + '/niveau/save';
export const DELETE_NIVEAU = API_BASE_URL + '/niveau/delete';
export const UPDATE_NIVEAU = API_BASE_URL + '/niveau/update';
export const FIND_NIVEAU_BY_CODE = API_BASE_URL + '/niveau/findAll?codeNiveau=';

// Inscrit
export const FIND_ALL_INSCRIT = API_BASE_URL + '/inscrit/findAll?isDeleted=false';
export const SAVE_INSCRIT = API_BASE_URL + '/inscrit/save';
export const DELETE_INSCRIT = API_BASE_URL + '/inscrit/delete';
export const UPDATE_INSCRIT = API_BASE_URL + '/inscrit/update';
export const FIND_INSCRIT_BY_CODE = API_BASE_URL + '/inscrit/findAll?codeInscrit=';
export const FIND_INSCRIT_BY_MATRICULE = API_BASE_URL + '/inscrit/findAll?matricule=';
export const FIND_NOTES_BY_MATRICULE = API_BASE_URL + '/inscrit/findNotes?matricule=';

// Donnees
export const FIND_ALL_DONNEES = API_BASE_URL + '/donnees/findAll?isDeleted=false';
export const SAVE_DONNEES = API_BASE_URL + '/donnees/save';
export const DELETE_DONNEES = API_BASE_URL + '/donnees/delete';
export const UPDATE_DONNEES = API_BASE_URL + '/donnees/update';
export const FIND_DONNEES_BY_CODE = API_BASE_URL + '/donnees/findAll?codeDonnees=';

// Cinematique
export const FIND_ALL_CINEMATIQUE = API_BASE_URL + '/cinematique/findAll?isDeleted=false';
export const SAVE_CINEMATIQUE = API_BASE_URL + '/cinematique/save';
export const DELETE_CINEMATIQUE = API_BASE_URL + '/cinematique/delete';
export const UPDATE_CINEMATIQUE = API_BASE_URL + '/cinematique/update';
export const FIND_CINEMATIQUE_BY_NIVEAU = API_BASE_URL + '/cinematique/findByNiveau/';
export const FIND_CINEMATIQUE_BY_CODE = API_BASE_URL + '/cinematique/findAll?codeCinematique=';
export const FIND_CINEMATIQUE_IMAGE = API_BASE_URL + '/cinematique/image?imageUrl=';
export const NIVEAU1 = "NIVEAU1";
export const NIVEAU2 = "NIVEAU2";
export const NIVEAU3 = "NIVEAU3";

// Template Prompt
export const FIND_ALL_TEMPLATEPROMPT = API_BASE_URL + '/templatePrompt/findAll?isDeleted=false';
export const SAVE_TEMPLATEPROMPT = API_BASE_URL + '/templatePrompt/save';
export const DELETE_TEMPLATEPROMPT = API_BASE_URL + '/templatePrompt/delete';
export const UPDATE_TEMPLATEPROMPT = API_BASE_URL + '/templatePrompt/update';
export const FIND_TEMPLATEPROMPT_BY_CODE = API_BASE_URL + '/templatePrompt/findAll?codeTemplatePrompt=';

// Reponse
export const FIND_ALL_REPONSE = API_BASE_URL + '/reponse/findAll?isDeleted=false';
export const SAVE_REPONSE = API_BASE_URL + '/reponse/save';
export const DELETE_REPONSE = API_BASE_URL + '/reponse/delete';
export const UPDATE_REPONSE = API_BASE_URL + '/reponse/update';
export const FIND_REPONSE_BY_CODE = API_BASE_URL + '/reponse/findAll?codeReponse=';

// Type Cinematique
export const FIND_ALL_TYPECINEMATIQUE = API_BASE_URL + '/typeCinematique/findAll?isDeleted=false';
export const SAVE_TYPECINEMATIQUE = API_BASE_URL + '/typeCinematique/save';
export const DELETE_TYPECINEMATIQUE = API_BASE_URL + '/typeCinematique/delete';
export const UPDATE_TYPECINEMATIQUE = API_BASE_URL + '/typeCinematique/update';
export const FIND_TYPECINEMATIQUE_BY_CODE = API_BASE_URL + '/typeCinematique/findAll?codeTypeCinematique=';

// Service (Noyau Global)
export const FIND_ALL_SERVICES = API_BASE_URL + '/service/findAll?isDeleted=false';
export const SAVE_SERVICES = API_BASE_URL + '/service/save';
export const DELETE_SERVICES = API_BASE_URL + '/service/delete';
export const UPDATE_SERVICES = API_BASE_URL + '/service/update';
export const FIND_SERVICES_BY_CODE = API_BASE_URL + '/service/findAll?codeService=';
export const FIND_SERVICES_LOGO = API_BASE_URL + '/service/logo?imageUrl=';
export const AFFICHER_SERVICES_LOGO = API_BASE_URL + '/service/logo?codeService=';

// Enums
export const FIND_ALL_NIVEAU = API_BASE_URL + '/enum/niveau';
export const FIND_ALL_VARIABLES = API_BASE_URL + '/enum/variable';
export const FIND_ALL_TYPESERVICES = API_BASE_URL + '/enum/typeService';
export const FIND_ALL_STATUT_LIAISON = API_BASE_URL + '/enum/statutLiaison';
export const FIND_ALL_TYPE_UTILISATEUR = API_BASE_URL + '/enum/typeUtilisateur';

// Dashboard
export const DASHBOARD_STATS = API_BASE_URL + '/dashboard/stats';

// Chatbot / Assistant Virtuel
export const CHATBOT_ASK        = API_BASE_URL + '/chatbot/ask';
export const CHATBOT_QUESTIONS  = API_BASE_URL + '/chatbot/predefinedQuestions';

// IA — nouveaux endpoints Gemini
export const AI_ANALYSE         = API_BASE_URL + '/ai/analyse';
export const AI_ORIENTATION     = API_BASE_URL + '/ai/orientation';
export const AI_SIMULATION      = API_BASE_URL + '/ai/simulation';
export const AI_FRAUD_ANALYSIS  = API_BASE_URL + '/ai/fraud-analysis';

// Career / Orientation existant
export const CAREER_RECOMMENDATIONS = API_BASE_URL + '/career/generateCareerRecommendations';

// Simulation existant
export const SIMULATION_EFFORT = API_BASE_URL + '/simulation/generateEffortResults';
