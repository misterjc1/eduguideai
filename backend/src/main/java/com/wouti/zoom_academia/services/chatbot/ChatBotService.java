package com.wouti.zoom_academia.services.chatbot;

import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.ChatClient;
import java.util.List;
import java.util.Arrays;

@Service
public class ChatBotService {

    private final ChatClient chatClient;

    public ChatBotService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // Méthode pour récupérer des questions prédéfinies
    public List<String> getPredefinedQuestions() {
        return Arrays.asList(
        		"Comment améliorer mes résultats scolaires ?",
                "Quels sont les métiers liés à mes études ?",
                "Comment préparer mes examens ?",
                "Quelles méthodes d'étude sont les plus efficaces ?",
                "Comment gérer mon temps d'étude ?",
                "Quels sont les outils disponibles pour la recherche académique ?",
                "Comment développer de bonnes habitudes d'étude ?",
                "Quels conseils pour réussir un entretien d'embauche ?",
                "Comment choisir ma filière d'études ?",
                "Quels sont les critères d'une bonne lettre de motivation ?",
                "Comment financer mes études au Burkina Faso ?",
                "Quelles sont les options d'apprentissage en ligne disponibles ?",
                "Comment trouver un stage ou une expérience professionnelle pertinente ?"
        );
    }

    // Méthode pour poser une question et obtenir une réponse de l'IA
    public String askQuestion(String question) {
    	String systemMessage = "Tu es un assistant académique intelligent, spécialisé dans le contexte éducatif du Burkina Faso. " +
                "Ta tâche est d'aider les étudiants en répondant à leurs questions sur le domaine scolaire, y compris les programmes d'études, les méthodes d'apprentissage et les ressources disponibles. " +
                "Sois clair et concis dans tes réponses, et n'hésite pas à fournir des conseils pratiques adaptés aux réalités locales. " +
                "Si une question est ambiguë ou nécessite des détails supplémentaires, demande des précisions pour mieux orienter ta réponse.";


        // Appelle le client d'IA pour générer une réponse à partir de la question
        return chatClient.prompt()
                         .system(systemMessage)
                         .user("Question: " + question)
                         .call()
                         .content();
    }
}
