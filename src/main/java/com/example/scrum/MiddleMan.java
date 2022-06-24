package com.example.scrum;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


public class MiddleMan {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WhiteScreen.fxml"));
    Parent root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("Whitescreen.fxml")).openStream());
    Scene scene = new Scene(root1);
    Stage stage;
    Stage stage1;
    Stage stage2;
    public static final MiddleMan INSTANCE;

    private int[][] feld = new int[15][15];
    private int[][] feld2 = new int[15][15];

    String player;

    static {
        try {
            INSTANCE = new MiddleMan();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MiddleMan() throws IOException {
    }
    /**
     * @author lnagler1
     * Wird in der Klasse Controller_Whitescreen aufgerufen, wenn der Ready Button gedrückt wird und der Counter in der Klasse auf 1 ist,
     * heißt das gerade der Spieler 2 am Zug war..
     * Wenn die Spieler getauscht haben und der nächste Spieler sein Spielfeld sehen will um Zu beginnen,
     * drückt er auf den Knopf Ready und der Whitescreen verschwindet und sein Spielfeld wird angezeigt.
     */
    public void showPlayField() throws IOException {
        if (stage == null) {
            stage = new Stage();
            System.out.println("Nö");
            fxmlLoader = new FXMLLoader(getClass().getResource("PlayField.fxml"));
            root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("PlayField.fxml")).openStream());
            System.out.println("Hallo");
            scene = new Scene(root1);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }else if (stage.isShowing()){
            System.out.println("is showing PlayField");
            stage.toFront();
        }
        System.out.println("I kenn des problem");
    }

    /**
     * @author lnagler1
     * Wird in der Klasse Controller_Whitescreen aufgerufen, wenn der Ready Button gedrückt wird und der Counter in der Klasse auf 0 ist,
     * heit das gerade der Spieler 1 seinen Zug gemacht hat..
     * Wenn die Spieler getauscht haben und der nächste Spieler sein Spielfeld sehen will um Zu beginnen,
     * drückt er auf den Knopf Ready und der Whitescreen verschwindet und sein Spielfeld wird angezeigt.
     */
    public void showPlayField2() throws IOException {
        if (stage1 == null) {
            stage1 = new Stage();
            fxmlLoader = new FXMLLoader(getClass().getResource("Playfield2.fxml"));
            root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("PlayField2.fxml")).openStream());
            System.out.println("Hallo");
            scene = new Scene(root1);
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.show();
        }else if (stage1.isShowing()){
            System.out.println("is showing Playfield2");
            stage1.toFront();
        }
    }

    /**
     * @author lnagler1,
     * @throws IOException
     * Wird in Controller_PlayField und Controller_PlayField2 aufgerufen, wenn der Finished Button gedrückt wird.
     * Wenn der Spieler zeinen Zug beendet hat, drückt er auf den Button und es erscheint ein WhiteScreen, damit er nicht
     * das Spielfeld des Gegners sieht.
     */
    public void showWhiteScreen() throws IOException {
        if (stage2 == null){
            System.out.println("Hallo");
            stage2 = new Stage();
            fxmlLoader = new FXMLLoader(getClass().getResource("Whitescreen.fxml"));
            root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("Whitescreen.fxml")).openStream());
            scene = new Scene(root1);
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.show();
        }else if (stage2.isShowing()){
            System.out.println("is showing WhiteScreen");
            stage2.toFront();
        }
    }
    public static MiddleMan getInstance(){
        return INSTANCE;
    }

    public int[][] getFeld() {
        return feld;
    }

    public void setFeld(int[][] feld) {
        this.feld = feld;
    }

    public int[][] getFeld2() {
        return feld2;
    }

    public void setFeld2(int[][] feld2) {
        this.feld2 = feld2;
    }

    public void setPlayer(String player){
        this.player = player;
    }
    public String getPlayer(){
        return player;
    }

}
