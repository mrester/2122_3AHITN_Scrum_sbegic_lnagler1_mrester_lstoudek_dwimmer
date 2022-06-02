package com.example.scrum;

import com.example.scrum.Spieler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class Controller_Menue {
    public static int count = 1;
    @FXML
    public ChoiceBox LaChoiceBox;
    public Label SpAusgabe;
    public Label LaAusgabe;
    public TextField eingabe;
    public Button confirm;
    Spieler s1 = new Spieler();
    Spieler s2 = new Spieler();

    InformationOutput logs = new InformationOutput();

    public void initialize() throws IOException {
        logs.fileManager();
        LaChoiceBox.setItems(FXCollections.observableArrayList(
                "Japan", "Deutschland", "USA", "Russland", "China", "Großbritanien"));
    }

    public void pressed(ActionEvent actionEvent) throws IOException {
        if (count == 1) {
            String name = eingabe.getText();
            String land = String.valueOf(LaChoiceBox.getValue());
            s1 = new Spieler(name, land);

            SpAusgabe.setText(s1.getName());
            LaAusgabe.setText(s1.land.getName());
            count++;
            logs.ProssesPlayerInfo(s1.name, land);
            eingabe.clear();

        } else if (count == 2) {
            String name = eingabe.getText();
            String land = String.valueOf(LaChoiceBox.getValue());
            s2 = new Spieler(name, land);

            SpAusgabe.setText(s2.getName());
            LaAusgabe.setText(s2.land.getName());
            count++;
            logs.ProssesPlayerInfo(s2.name, land);
            eingabe.clear();

        }
    }
}