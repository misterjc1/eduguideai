package com.wouti.zoom_academia.config;

import com.wouti.zoom_academia.entities.*;
import com.wouti.zoom_academia.repositories.*;
import com.wouti.zoom_academia.transverse.StatutLiaison;
import com.wouti.zoom_academia.transverse.TypeService;
import com.wouti.zoom_academia.transverse.TypeUtilisateur;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfilRepository profilRepo;
    private final UtilisateurRepository utilisateurRepo;
    private final NiveauRepository niveauRepo;
    private final InscritRepository inscritRepo;
    private final LiaisonRepository liaisonRepo;
    private final TemplatePromptRepository templatePromptRepo;
    private final ServiceRepository serviceRepo;
    private final TypeCinematiqueRepository typeCinematiqueRepo;
    private final CinematiqueRepository cinematiqueRepo;
    private final ParametreRepository parametreRepo;
    private final NoteRepository noteRepo;

    public DataLoader(ProfilRepository profilRepo,
                      UtilisateurRepository utilisateurRepo,
                      NiveauRepository niveauRepo,
                      InscritRepository inscritRepo,
                      LiaisonRepository liaisonRepo,
                      TemplatePromptRepository templatePromptRepo,
                      ServiceRepository serviceRepo,
                      TypeCinematiqueRepository typeCinematiqueRepo,
                      CinematiqueRepository cinematiqueRepo,
                      ParametreRepository parametreRepo,
                      NoteRepository noteRepo) {
        this.profilRepo = profilRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.niveauRepo = niveauRepo;
        this.inscritRepo = inscritRepo;
        this.liaisonRepo = liaisonRepo;
        this.templatePromptRepo = templatePromptRepo;
        this.serviceRepo = serviceRepo;
        this.typeCinematiqueRepo = typeCinematiqueRepo;
        this.cinematiqueRepo = cinematiqueRepo;
        this.parametreRepo = parametreRepo;
        this.noteRepo = noteRepo;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (profilRepo.count() > 0) {
            return; // Données déjà présentes
        }

        // ---- PROFILS ----
        Profil admin = profil("IIAPRF0000001", "Administrateur",
            "Acces complet a la plateforme",
            "administration#utilisateurs#profils#sessions#inscrits#niveaux#services#cinematiques#prompts#donnees");

        Profil enseignant = profil("IIAPRF0000002", "Enseignant",
            "Acces a la gestion des inscrits, niveaux et aux services IA",
            "inscrits#niveaux#notes#services");

        Profil etudiant = profil("IIAPRF0000003", "Etudiant",
            "Acces aux services IA et consultation de son profil",
            "services#mon-compte");

        Profil parent = profil("IIAPRF0000004", "Parent / Tuteur",
            "Acces au suivi de l'inscrit lie et aux services IA",
            "services#mon-compte");

        admin      = profilRepo.save(admin);
        enseignant = profilRepo.save(enseignant);
        etudiant   = profilRepo.save(etudiant);
        parent     = profilRepo.save(parent);

        // ---- UTILISATEURS ----
        utilisateurRepo.save(utilisateur("IIAUSR0000001", "admin",          "admin123",  "admin@veneem.bf",                 "ILBOUDO",   "Jean-Claude", TypeUtilisateur.AGENT,   admin));
        utilisateurRepo.save(utilisateur("IIAUSR0000002", "prof.ouedraogo", "prof123",   "ibrahima.ouedraogo@ujkz.bf",      "OUEDRAOGO", "Ibrahima",    TypeUtilisateur.AGENT,   enseignant));
        Utilisateur alice  = utilisateurRepo.save(utilisateur("IIAUSR0000003", "alice.kabore",    "etud123",   "alice.kabore@etud.ujkz.bf",       "KABORE",    "Alice",       TypeUtilisateur.INSCRIT, etudiant));
        Utilisateur traore = utilisateurRepo.save(utilisateur("IIAUSR0000004", "traore.parent",   "parent123", "jean.traore@gmail.com",            "TRAORE",    "Jean",        TypeUtilisateur.TUTEUR,  parent));
        utilisateurRepo.save(utilisateur("IIAUSR0000005", "moussa.sawadogo", "etud123",   "moussa.sawadogo@etud.ujkz.bf",    "SAWADOGO",  "Moussa",      TypeUtilisateur.INSCRIT, etudiant));

        // ---- NIVEAUX ----
        Niveau l1info  = niveauRepo.save(niveau("IIANIVOOOOO1", "Licence 1 Informatique",  "Informatique"));
        Niveau l2info  = niveauRepo.save(niveau("IIANIVOOOOO2", "Licence 2 Informatique",  "Informatique"));
        Niveau l3info  = niveauRepo.save(niveau("IIANIVOOOOO3", "Licence 3 Informatique",  "Informatique"));
        Niveau m1info  = niveauRepo.save(niveau("IIANIVOOOOO4", "Master 1 Informatique",   "Informatique"));
        Niveau m2info  = niveauRepo.save(niveau("IIANIVOOOOO5", "Master 2 Informatique",   "Informatique"));
        Niveau l1ges   = niveauRepo.save(niveau("IIANIVOOOOO6", "Licence 1 Gestion",       "Gestion"));
        Niveau l2ges   = niveauRepo.save(niveau("IIANIVOOOOO7", "Licence 2 Gestion",       "Gestion"));
        Niveau l3ges   = niveauRepo.save(niveau("IIANIVOOOOO8", "Licence 3 Gestion",       "Gestion"));
        Niveau l1droit = niveauRepo.save(niveau("IIANIVOOOOO9", "Licence 1 Droit",         "Droit"));
        Niveau l2droit = niveauRepo.save(niveau("IIANIVOOO010", "Licence 2 Droit",         "Droit"));

        // ---- INSCRITS ----
        // matricule = username du compte Utilisateur lié → permet findNotes?matricule=alice.kabore
        Inscrit ins01 = inscritRepo.save(inscrit("IIAINSOOOO01", "alice.kabore",    "KABORE",    "Alice",    "alice.kabore@ujkz.bf",     "70001001", l3info));
        Inscrit ins02 = inscritRepo.save(inscrit("IIAINSOOOO02", "moussa.sawadogo", "SAWADOGO",  "Moussa",   "moussa.sawadogo@ujkz.bf",  "70001002", l3info));
        Inscrit ins03 = inscritRepo.save(inscrit("IIAINSOOOO03", "2024INF003", "OUEDRAOGO", "Fatima",   "fatima.ouedraogo@ujkz.bf", "70001003", l3info));
        Inscrit ins04 = inscritRepo.save(inscrit("IIAINSOOOO04", "2024INF004", "TRAORE",    "Ismael",   "ismael.traore@ujkz.bf",    "70001004", l3info));
        Inscrit ins05 = inscritRepo.save(inscrit("IIAINSOOOO05", "2024INF005", "COMPAORE",  "Djeneba",  "djeneba.compaore@ujkz.bf", "70001005", l3info));
        Inscrit ins06 = inscritRepo.save(inscrit("IIAINSOOOO06", "2024INF006", "ZONGO",     "Romuald",  "romuald.zongo@ujkz.bf",    "70001006", l2info));
        Inscrit ins07 = inscritRepo.save(inscrit("IIAINSOOOO07", "2024INF007", "KINDA",     "Mariam",   "mariam.kinda@ujkz.bf",     "70001007", l2info));
        Inscrit ins08 = inscritRepo.save(inscrit("IIAINSOOOO08", "2024INF008", "SOME",      "Aristide", "aristide.some@ujkz.bf",    "70001008", l2info));
        Inscrit ins09 = inscritRepo.save(inscrit("IIAINSOOOO09", "2024INF009", "ILBOUDO",   "Raoul",    "raoul.ilboudo@ujkz.bf",    "70001009", m1info));
        Inscrit ins10 = inscritRepo.save(inscrit("IIAINSOOOO10", "2024INF010", "NIKIEMA",   "Sandra",   "sandra.nikiema@ujkz.bf",   "70001010", m1info));
        Inscrit ins11 = inscritRepo.save(inscrit("IIAINSOOOO11", "2024GES001", "BAZIE",     "Kofi",     "kofi.bazie@ujkz.bf",       "70001011", l1ges));
        Inscrit ins12 = inscritRepo.save(inscrit("IIAINSOOOO12", "2024GES002", "YAMEOGO",   "Laure",    "laure.yameogo@ujkz.bf",    "70001012", l1ges));
        Inscrit ins13 = inscritRepo.save(inscrit("IIAINSOOOO13", "2024GES003", "TINTO",     "Fabrice",  "fabrice.tinto@ujkz.bf",    "70001013", l2ges));
        Inscrit ins14 = inscritRepo.save(inscrit("IIAINSOOOO14", "2024GES004", "SORE",      "Aminata",  "aminata.sore@ujkz.bf",     "70001014", l2ges));
        Inscrit ins15 = inscritRepo.save(inscrit("IIAINSOOOO15", "2024GES005", "BARRO",     "Adama",    "adama.barro@ujkz.bf",      "70001015", l3ges));
        Inscrit ins16 = inscritRepo.save(inscrit("IIAINSOOOO16", "2024DRT001", "SIMPORE",   "Olivia",   "olivia.simpore@ujkz.bf",   "70001016", l1droit));
        Inscrit ins17 = inscritRepo.save(inscrit("IIAINSOOOO17", "2024DRT002", "COMBARY",   "Serge",    "serge.combary@ujkz.bf",    "70001017", l1droit));
        Inscrit ins18 = inscritRepo.save(inscrit("IIAINSOOOO18", "2024DRT003", "OUOBA",     "Clarisse", "clarisse.ouoba@ujkz.bf",   "70001018", l2droit));
        Inscrit ins19 = inscritRepo.save(inscrit("IIAINSOOOO19", "2024DRT004", "DIALLO",    "Hamidou",  "hamidou.diallo@ujkz.bf",   "70001019", l2droit));
        Inscrit ins20 = inscritRepo.save(inscrit("IIAINSOOOO20", "2024INF011", "RABO",      "Hermann",  "hermann.rabo@ujkz.bf",     "70001020", l3info));

        // ---- LIAISON parent <-> inscrit ----
        Liaison liaison = new Liaison();
        liaison.setCodeLiaison("IIALS0000001");
        liaison.setStatutLiaison(StatutLiaison.VALIDE);
        liaison.setUtilisateur(traore);
        liaison.setInscrit(ins01);
        liaison.setCreatorCode("system");
        liaisonRepo.save(liaison);

        // ---- TEMPLATE PROMPTS ----
        TemplatePrompt tp1 = templatePrompt("IIATPROM000001",
            "Analysez les performances academiques et predisez les risques de decrochage scolaire. Matricule: {matricule}, Semestre: {semestre}, Moyenne actuelle: {moyenne_actuelle}, Absences: {absences}, Engagement: {engagement}. Fournissez une analyse detaillee et des recommandations personnalisees.",
            "matricule,semestre,moyenne_actuelle,absences,engagement");
        TemplatePrompt tp2 = templatePrompt("IIATPROM000002",
            "Tu es un assistant virtuel academique specialise pour les etudiants. Question de l'etudiant: {question}. Contexte: {contexte}. Niveau de l'etudiant: {niveau_etudiant}. Reponds de facon pedagogique et bienveillante.",
            "question,contexte,niveau_etudiant");
        TemplatePrompt tp3 = templatePrompt("IIATPROM000003",
            "Analysez les indicateurs suivants pour detecter toute anomalie statistique. Matricule: {matricule}, Module: {module}, Note actuelle: {note_actuelle}, Historique des notes: {historique_notes}, Ecart-type: {ecart_type}. Identifiez les patterns suspects et expliquez votre raisonnement.",
            "matricule,module,note_actuelle,historique_notes,ecart_type");
        TemplatePrompt tp4 = templatePrompt("IIATPROM000004",
            "Orientez cet etudiant vers des filieres et metiers adaptes. Filieres choisies: {filieres_choisies}, Interets: {interets}, Competences: {competences}, Marche de l'emploi actuel: {marche_emploi}. Proposez 3 orientations professionnelles detaillees avec les etapes a suivre.",
            "filieres_choisies,interets,competences,marche_emploi");
        TemplatePrompt tp5 = templatePrompt("IIATPROM000005",
            "Simulez l'impact des efforts d'apprentissage sur les resultats scolaires. Heures de travail: {heures_travail}, Methodes d'etude: {methodes_etude}, Modules difficiles: {modules_difficiles}, Objectif de note: {objectif_note}. Donnez des predictions chiffrees et un plan d'action concret.",
            "heures_travail,methodes_etude,modules_difficiles,objectif_note");
        tp1 = templatePromptRepo.save(tp1); tp2 = templatePromptRepo.save(tp2);
        tp3 = templatePromptRepo.save(tp3); tp4 = templatePromptRepo.save(tp4);
        tp5 = templatePromptRepo.save(tp5);

        // ---- SERVICES IA ----
        Service svc1 = serviceRepo.save(service("IIASERV000001", TypeService.ANALYSE_PREDICTIVE,
            "Analysez les performances academiques et predisez les risques de decrochage", tp1));
        Service svc2 = serviceRepo.save(service("IIASERV000002", TypeService.ASSISTANT_VIRTUEL,
            "Posez toutes vos questions academiques a notre assistant IA", tp2));
        Service svc3 = serviceRepo.save(service("IIASERV000003", TypeService.DETECTION_FRAUDE,
            "Detectez les anomalies statistiques dans les resultats academiques", tp3));
        Service svc4 = serviceRepo.save(service("IIASERV000004", TypeService.ORIENTATION_PROFFESSIONNELLE,
            "Decouvrez les metiers et parcours professionnels adaptes a votre profil", tp4));
        Service svc5 = serviceRepo.save(service("IIASERV000005", TypeService.SIMULATION_EFFORTS,
            "Simulez l'impact de vos efforts d'apprentissage sur vos resultats", tp5));

        // ---- TYPE CINEMATIQUES ----
        TypeCinematique tc1 = typeCinematiqueRepo.save(typeCinematique("IIATCNMT00001", "Saisie texte libre",    com.wouti.zoom_academia.transverse.Niveau.NIVEAU1, "TEXT",     "false"));
        TypeCinematique tc2 = typeCinematiqueRepo.save(typeCinematique("IIATCNMT00002", "Choix unique",           com.wouti.zoom_academia.transverse.Niveau.NIVEAU2, "RADIO",    "false"));
        TypeCinematique tc3 = typeCinematiqueRepo.save(typeCinematique("IIATCNMT00003", "Choix multiples",        com.wouti.zoom_academia.transverse.Niveau.NIVEAU2, "CHECKBOX", "true"));
        TypeCinematique tc4 = typeCinematiqueRepo.save(typeCinematique("IIATCNMT00004", "Affichage resultat IA",  com.wouti.zoom_academia.transverse.Niveau.NIVEAU3, "RESULT",   "false"));

        // ---- CINEMATIQUES ----
        cinematiqueRepo.save(cinematique("IIACNMT000001", tc1, svc1));
        cinematiqueRepo.save(cinematique("IIACNMT000002", tc2, svc1));
        cinematiqueRepo.save(cinematique("IIACNMT000003", tc4, svc1));
        cinematiqueRepo.save(cinematique("IIACNMT000004", tc1, svc2));
        cinematiqueRepo.save(cinematique("IIACNMT000005", tc4, svc2));
        cinematiqueRepo.save(cinematique("IIACNMT000006", tc2, svc3));
        cinematiqueRepo.save(cinematique("IIACNMT000007", tc4, svc3));
        cinematiqueRepo.save(cinematique("IIACNMT000008", tc3, svc4));
        cinematiqueRepo.save(cinematique("IIACNMT000009", tc4, svc4));
        cinematiqueRepo.save(cinematique("IIACNMT000010", tc1, svc5));
        cinematiqueRepo.save(cinematique("IIACNMT000011", tc4, svc5));

        // ---- PARAMETRES (options pour orientation et simulation) ----
        parametreRepo.save(parametre("IIAPARAM00001", "Developpement logiciel",    "DEV",   svc4));
        parametreRepo.save(parametre("IIAPARAM00002", "Intelligence artificielle", "IA",    svc4));
        parametreRepo.save(parametre("IIAPARAM00003", "Cybersecurite",             "CYBER", svc4));
        parametreRepo.save(parametre("IIAPARAM00004", "Gestion de projet",         "MANA",  svc4));
        parametreRepo.save(parametre("IIAPARAM00005", "Finance et banque",         "FIN",   svc4));
        parametreRepo.save(parametre("IIAPARAM00006", "Marketing digital",         "MKTG",  svc4));
        parametreRepo.save(parametre("IIAPARAM00007", "Droit des affaires",        "DROIT", svc4));
        parametreRepo.save(parametre("IIAPARAM00008", "Entrepreneuriat",           "ENTRE", svc4));
        parametreRepo.save(parametre("IIAPARAM00009", "Moins de 2h par jour",      "1H",    svc5));
        parametreRepo.save(parametre("IIAPARAM00010", "2h a 4h par jour",          "3H",    svc5));
        parametreRepo.save(parametre("IIAPARAM00011", "Plus de 4h par jour",       "5H",    svc5));

        // ---- NOTES ---- (matricule = username pour alice et moussa)
        noteRepo.save(note("IIANOT0000001", "alice.kabore", "S1", "BD_AVA",   "Bases de donnees avancees",     14.5, ins01));
        noteRepo.save(note("IIANOT0000002", "alice.kabore", "S1", "ALGO",     "Algorithmique avancee",         12.0, ins01));
        noteRepo.save(note("IIANOT0000003", "alice.kabore", "S1", "RES",      "Reseaux informatiques",         15.5, ins01));
        noteRepo.save(note("IIANOT0000004", "alice.kabore", "S1", "GL",       "Genie logiciel",                13.0, ins01));
        noteRepo.save(note("IIANOT0000005", "alice.kabore", "S2", "IA_FOND",  "Fondements de l'IA",            16.0, ins01));
        noteRepo.save(note("IIANOT0000006", "alice.kabore", "S2", "WEB_DEV",  "Developpement Web",             15.0, ins01));
        noteRepo.save(note("IIANOT0000007", "alice.kabore", "S2", "SYS_DIST", "Systemes distribues",           11.5, ins01));
        noteRepo.save(note("IIANOT0000008", "moussa.sawadogo", "S1", "BD_AVA",   "Bases de donnees avancees",   9.5, ins02));
        noteRepo.save(note("IIANOT0000009", "moussa.sawadogo", "S1", "ALGO",     "Algorithmique avancee",      10.0, ins02));
        noteRepo.save(note("IIANOT0000010", "moussa.sawadogo", "S1", "RES",      "Reseaux informatiques",      11.0, ins02));
        noteRepo.save(note("IIANOT0000011", "moussa.sawadogo", "S1", "GL",       "Genie logiciel",              8.5, ins02));
        noteRepo.save(note("IIANOT0000012", "moussa.sawadogo", "S2", "IA_FOND",  "Fondements de l'IA",         12.0, ins02));
        noteRepo.save(note("IIANOT0000013", "moussa.sawadogo", "S2", "WEB_DEV",  "Developpement Web",          13.0, ins02));
        noteRepo.save(note("IIANOT0000014", "2024INF003", "S1", "BD_AVA",   "Bases de donnees avancees",     17.0, ins03));
        noteRepo.save(note("IIANOT0000015", "2024INF003", "S1", "ALGO",     "Algorithmique avancee",         16.5, ins03));
        noteRepo.save(note("IIANOT0000016", "2024INF003", "S1", "RES",      "Reseaux informatiques",         14.0, ins03));
        noteRepo.save(note("IIANOT0000017", "2024INF003", "S2", "IA_FOND",  "Fondements de l'IA",            18.0, ins03));
        noteRepo.save(note("IIANOT0000018", "2024INF003", "S2", "WEB_DEV",  "Developpement Web",             17.5, ins03));
        noteRepo.save(note("IIANOT0000019", "2024INF004", "S1", "BD_AVA",   "Bases de donnees avancees",      5.0, ins04));
        noteRepo.save(note("IIANOT0000020", "2024INF004", "S1", "ALGO",     "Algorithmique avancee",          4.5, ins04));
        noteRepo.save(note("IIANOT0000021", "2024INF004", "S1", "RES",      "Reseaux informatiques",          7.0, ins04));
        noteRepo.save(note("IIANOT0000022", "2024INF004", "S2", "IA_FOND",  "Fondements de l'IA",             6.0, ins04));
        noteRepo.save(note("IIANOT0000023", "2024INF009", "S1", "ML",       "Machine Learning",              16.0, ins09));
        noteRepo.save(note("IIANOT0000024", "2024INF009", "S1", "DL",       "Deep Learning",                 15.0, ins09));
        noteRepo.save(note("IIANOT0000025", "2024INF009", "S1", "NLP",      "Traitement du langage naturel", 14.0, ins09));
        noteRepo.save(note("IIANOT0000026", "2024INF009", "S2", "CV",       "Vision par ordinateur",         13.5, ins09));
        noteRepo.save(note("IIANOT0000027", "2024GES005", "S1", "COMPTA",   "Comptabilite generale",         12.0, ins15));
        noteRepo.save(note("IIANOT0000028", "2024GES005", "S1", "MKTG",     "Marketing fondamental",         14.0, ins15));
        noteRepo.save(note("IIANOT0000029", "2024GES005", "S1", "MANA",     "Management des organisations",  13.0, ins15));
        noteRepo.save(note("IIANOT0000030", "2024GES005", "S2", "FIN",      "Finance d'entreprise",          11.0, ins15));
    }

    // ---- helpers ----

    private Profil profil(String code, String intitule, String description, String role) {
        Profil p = new Profil();
        p.setCodeProfil(code);
        p.setIntitule(intitule);
        p.setDescription(description);
        p.setRole(role);
        p.setStatut(true);
        p.setCreatorCode("system");
        return p;
    }

    private Utilisateur utilisateur(String code, String username, String password, String email,
                                    String nom, String prenom, TypeUtilisateur type, Profil profil) {
        Utilisateur u = new Utilisateur();
        u.setCodeUtilisateur(code);
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setType(type);
        u.setActif(true);
        u.setProfil(profil);
        u.setCreatorCode("system");
        return u;
    }

    private Niveau niveau(String code, String libelle, String filiere) {
        Niveau n = new Niveau();
        n.setCodeNiveau(code);
        n.setLibelle(libelle);
        n.setFiliere(filiere);
        n.setCreatorCode("system");
        return n;
    }

    private Inscrit inscrit(String code, String matricule, String nom, String prenom,
                             String email, String telephone, Niveau niveau) {
        Inscrit i = new Inscrit();
        i.setCodeInscrit(code);
        i.setMatricule(matricule);
        i.setNom(nom);
        i.setPrenom(prenom);
        i.setEmail(email);
        i.setTelephone(telephone);
        i.setNiveau(niveau);
        i.setCreatorCode("system");
        return i;
    }

    private TemplatePrompt templatePrompt(String code, String description, String variables) {
        TemplatePrompt t = new TemplatePrompt();
        t.setCodeTemplatePrompt(code);
        t.setDescription(description);
        t.setVariables(variables);
        t.setCreatorCode("system");
        return t;
    }

    private Service service(String code, TypeService type, String message, TemplatePrompt tp) {
        Service s = new Service();
        s.setCodeService(code);
        s.setTypeService(type);
        s.setMessage(message);
        s.setTemplatePrompt(tp);
        s.setCreatorCode("system");
        return s;
    }

    private TypeCinematique typeCinematique(String code, String libelle,
                                             com.wouti.zoom_academia.transverse.Niveau niveau,
                                             String typeBouton, String choixMultiple) {
        TypeCinematique tc = new TypeCinematique();
        tc.setCodeTypeCinematique(code);
        tc.setNiveau(niveau);
        tc.setTypeBouton(typeBouton);
        tc.setChoixMultiple(choixMultiple);
        tc.setCreatorCode("system");
        return tc;
    }

    private Cinematique cinematique(String code, TypeCinematique tc, Service svc) {
        Cinematique c = new Cinematique();
        c.setCodeCinematique(code);
        c.setTypeCinematique(tc);
        c.setTypeService(svc);
        c.setCreatorCode("system");
        return c;
    }

    private Parametre parametre(String code, String libelle, String valeur, Service svc) {
        Parametre p = new Parametre();
        p.setCodeParametre(code);
        p.setParametres(libelle + "=" + valeur);
        p.setService(svc);
        p.setCreatorCode("system");
        return p;
    }

    private Note note(String code, String matricule, String semestre, String module,
                      String libelle, Double valeur, Inscrit inscrit) {
        Note n = new Note();
        n.setCodeNote(code);
        n.setMatricule(matricule);
        n.setSemestre(semestre);
        n.setModule(module);
        n.setLibelle(libelle);
        n.setValeur(valeur);
        n.setInscrit(inscrit);
        n.setCreatorCode("system");
        return n;
    }
}
