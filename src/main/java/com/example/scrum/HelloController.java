package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    public static int count =1;
    @FXML
    public ChoiceBox LaChoiceBox;
    public Label SpAusgabe;
    public Label LaAusgabe;
    public TextField eingabe;
    public Button confirm;
    Spieler s1 = new Spieler();
    Spieler s2 = new Spieler();
    InformationOutput log;


    public void pressed(ActionEvent actionEvent) {
        if (count == 1){
            String name = eingabe.getText();
            String land = String.valueOf(LaChoiceBox.getValue());
            s1 = new Spieler(name,land);

            SpAusgabe.setText(s1.getName());
            LaAusgabe.setText(s1.land.getName());
            count++;
            log.PlayerCreated(name);
        }else if (count == 2){
            String name = eingabe.getText();
            String land = String.valueOf(LaChoiceBox.getValue());
            s2 = new Spieler(name,land);

            SpAusgabe.setText(s2.getName());
            LaAusgabe.setText(s2.land.getName());
            count++;
            log.PlayerCreated(name);

        }




    }
}