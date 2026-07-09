package com.wouti.zoom_academia.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wouti.zoom_academia.entities.SelectedParams;

@AiAgent
@Service
public class SimulationEffortAgent {
	
	private final ChatClient chatClient;

    public SimulationEffortAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
	
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateSimulationEffort(SelectedParams selectedParams, String templatePrompt) {
        String responseContent = chatClient.prompt()
                     .system(templatePrompt)
                     .user("Selected Params: " + selectedParams.toString())
                     .call()
                     .content();
        
        // Log the full response for debugging
        System.out.println("API Response: " + responseContent);
        
        // Split the response into text and graph parts
        String[] responseParts = responseContent.split("Graph Data:");
        String textualExplanation = responseParts[0];  // This is the text part
        String graphData = responseParts.length > 1 ? responseParts[1] : "";  // The graph part, if present

        // Check if graphData contains valid JSON
        try {
            // Parse the graph data
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode graphJson = objectMapper.readTree(graphData);

            // Return the full structured response
            return "Text Explanation: " + textualExplanation + "\nGraph Data: " + graphJson.toString();
        } catch (Exception e) {
            // Handle invalid JSON or missing graph data
            return "Text Explanation: " + textualExplanation + "\nGraph Data: Not available or invalid";
        }
    }

}
