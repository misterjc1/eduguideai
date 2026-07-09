package com.wouti.zoom_academia.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiAnalyseController {

    private final ChatClient chatClient;

    public AiAnalyseController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    // ── Analyse prédictive : narrative IA ──
    @PostMapping("/analyse")
    public ResponseEntity<Map<String, String>> analyseNotes(@RequestBody AnalyseRequest req) {
        String system = "Tu es un expert en pédagogie et en analyse académique au Burkina Faso. " +
            "Tu analyses les notes d'un étudiant et tu fournis une interprétation claire, encourageante et actionnable. " +
            "Réponds toujours en français, de façon concise (max 5 phrases), bienveillante et motivante.";

        String user = String.format(
            "Analyse académique pour l'étudiant '%s' :\n" +
            "- Niveau : %s\n" +
            "- Objectif académique : %s\n" +
            "- Moyenne actuelle : %.2f/20\n" +
            "- Moyenne prédite : %.2f/20\n" +
            "- Notes par module : %s\n\n" +
            "Donne une interprétation personnalisée et des conseils concrets pour atteindre l'objectif.",
            req.getUsername(), req.getNiveau(), req.getObjectif(),
            req.getMoyenneActuelle(), req.getMoyennePredite(), req.getNotesResume()
        );

        String content = chatClient.prompt().system(system).user(user).call().content();
        return ResponseEntity.ok(Map.of("analyse", content));
    }

    // ── Orientation : recommandations de carrière ──
    @PostMapping("/orientation")
    public ResponseEntity<Map<String, String>> orientation(@RequestBody OrientationRequest req) {
        String system = "Tu es un conseiller en orientation professionnelle spécialisé dans le contexte africain et burkinabè. " +
            "Tu proposes des recommandations de carrière réalistes, adaptées au marché local et aux intérêts de l'étudiant. " +
            "Réponds en français, de façon structurée avec des sections claires (Métiers recommandés, Compétences à développer, Étapes concrètes).";

        String user = String.format(
            "Profil étudiant :\n" +
            "- Filière actuelle : %s\n" +
            "- Domaines d'intérêt : %s\n" +
            "- Compétences : %s\n" +
            "- Objectif professionnel : %s\n" +
            "- Valeurs : %s\n\n" +
            "Propose 3 à 4 métiers adaptés avec pour chacun : description, débouchés au Burkina Faso, et étapes pour y arriver.",
            req.getFiliere(), req.getInterets(), req.getCompetences(),
            req.getObjectif(), req.getValeurs()
        );

        String content = chatClient.prompt().system(system).user(user).call().content();
        return ResponseEntity.ok(Map.of("recommandations", content));
    }

    // ── Simulation : plan d'efforts ──
    @PostMapping("/simulation")
    public ResponseEntity<Map<String, String>> simulation(@RequestBody SimulationRequest req) {
        String system = "Tu es un coach académique spécialisé en planification d'études. " +
            "Tu crées des plans d'études réalistes et motivants. " +
            "Réponds en français avec un plan structuré (hebdomadaire, par matière, avec créneaux horaires suggérés).";

        String user = String.format(
            "Plan d'effort académique pour '%s' :\n" +
            "- Niveau : %s\n" +
            "- Objectifs : %s\n" +
            "- Notes actuelles : %s\n" +
            "- Disponibilité hebdomadaire : %s heures\n\n" +
            "Crée un plan d'étude détaillé sur 4 semaines, avec priorités par matière et conseils pratiques.",
            req.getUsername(), req.getNiveau(), req.getObjectifs(),
            req.getNotesResume(), req.getHeuresDisponibles()
        );

        String content = chatClient.prompt().system(system).user(user).call().content();
        return ResponseEntity.ok(Map.of("plan", content));
    }

    // ── Détection fraudes : analyse narrative ──
    @PostMapping("/fraud-analysis")
    public ResponseEntity<Map<String, String>> fraudAnalysis(@RequestBody FraudRequest req) {
        String system = "Tu es un expert en intégrité académique. " +
            "Tu analyses des résultats de détection de fraude sur des notes académiques. " +
            "Réponds en français, de façon factuelle et professionnelle, sans accuser directement, " +
            "en soulignant les anomalies détectées et en recommandant des mesures de contrôle.";

        String user = String.format(
            "Analyse de l'intégrité académique :\n" +
            "- Nombre total de notes analysées : %d\n" +
            "- Anomalies détectées : %d\n" +
            "- Règles appliquées : %s\n" +
            "- Résumé des fraudes : %s\n\n" +
            "Fournis une interprétation professionnelle et des recommandations pour améliorer l'intégrité du système de notation.",
            req.getTotalNotes(), req.getAnomalies(), req.getRegles(), req.getResume()
        );

        String content = chatClient.prompt().system(system).user(user).call().content();
        return ResponseEntity.ok(Map.of("analyse", content));
    }

    // ── DTOs ──
    public static class AnalyseRequest {
        private String username; private String niveau; private String objectif;
        private double moyenneActuelle; private double moyennePredite; private String notesResume;
        public String getUsername() { return username; }
        public void setUsername(String v) { this.username = v; }
        public String getNiveau() { return niveau; }
        public void setNiveau(String v) { this.niveau = v; }
        public String getObjectif() { return objectif; }
        public void setObjectif(String v) { this.objectif = v; }
        public double getMoyenneActuelle() { return moyenneActuelle; }
        public void setMoyenneActuelle(double v) { this.moyenneActuelle = v; }
        public double getMoyennePredite() { return moyennePredite; }
        public void setMoyennePredite(double v) { this.moyennePredite = v; }
        public String getNotesResume() { return notesResume; }
        public void setNotesResume(String v) { this.notesResume = v; }
    }

    public static class OrientationRequest {
        private String filiere; private String interets; private String competences;
        private String objectif; private String valeurs;
        public String getFiliere() { return filiere; }
        public void setFiliere(String v) { this.filiere = v; }
        public String getInterets() { return interets; }
        public void setInterets(String v) { this.interets = v; }
        public String getCompetences() { return competences; }
        public void setCompetences(String v) { this.competences = v; }
        public String getObjectif() { return objectif; }
        public void setObjectif(String v) { this.objectif = v; }
        public String getValeurs() { return valeurs; }
        public void setValeurs(String v) { this.valeurs = v; }
    }

    public static class SimulationRequest {
        private String username; private String niveau; private String objectifs;
        private String notesResume; private String heuresDisponibles;
        public String getUsername() { return username; }
        public void setUsername(String v) { this.username = v; }
        public String getNiveau() { return niveau; }
        public void setNiveau(String v) { this.niveau = v; }
        public String getObjectifs() { return objectifs; }
        public void setObjectifs(String v) { this.objectifs = v; }
        public String getNotesResume() { return notesResume; }
        public void setNotesResume(String v) { this.notesResume = v; }
        public String getHeuresDisponibles() { return heuresDisponibles; }
        public void setHeuresDisponibles(String v) { this.heuresDisponibles = v; }
    }

    public static class FraudRequest {
        private int totalNotes; private int anomalies; private String regles; private String resume;
        public int getTotalNotes() { return totalNotes; }
        public void setTotalNotes(int v) { this.totalNotes = v; }
        public int getAnomalies() { return anomalies; }
        public void setAnomalies(int v) { this.anomalies = v; }
        public String getRegles() { return regles; }
        public void setRegles(String v) { this.regles = v; }
        public String getResume() { return resume; }
        public void setResume(String v) { this.resume = v; }
    }
}
