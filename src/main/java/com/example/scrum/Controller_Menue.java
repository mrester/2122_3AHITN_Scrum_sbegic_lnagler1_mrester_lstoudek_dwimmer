package com.example.scrum;

import com.example.scrum.Spieler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_Menue {
    @FXML
    //Spieler 1
    public TextField eingabe1;
    public ChoiceBox LaChoiceBox1;

    //Spieler 2
    public TextField eingabe2;
    public ChoiceBox LaChoiceBox2;

    //Confirm Button
    public Button confirm;
    Spieler s1 = new Spieler();
    Spieler s2 = new Spieler();

    InformationOutput logs = new InformationOutput();

    public void initialize() throws IOException {
        logs.fileManager();
        LaChoiceBox1.setItems(FXCollections.observableArrayList(
                "Japan", "Deutschland", "USA", "Russland", "China", "Großbritanien"));
        LaChoiceBox2.setItems(FXCollections.observableArrayList(
                "Japan", "Deutschland", "USA", "Russland", "China", "Großbritanien"));
    }

    public void pressed(ActionEvent actionEvent) throws IOException {
        String name1 = eingabe1.getText();
        String land1 = String.valueOf(LaChoiceBox1.getValue());
        s1 = new Spieler(name1, land1);

        logs.ProssesPlayerInfo(s1.name, land1);

        String name2 = eingabe2.getText();
        String land2 = String.valueOf(LaChoiceBox2.getValue());
        s2 = new Spieler(name2, land2);

        logs.ProssesPlayerInfo(s2.name, land2);

        if (name1.equals("") || name2.equals("") || name1.equals(name2) || land1.equals("null") || land2.equals("null") || land1.equals(land2)) {
            //logs.InputError();
        }else {

            Stage stage1 = (Stage) confirm.getScene().getWindow();
            stage1.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Playfield.fxml"));
            Parent root1 = null;
            try {
                root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("Playfield.fxml")).openStream());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                System.out.println("wia hom a problem!");
                e.printStackTrace();
            }
        }
    }

}