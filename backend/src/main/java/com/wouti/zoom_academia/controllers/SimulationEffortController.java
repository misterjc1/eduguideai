package com.wouti.zoom_academia.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wouti.zoom_academia.services.SimulationEffortAgent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.entities.SelectedParams;

@RestController
@RequestMapping("/simulation")
public class SimulationEffortController {

    private final SimulationEffortAgent simulationEffortAgent;

    public SimulationEffortController(SimulationEffortAgent simulationEffortAgent) {
        this.simulationEffortAgent = simulationEffortAgent;
    }

    @PostMapping("/generateEffortResults")
    public ResponseEntity<String> generateSimulationEffort(@RequestBody SimulationEffortRequest request) {
        // Appel du service de génération de l'effort de simulation
        String simulationResult = simulationEffortAgent.generateSimulationEffort(request.getSelectedParams(), request.getTemplatePrompt());

        // Assurer que le résultat est bien en JSON
        if (isValidJson(simulationResult)) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // Le type de contenu est JSON
                    .body(simulationResult);
        } else {
            // Si le format JSON n'est pas valide, retourne une erreur ou un message adapté
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Invalid JSON response received.\"}");
        }
    }

    // Méthode pour vérifier si la réponse est un JSON valide
    private boolean isValidJson(String json) {
        try {
            new ObjectMapper().readTree(json);  // Vérification que la chaîne est bien un JSON valide
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Classe interne ou externe pour modéliser le body de la requête
    public static class SimulationEffortRequest {
        private SelectedParams selectedParams;
        private String templatePrompt;

        // Getters et setters
        public SelectedParams getSelectedParams() {
            return selectedParams;
        }

        public void setSelectedParams(SelectedParams selectedParams) {
            this.selectedParams = selectedParams;
        }

        public String getTemplatePrompt() {
            return templatePrompt;
        }

        public void setTemplatePrompt(String templatePrompt) {
            this.templatePrompt = templatePrompt;
        }
    }
}
