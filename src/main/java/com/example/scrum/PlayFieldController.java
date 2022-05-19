/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Informatik und Netzwerktechnik
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : lnagler1
 * @date : 5.05. 2022
 * @details SchiffeVersenken, siehe Trello
 */

package com.example.scrum;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

class MyPane extends Pane{
    Pane pane = new Pane();
    Background getroffen = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
    Background verfehlt = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
    Background versenkt = new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY));

    MyPane(int row, int column, GridPane field){
        field.add(pane, row, column);

        pane.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println(row + "+" + column);
                        /**
                         * @implNote Sobald es mit dem Code von Leonhard zusammengefuegt wird, kann
                         * man die Ausklammerung weg machen und es sollte funktionieren.
                         */

                        /**
                         * Je nachdem welchen tipp man von der Spielfeld methode "tipp" zurueckbekommt
                         * wird das Feld, auf welches man geklickt hat in der Farbe:
                         *     > RED angezeigt, wenn ein Schiff getroffen wurde
                         *     > DARKGRAY angezeigt, wenn man das Wasser trifft (Schiff verfehlt)
                         *     > DARKRED angezeigt, wenn ein Schiff versenkt wurde.
                         */
                        /*Position position = new Position(row, column);
                        SpielFeld sf = new SpielFeld();
                        if(sf.tipp(position) == 0){
                            pane.setBackground(verfehlt);
                        }else if(sf.tipp(position) == 1){
                            pane.setBackground(getroffen);
                        }else if(st.tipp(position) == 2){
                            pane.setBackground(versenkt);
                        }
                         */
                    }
                });
    }
}


public class PlayFieldController {
    public GridPane ownPlayField;
    public GridPane enemyField;

    // Spielfeld
    public void initialize(){
        for (int i = 0; i < ownPlayField.getRowCount(); i++) {
            for (int j = 0; j < ownPlayField.getColumnCount(); j++) {
                new MyPane(i, j ,ownPlayField);
                new MyPane(i, j, enemyField);
            }
        }

        // sf.tipp("A7")
    }

}
