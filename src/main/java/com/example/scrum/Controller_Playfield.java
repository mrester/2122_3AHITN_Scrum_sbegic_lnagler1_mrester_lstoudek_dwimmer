/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Informatik und Netzwerktechnik
 *----------------------------------------------------------------------------*/
/**
 * Output des PlayFields
 *
 * @author : lnagler1
 * @date : 5.05. 2022
 * @details SchiffeVersenken, siehe Trello
 */

package com.example.scrum;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

class MyPane extends Pane {
    Pane pane = new Pane();
    Background getroffen = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
    Background verfehlt = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
    Background versenkt = new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY));

    MyPane(int row, int column, GridPane field) {
        field.add(pane, row, column);
        Shipplacement sh = new Shipplacement();
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, sh.handleEventTipp);
    }
}


public class Controller_Playfield {
    public GridPane ownPlayField;
    public GridPane enemyField;
    public GridPane columnNaming;
    public GridPane columnNaming1;
    public GridPane rowNaming;
    public GridPane rowNaming1;
    public Label LandPL2;
    public Label LandPL1;
    public Button btExit;
    public Pane paneAll;

    @FXML
    ChoiceBox<String> shipType;
    @FXML
    TextField shipX;
    @FXML
    TextField shipY;
    @FXML
    ChoiceBox<String> shipRotation;

    @FXML
    Button butt;
    Spielfeld spielfeld = new Spielfeld();
    Shipplacement sh = new Shipplacement();
    InformationOutput log = new InformationOutput();

    Spieler spieler = new Spieler();


    int countUBoot = 4;
    int countZerstoerer = 3;
    int countKreuzer = 2;
    int countSchlachtschiff = 1;
    MiddleMan middleMan = MiddleMan.getInstance();
    static int a = 0;

    /**
     * Klasse: Spielfeld
     * Methode: initialize
     * jeder Zelle der GridPane wird eine Pane (Der Klasse MyPane) untergeordnet, damit man
     * mit row und column auf jede einzelne Zelle zugreifen kann.
     */
    public void initialize() {
        GridPane gridPane = new GridPane();
        int player = 1;
        gridPane = ownPlayField;

        LandPL1.setText(spieler.getLand1());
        LandPL2.setText(spieler.getLand2());

        shipType.getItems().addAll("U-Boot", "Zerstörer", "Kreuzer", "Schlachtschiff");
        shipRotation.getItems().addAll("Right", "Left", "Up", "Down");
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                columnNaming.add(new Label(" " + i), i, 0);
                columnNaming1.add(new Label(" " + i), i, 0);
                rowNaming.add(new Label("" + i), 0, i);
                rowNaming1.add(new Label("" + i), 0, i);
                new MyPane(i, j, enemyField);
            }
        }

        // sf.tipp("A7")
    }

    /**
     * @author Leonhard Stoudek
     */
    public void save() {
        int col = Integer.parseInt(shipX.getText());
        int row = Integer.parseInt(shipY.getText());
        int type = 1;
        int placedt = 4;
        Position[] positions = new Position[2];
        boolean shipsOver = true;

        switch (shipType.getValue()) {
            case "U-Boot":
                if (countUBoot != 0) {
                    placedt = countUBoot;
                    log.setShipType(0);
                    positions[0] = new Position(col, row);
                    if (spielfeld.checkposition(positions, shipRotation.getValue())) {
                        countUBoot--;
                    }
                } else {
                    shipsOver = false;
                }
                break;
            case "Zerstörer":
                if (countZerstoerer != 0) {
                    positions = new Position[3];
                    placedt = countZerstoerer;
                    type = 2;
                    log.setShipType(1);
                    positions[0] = new Position(col, row);
                    if (spielfeld.checkposition(positions, shipRotation.getValue())) {
                        countZerstoerer--;
                    }
                } else {
                    shipsOver = false;
                }
                break;
            case "Kreuzer":
                if (countKreuzer != 0) {
                    positions = new Position[4];
                    placedt = countKreuzer;
                    type = 3;
                    log.setShipType(2);
                }
                    positions[0] = new Position(col, row);
                    if (spielfeld.checkposition(positions, shipRotation.getValue())) {
                        countKreuzer--;
                    }
                 else {
                    shipsOver = false;
                }
                break;
            case "Schlachtschiff":
                if (countSchlachtschiff != 0) {
                    positions = new Position[5];
                    placedt = countSchlachtschiff;
                    type = 4;
                    positions[0] = new Position(col, row);
                    if (spielfeld.checkposition(positions, shipRotation.getValue())) {
                        countSchlachtschiff--;
                    }

                }else {
                log.setShipType(3);
                    shipsOver = false;
                }
        }
        positions[0] = new Position(col, row);

        if (spielfeld.checkposition(positions, shipRotation.getValue()) && shipsOver) {
            switch (shipRotation.getValue()) {
                case "Left":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col - i, row);
                        Pane pane = new Pane();
                        pane.setStyle("-fx-background-color: red");
                        ownPlayField.add(pane, col - i, row);
                    }
                    break;
                case "Right":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col + i, row);
                        Pane pane = new Pane();
                        pane.setStyle("-fx-background-color: red");
                        ownPlayField.add(pane, col + i, row);
                    }
                    break;
                case "Up":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col, row - i);
                        Pane pane = new Pane();
                        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, sh.handleEventTipp);
                        pane.setStyle("-fx-background-color: red");
                        ownPlayField.add(pane, col, row - i);
                    }
                    break;
                case "Down":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col, row + i);
                        Pane pane = new Pane();
                        pane.setStyle("-fx-background-color: red");
                        ownPlayField.add(pane, col, row + i);
                    }
            }
            ownPlayField.setGridLinesVisible(true);
            Schiff schiff = new Schiff(type, placedt);
            schiff.setShip(positions);
            spielfeld.setShip(schiff);

            try {
                log.shipInfo(shipRotation.getValue(),shipType.getValue(),shipX.getText(), shipY.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(shipRotation.getValue() + shipType.getValue() + shipX.getText() + shipY.getText());
        } else {
            System.out.println("nicht möglich");

        }
    }

    public void ExitButtPressed() {
        Stage s = (Stage) btExit.getScene().getWindow();
        s.close();
    }

    public void finishedPressed() throws IOException {
        middleMan.showWhiteScreen();
    }
}
