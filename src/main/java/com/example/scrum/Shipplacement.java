package com.example.scrum;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
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
    EventHandler<? super MouseEvent> handleMouseEntered = new HandleMouseEntered<MouseEvent>();
    EventHandler<? super MouseEvent>  handleMouseExited = new HandleMouseExited<MouseEvent>();

    private final SoundPlayer soundPlayer = new SoundPlayer();

    int countUBoot = 4;
    int countZerstörer = 3;
    int countKreuzer = 2;
    int countSchlachtschiff = 1;
    int id = 0;
    MiddleMan middleMan = MiddleMan.getInstance();

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
                pane.addEventFilter(MouseEvent.MOUSE_ENTERED, handleMouseEntered);
                pane.addEventFilter(MouseEvent.MOUSE_EXITED, handleMouseExited);
                gridpane.add(pane, i, j);
                gridpane.setGridLinesVisible(true);
            }
        }
        /*
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
            if (spielfeld.tipp(position)) {
                soundPlayer.playFile("src/main/resources/com/example/scrum/Ship-Hit-Sound.mp3");
                node.setStyle("-fx-background-color: darkred");
                try {
                    log.shipHitOrMiss(position, id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                id = 1;
                soundPlayer.playFile("src/main/resources/com/example/scrum/Water-Hit-Sound.mp3");
                node.setStyle("-fx-background-color: darkgrey");
                try {
                    log.shipHitOrMiss(position, id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            node.setDisable(true);
        }
    }

    class HandleDrageDetected<MouseEvent> implements EventHandler<javafx.scene.input.MouseEvent>{




        @Override
        public void handle(javafx.scene.input.MouseEvent event) {
            /* drag was detected, start a drag-and-drop gesture*/
            /* allow any transfer mode */
           // Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            /* Put a string on a dragboard */
            ClipboardContent content = new ClipboardContent();
            //content.putString(source.getText());
            //db.setContent(content);

            event.consume();

        }
    }

    class HandleMouseEntered<Mouseevent> implements EventHandler<MouseEvent>{



        @Override
        public void handle(MouseEvent event) {
            Node node = (Node) event.getSource();
            /* the drag-and-drop gesture entered the target */
            System.out.println("HandleMouseEntered");
            /* show to the user that it is an actual gesture target */
            node.setStyle("-fx-background-color: green");

            event.consume();
        }
    }
    class HandleMouseExited<Mouseevent> implements EventHandler<MouseEvent >{




        @Override
        public void handle(MouseEvent event) {
            Node node = (Node) event.getSource();
            System.out.println("HandleMouseExited");
            /* the drag-and-drop gesture entered the target */
            /* mouse moved away, remove the graphical cues */
            node.setStyle("-fx-background-color: #25E712");

            event.consume();
        }
    }
}