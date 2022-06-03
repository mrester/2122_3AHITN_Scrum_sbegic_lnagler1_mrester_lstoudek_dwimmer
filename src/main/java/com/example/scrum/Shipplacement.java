package com.example.scrum;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Shipplacement {
    @FXML
    private GridPane gridpane;

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
    InformationOutput log = new InformationOutput();

    EventHandler<? super MouseEvent> handleEventTipp = new HandleEventTipp<MouseEvent>();

    int countUBoot = 4;
    int countZerstörer = 3;
    int countKreuzer = 2;
    int countSchlachtschiff = 1;

    @FXML
    protected void onHelloButtonClick() {
        //System.out.println(this);
    }

    public void initialize() {
        shipType.getItems().addAll("U-Boot", "Zerstörer", "Kreuzer", "Schlachtschiff");
        shipRotation.getItems().addAll("Right", "Left", "Up", "Down");
        for (int i = 0; i < gridpane.getColumnCount(); i++) {
            for (int j = 0; j < gridpane.getRowCount(); j++) {

                Pane pane = new Pane();

                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, handleEventTipp);
                gridpane.add(pane, i, j);
                gridpane.setGridLinesVisible(true);
            }
        }/*
        Position[] positions = new Position[2];
        positions[0] = new Position(1, 2);
        positions[1] = new Position(1, 3);

        Schiff schiff = new Schiff();
        schiff.setShip(positions);

        spielfeld.setShip(schiff);
*/
        // System.out.println(gridpane.getChildren());
        //  ObservableList<Node> observableList = gridpane.getChildren();
        //for (int i = 0; i < observableList.size(); i++) {
        //  Node node = observableList.get(i);
        // System.out.println(node);
        // }
    }

    class HandleEventTipp<Mouseevent> implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Node node = (Node) mouseEvent.getSource();
            System.out.println(node);
            int col = GridPane.getColumnIndex(node);
            int row = GridPane.getRowIndex(node);
            Position position = new Position(col, row);
            if (spielfeld.tipp(position)){
                node.setStyle("-fx-background-color: darkred");
                try {
                    log.shipPlaced(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                node.setStyle("-fx-background-color: darkgrey");
            }
            node.setDisable(true);
        }
    }
}