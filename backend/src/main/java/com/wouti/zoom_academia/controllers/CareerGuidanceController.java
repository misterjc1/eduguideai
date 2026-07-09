package com.wouti.zoom_academia.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.services.CareerOrientationAgent;


@RestController
@RequestMapping("/career")
public class CareerGuidanceController {

   
	private final CareerOrientationAgent careerGuidanceAgent;

    public CareerGuidanceController(CareerOrientationAgent careerGuidanceAgent) {
        this.careerGuidanceAgent = careerGuidanceAgent;
    }
    
    @PostMapping("/generateCareerRecommendations")
    public ResponseEntity<String> generateCareerRecommendations(@RequestBody CareerRecommendationRequest request) {
        // Appel du service de génération de recommandations
        String recommendations = careerGuidanceAgent.generateCareerRecommendations(request.getPreferences(), request.getTemplatePrompt());
        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON) // Le type de contenu est JSON
                             .body(recommendations);
    }
    
    
 // Nouveau endpoint qui prend un string "templatePrompt" comme corps de requête
    @PostMapping("/generateCareerRecommendationsByTemplate")
    public ResponseEntity<String> generateCareerRecommendationsByTemplate(@RequestBody String templatePrompt) {
        // Appelle le service de génération de recommandations avec uniquement le templatePrompt
        String recommendations = careerGuidanceAgent.generateCareerRecommendations(new String[]{}, templatePrompt);
        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(recommendations);
    }
    
    // Classe interne ou externe pour modéliser le body de la requête
    public static class CareerRecommendationRequest {
        private String[] preferences;
        private String templatePrompt;

        // Getters et setters
        public String[] getPreferences() {
            return preferences;
        }

        public void setPreferences(String[] preferences) {
            this.preferences = preferences;
        }

        public String getTemplatePrompt() {
            return templatePrompt;
        }

        public void setTemplatePrompt(String templatePrompt) {
            this.templatePrompt = templatePrompt;
        }
    }

}
