package com.aichatbot.services;

import com.aichatbot.config.Config;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GeminiService {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    private static final String API_KEY = "AIzaSyAHHuOkjP9NRWAYUKoKcnuZCoT-oSlK42s";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public GeminiService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String getResponse(String userMessage) {
        try {
            // Create message node
            ObjectNode messageNode = objectMapper.createObjectNode()
                .put("role", "user")
                .put("content", userMessage);

            // Create request body
            ObjectNode requestBody = objectMapper.createObjectNode();
            ObjectNode contentsNode = objectMapper.createObjectNode();
            contentsNode.putArray("parts").addObject().put("text", userMessage);
            requestBody.putArray("contents").add(contentsNode);
            // Removed incorrect fields 'prompt', 'max_tokens', and 'temperature'

            // Build HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();

            // Send request and parse response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Status: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            if (response.statusCode() != 200) {
                throw new IOException("API request failed with status code: " + response.statusCode());
            }
            String generatedText = "";
            JsonNode responseJson = objectMapper.readTree(response.body());
            if (responseJson.has("candidates")) {
                JsonNode candidates = responseJson.get("candidates");
                if (candidates.isArray() && candidates.size() > 0) {
                    JsonNode firstCandidate = candidates.get(0);
                    if (firstCandidate.has("content")) {
                        JsonNode content = firstCandidate.get("content");
                        if (content.has("parts")) {
                            JsonNode parts = content.get("parts");
                            if (parts.isArray() && parts.size() > 0) {
                                generatedText = parts.get(0).get("text").asText();
                            }
                        }
                    }
                }
            }
            return generatedText;
        } catch (Exception e) {
            System.err.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return "Sorry, I couldn't process your request.";
        }
    }
}