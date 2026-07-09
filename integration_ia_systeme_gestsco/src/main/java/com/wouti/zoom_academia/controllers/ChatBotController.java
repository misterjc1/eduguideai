package com.wouti.zoom_academia.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wouti.zoom_academia.services.chatbot.ChatBotService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping("/predefinedQuestions")
    public ResponseEntity<List<String>> getPredefinedQuestions() {
        List<String> questions = chatBotService.getPredefinedQuestions();
        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(questions);
    }

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> askQuestion(@RequestBody ChatRequest chatRequest) {
        String response = chatBotService.askQuestion(chatRequest.getQuestion());

        // Renvoie la réponse comme un objet JSON avec la clé "response"
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("response", response);

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(responseMap);
    }

    public static class ChatRequest {
        private String question;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
}

