package com.aichatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/chat.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("AI ChatBot 🤖");
        stage.setScene(scene);
        stage.setHeight(720);
        stage.setWidth(1080);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}