package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    public TextField PlEingabe;
    public Label PlAusgabe;
    Spieler s2 = new Spieler();


    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void pressed(ActionEvent actionEvent) {
        String name = PlEingabe.getText();
        s2.setName(name);
        PlAusgabe.setText(s2.getName());
    }
}