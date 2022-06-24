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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static com.sun.tools.javac.resources.CompilerProperties.Warnings.Warning;

/** Authoren: sbegic; wimmer; rester
 * Controller_Menue: Die Controller Klasse für die Steuerung und durchsetzung der Menue.fxml
 * @eingabe1 Textfeld für den Namen des ersten Spieler.
 * @eingabe2 Textfeld für den Namen des zweiten Spieler.
 * @LaChoiceBox1 ChoiceBox für die Auswahl des Landes des ersten Spielers.
 * @LaChoiceBox2 ChoiceBox für die Auswahl des Landes des zweiten Spielers.
 * @confirm Button der die Klasse pressed() aufruft.
 * @exitBut zum schließen des Fensters.
 * @s1 Spieler 1 mit Spieler Klasse.
 * @s2 Spieler 2 mit Spieler Klasse.
 * @logs für den Informations Output. (Hit, Missed, Destroyed)
 * initialize() Füllt die ChoiceBoxen mit der Länder Auswahlen.
 * pressed() Zwischenspeichert in eigene String Variablend die Spieler Namen
   und Länder. Pattern wird erstellt um die Eingabe zu überprüfen.
 * Die if() überprüft auf Gleichheit der Namen, Leere Namen,
   Gleichheit der Länder, null(leere) Länder, regex von den Namen.
   Falls eines Davon true liefert wird ein fehler Fenster geöffnet.
   Dies wird solange gemacht bis die Eingaben alle erfolgreich waren.
   Bei Erfolg werden Die SPieler mit Namen und Länder Gesetzt.
 * exitButPressed() Schließt das Fenster und Beendet somit das Programm.
 */
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
    Spieler s1; // = new Spieler();
    Spieler s2;// = new Spieler();

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

        String name2 = eingabe2.getText();
        String land2 = String.valueOf(LaChoiceBox2.getValue());
        Pattern pattern = Pattern.compile("[^A-z]");
        Matcher m1 = pattern.matcher(name1);
        Matcher m2 = pattern.matcher(name2);
        boolean pat1 = m1.find();
        boolean pat2 = m2.find();


        if ( name1.equals("") || name2.equals("") || name1.equals(name2) || land1.equals("null") ||
                land2.equals("null") || land1.equals(land2) || pat1 || pat2) {
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

        } else {

            Stage stage1 = (Stage) confirm.getScene().getWindow();
            stage1.close();

            MiddleMan middleMan = MiddleMan.getInstance();
            middleMan.showPlayField();

            s1 = new Spieler(name1, land1);
            logs.ProssesPlayerInfo(s1.name, land1);
            s2 = new Spieler(name2, land2);
            logs.ProssesPlayerInfo(s2.name, land2);
        }
    }

    public void exitButPressed(ActionEvent actionEvent) {
        Stage s = (Stage) exitBut.getScene().getWindow();
        s.close();
    }
}