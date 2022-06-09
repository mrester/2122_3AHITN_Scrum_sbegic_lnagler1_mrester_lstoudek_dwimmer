package com.example.scrum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SplashScreen extends Thread {

    @FXML
    public StackPane stackpane;
    @FXML
    public Pane stagepane;

    public void initialize() {
        System.out.println(this.stackpane.toString());
        this.start();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);


            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menue.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (stackpane != null) {
                        stackpane.getScene().getWindow().hide();
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                }

            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ;
    }
}
