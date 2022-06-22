package com.example.scrum;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Controller_WhiteScreen {
    public Pane paneAll;
    public static int counter = 0;
    public Button ready;
    public String field;
    FXMLLoader fxmlLoader;
    Parent root1;
    Scene scene;
    MiddleMan middleMan = MiddleMan.getInstance();

    public Controller_WhiteScreen() throws IOException {
    }

    public void readyPressed() throws IOException {
        if (counter == 0){
            middleMan.showPlayField2();
            counter++;
        }else if (counter == 1){
            middleMan.showPlayField();
            counter = 0;
        }



        /*if (stage1 == null && field.equals("PlayField2")){
            fxmlLoader = new FXMLLoader(getClass().getResource(field + ".fxml"));
            root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource(field + ".fxml")).openStream());
            System.out.println("Hallo");
            stage1 = new Stage();
            scene = new Scene(root1);
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.show();
        }else if (stage1.isShowing() && field.equals("PlayField2")){
            System.out.println("is showing");
            stage1.toFront();
        }

        if (stage == null && field.equals("PlayField")){
            fxmlLoader = new FXMLLoader(getClass().getResource(field + ".fxml"));
            root1 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource(field + ".fxml")).openStream());
            System.out.println("Hallo");
            scene = new Scene(root1);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }else if (stage.isShowing() && field.equals("PlayField")){
            System.out.println("is showing");
            stage.toFront();
        }
         */
    }
}
