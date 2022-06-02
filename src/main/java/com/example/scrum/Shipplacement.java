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
                    countUBoot--;
                } else {
                    shipsOver = false;
                }
                break;
            case "Zerstörer":
                if (countZerstörer != 0) {
                    positions = new Position[3];
                    placedt = countZerstörer;
                    countZerstörer--;
                    type = 2;
                } else {
                    shipsOver = false;
                }
                break;
            case "Kreuzer":
                if (countKreuzer != 0) {
                    positions = new Position[4];
                    placedt = countKreuzer;
                    type = 3;
                    countKreuzer--;
                }else {
                    shipsOver = false;
                }
                break;
            case "Schlachtschiff":
                if (countSchlachtschiff != 0) {
                    placedt = countSchlachtschiff;
                    countSchlachtschiff--;
                    positions = new Position[5];
                    type = 4;
                }else {
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
                        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, handleEventTipp);
                        pane.setStyle("-fx-background-color: red");
                        gridpane.add(pane, col - i, row);
                    }
                    break;
                case "Right":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col + i, row);
                        Pane pane = new Pane();
                        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, handleEventTipp);
                        pane.setStyle("-fx-background-color: red");
                        gridpane.add(pane, col + i, row);
                    }
                    break;
                case "Up":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col, row - i);
                        Pane pane = new Pane();
                        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, handleEventTipp);
                        pane.setStyle("-fx-background-color: red");
                        gridpane.add(pane, col, row - i);
                    }
                    break;
                case "Down":
                    for (int i = 0; i < positions.length; i++) {
                        positions[i] = new Position(col, row + i);
                        Pane pane = new Pane();
                        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, handleEventTipp);
                        pane.setStyle("-fx-background-color: red");
                        gridpane.add(pane, col, row + i);
                    }
            }
            gridpane.setGridLinesVisible(true);
            Schiff schiff = new Schiff(type, placedt);
            schiff.setShip(positions);
            spielfeld.setShip(schiff);
            System.out.println(shipRotation.getValue() + shipType.getValue() + shipX.getText() + shipY.getText());
        } else {
            System.out.println("nicht möglich");
        }

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
            } else {
                node.setStyle("-fx-background-color: darkgrey");
            }
            node.setDisable(true);
        }
    }
}