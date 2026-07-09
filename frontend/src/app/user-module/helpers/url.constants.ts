export const HTTP_HEADER = 'http://';
export const IP_ADDRESS = 'localhost:';
export const PORT = '8181';

// Audit
export const FIND_ALL_AUDIT = HTTP_HEADER + IP_ADDRESS + PORT + '/audit/findAll?isDeleted=false';

// Inscrit
export const FIND_ALL_INSCRITS = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/findAll?isDeleted=false';
export const FIND_NOTES_BY_MATRICULE = HTTP_HEADER + IP_ADDRESS + PORT + '/inscrit/findNotes?matricule=';

// Page
export const ADD_PAGE = HTTP_HEADER + IP_ADDRESS + PORT + '/page/save';
export const DELETE_PAGE = HTTP_HEADER + IP_ADDRESS + PORT + '/page/delete';
export const FIND_ALL_PAGE = HTTP_HEADER + IP_ADDRESS + PORT + '/page/findAll?isDeleted=false';
export const FIND_PAGE_BY_KEY = HTTP_HEADER + IP_ADDRESS + PORT + '/page/findAll?key=';
export const UPDATE_PAGE = HTTP_HEADER + IP_ADDRESS + PORT + '/page/update';

// Personne
export const FIND_PERSONNE_BY_STRUCTURE = HTTP_HEADER + IP_ADDRESS + PORT + '/personne/findByStructure?codeStructure=';
export const DELETE_PERSONNE = HTTP_HEADER + IP_ADDRESS + PORT + '/personne/delete';
export const FIND_ALL_PERSONNE = HTTP_HEADER + IP_ADDRESS + PORT + '/personne/findAll?isDeleted=false';
export const FIND_PERSONNE_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/personne/findAll?codePersonne=';
export const SAVE_PERSONNE = HTTP_HEADER + IP_ADDRESS + PORT + '/personne/save';
export const UPDATE_PERSONNE = HTTP_HEADER + IP_ADDRESS + PORT + '/personne/update';

// Profil
export const FIND_PROFIL_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/profil/findAll?codeProfil=';
export const ADD_PROFIL = HTTP_HEADER + IP_ADDRESS + PORT + '/profil/save';
export const DELETE_PROFIL = HTTP_HEADER + IP_ADDRESS + PORT + '/profil/delete';
export const FIND_ALL_PROFIL = HTTP_HEADER + IP_ADDRESS + PORT + '/profil/findAll?isDeleted=false';
export const UPDATE_PROFIL = HTTP_HEADER + IP_ADDRESS + PORT + '/profil/update';

// Session
export const FIND_SESSION_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/session/findAll?codeSession=';
export const UPDATE_SESSION = HTTP_HEADER + IP_ADDRESS + PORT + '/session/update';
export const FIND_SESSION_BY_USER = HTTP_HEADER + IP_ADDRESS + PORT + '/session/findByUser?codeUtilisateur=';
export const FIND_ALL_SESSION = HTTP_HEADER + IP_ADDRESS + PORT + '/session/findAll?isDeleted=false';
export const SAVE_SESSION = HTTP_HEADER + IP_ADDRESS + PORT + '/session/save';

// Terminal
export const FIND_TERMINAL_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/terminal/findAll?codeTerminal=';

// Utilisateur
export const UPDATEPASSWORD = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/updatePassword';
export const FIND_UTILISATEUR_BY_USERNAME = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findAll?username=';
export const FIND_UTILISATEUR_BY_CODE = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findAll?codeUtilisateur=';
export const FIND_UTILISATEUR_BY_STRUCTURE = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findByStructure?codeStructure=';
export const ADD_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/save';
export const DELETE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/delete';
export const FIND_ALL_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findAll?isDeleted=false';
export const UPDATE_UTILISATEUR = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/update';
export const SIGN_IN = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/signin';
export const SIGN_UP = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/signup';
export const FIND_UTILISATEUR_BY_PERSON = HTTP_HEADER + IP_ADDRESS + PORT + '/utilisateur/findByPerson?codePersonne=';