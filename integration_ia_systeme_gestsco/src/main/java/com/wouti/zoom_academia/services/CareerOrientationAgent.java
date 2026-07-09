package com.wouti.zoom_academia.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@AiAgent
@Service
public class CareerOrientationAgent {
	
	private final ChatClient chatClient;

    public CareerOrientationAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
	
    private final ObjectMapper objectMapper = new ObjectMapper();



    public String generateCareerRecommendations(String[] preferences, String templatePrompt) {
        // Logique pour générer les recommandations
        String responseContent = chatClient.prompt()
                    .system(templatePrompt)  
                    .user("Selected Preferences: " + String.join(", ", preferences))
                    .call()
                    .content();

        // Parse the response to extract the JSON part (in case the response is wrapped in additional text)
        int jsonStart = responseContent.indexOf("[");
        int jsonEnd = responseContent.lastIndexOf("]") + 1;

        if (jsonStart != -1 && jsonEnd != -1) {
            String jsonResponse = responseContent.substring(jsonStart, jsonEnd);
            return jsonResponse;
        } else {
            throw new IllegalArgumentException("Invalid response format: JSON data not found.");
        }
    }


}
