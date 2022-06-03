package com.example.scrum;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menue"));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }
}