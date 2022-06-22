package com.example.scrum;

import com.example.scrum.Spieler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public Button exitBut;
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
            Stage window = new Stage();

            //Window
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Input-Error");


            //Label
            Label label = new Label();
            label.setText("Missing or Invalid Input-Parameters!");
            label.setStyle("-fx-font-size: 30");
            label.setTextFill(Color.WHITE);

            //Button
            Button btn_close = new Button("OK");
            btn_close.setMinSize(80, 30);
            btn_close.setOnAction(e -> window.close());
            btn_close.setStyle("-fx-background-color: WHITE");

            //Vbox / Layout
            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, btn_close);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSropdEHx6PYFHHNyPavR0-UNv4UDc8wX4yhg&usqp=CAU'); -fx-background-repeat: no-repeat; -fx-background-size: 500 500; -fx-background-position: center center;");

            Scene scene = new Scene(layout, 500, 400);
            window.setScene(scene);
            window.showAndWait();

        }else {

            Stage stage1 = (Stage) confirm.getScene().getWindow();
            stage1.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Playfield.fxml"));
            Parent root1 = null;
            try {
                root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("Playfield.fxml")).openStream());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                System.out.println("wia hom a problem!");
                e.printStackTrace();
            }
        }
    }

    public void exitButPressed(ActionEvent actionEvent) {
        Stage s = (Stage) exitBut.getScene().getWindow();
        s.close();
    }
}