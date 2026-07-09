import { environment } from '../../../environments/environment';

// URL de base de l'API, definie par environment.ts (dev) / environment.prod.ts (prod)
export const API_BASE_URL = environment.apiBaseUrl;

// Audit
export const FIND_ALL_AUDIT = API_BASE_URL + '/audit/findAll?isDeleted=false';

// Inscrit
export const FIND_ALL_INSCRITS = API_BASE_URL + '/inscrit/findAll?isDeleted=false';
export const FIND_NOTES_BY_MATRICULE = API_BASE_URL + '/inscrit/findNotes?matricule=';

// Page
export const ADD_PAGE = API_BASE_URL + '/page/save';
export const DELETE_PAGE = API_BASE_URL + '/page/delete';
export const FIND_ALL_PAGE = API_BASE_URL + '/page/findAll?isDeleted=false';
export const FIND_PAGE_BY_KEY = API_BASE_URL + '/page/findAll?key=';
export const UPDATE_PAGE = API_BASE_URL + '/page/update';

// Personne
export const FIND_PERSONNE_BY_STRUCTURE = API_BASE_URL + '/personne/findByStructure?codeStructure=';
export const DELETE_PERSONNE = API_BASE_URL + '/personne/delete';
export const FIND_ALL_PERSONNE = API_BASE_URL + '/personne/findAll?isDeleted=false';
export const FIND_PERSONNE_BY_CODE = API_BASE_URL + '/personne/findAll?codePersonne=';
export const SAVE_PERSONNE = API_BASE_URL + '/personne/save';
export const UPDATE_PERSONNE = API_BASE_URL + '/personne/update';

// Profil
export const FIND_PROFIL_BY_CODE = API_BASE_URL + '/profil/findAll?codeProfil=';
export const ADD_PROFIL = API_BASE_URL + '/profil/save';
export const DELETE_PROFIL = API_BASE_URL + '/profil/delete';
export const FIND_ALL_PROFIL = API_BASE_URL + '/profil/findAll?isDeleted=false';
export const UPDATE_PROFIL = API_BASE_URL + '/profil/update';

// Session
export const FIND_SESSION_BY_CODE = API_BASE_URL + '/session/findAll?codeSession=';
export const UPDATE_SESSION = API_BASE_URL + '/session/update';
export const FIND_SESSION_BY_USER = API_BASE_URL + '/session/findByUser?codeUtilisateur=';
export const FIND_ALL_SESSION = API_BASE_URL + '/session/findAll?isDeleted=false';
export const SAVE_SESSION = API_BASE_URL + '/session/save';

// Terminal
export const FIND_TERMINAL_BY_CODE = API_BASE_URL + '/terminal/findAll?codeTerminal=';

// Utilisateur
export const UPDATEPASSWORD = API_BASE_URL + '/utilisateur/updatePassword';
export const FIND_UTILISATEUR_BY_USERNAME = API_BASE_URL + '/utilisateur/findAll?username=';
export const FIND_UTILISATEUR_BY_CODE = API_BASE_URL + '/utilisateur/findAll?codeUtilisateur=';
export const FIND_UTILISATEUR_BY_STRUCTURE = API_BASE_URL + '/utilisateur/findByStructure?codeStructure=';
export const ADD_UTILISATEUR = API_BASE_URL + '/utilisateur/save';
export const DELETE_UTILISATEUR = API_BASE_URL + '/utilisateur/delete';
export const FIND_ALL_UTILISATEUR = API_BASE_URL + '/utilisateur/findAll?isDeleted=false';
export const UPDATE_UTILISATEUR = API_BASE_URL + '/utilisateur/update';
export const SIGN_IN = API_BASE_URL + '/utilisateur/signin';
export const SIGN_UP = API_BASE_URL + '/utilisateur/signup';
export const FIND_UTILISATEUR_BY_PERSON = API_BASE_URL + '/utilisateur/findByPerson?codePersonne=';