package com.aichatbot.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import com.aichatbot.services.GeminiService;

public class ChatController {

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField userInput;

    @FXML
    public void handleSend() {
        String message = userInput.getText();
        chatListView.getItems().add("You: " + message);
        userInput.clear();
        try {
            chatListView.getItems().add("Bot: " + getResponseFromGeminiAI(message));
        } catch (Exception e) {
            chatListView.getItems().add("Bot: Sorry, I couldn't process your request.");
            System.err.println("Error: " + e.getMessage());
        }
    }

    private String getResponseFromGeminiAI(String message) {
        GeminiService geminiService = new GeminiService();
        return geminiService.getResponse(message);
    }
}
