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


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

class MyPane extends Pane {
    Pane pane = new Pane();
    Background getroffen = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
    Background verfehlt = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
    Background versenkt = new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY));

    MyPane(int row, int column, GridPane field) {
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


public class Controller_Playfield {
    public GridPane ownPlayField;
    public GridPane enemyField;
    public GridPane columnNaming;
    public GridPane columnNaming1;
    public GridPane rowNaming;
    public GridPane rowNaming1;

    /**
     * Klasse: Spielfeld
     * Methode: initialize
     * jeder Zelle der GridPane wird eine Pane (Der Klasse MyPane) untergeordnet, damit man
     * mit row und column auf jede einzelne Zelle zugreifen kann.
     */
    public void initialize() {
        for (int i = 0; i < ownPlayField.getRowCount(); i++) {
            for (int j = 0; j < ownPlayField.getColumnCount(); j++) {
                columnNaming.add(new Label(" " + i), i, 0);
                columnNaming1.add(new Label(" " + i), i, 0);
                rowNaming.add(new Label("" + i), 0, i);
                rowNaming1.add(new Label("" + i), 0, i);
                new MyPane(i, j, ownPlayField);
                new MyPane(i, j, enemyField);
            }
        }

        // sf.tipp("A7")
    }

}
