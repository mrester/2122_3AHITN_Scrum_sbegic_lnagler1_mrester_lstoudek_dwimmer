package com.example.scrum;


import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Spielfeld {

    /**
     * feld[x][y] =
     * 0 = Wasser
     * 1 = U-Boot
     * 2 = Zerstörer
     * 3 = Kreuzer
     * 4 = Schlachtschiff
     */

    MiddleMan middleMan = MiddleMan.getInstance();
    int[][] feld = new int[15][15];
    InformationOutput log = new InformationOutput();
    static Schiff[] schiffe = new Schiff[10];
    static Schiff2[] schiffe2 = new Schiff2[10];

    int countr = 0;
    int id = 1;

    Spielfeld() {
        for (int i = feld.length; i > 0; i--) {
            for (int j = feld.length; j > 0; j--) {
                feld[i - 1][j - 1] = 0;
            }
        }
    }


    public void setShip(Schiff ship) {
        if (Objects.equals(middleMan.getPlayer(), "Player1")) {
            for (int j = 0; j < ship.posarr.length; j++) {
                if (feld[ship.posarr[j].getCol()][ship.posarr[j].getRow()] == 0) {
                    feld[ship.posarr[j].getCol()][ship.posarr[j].getRow()] = ship.id;
                    middleMan.setFeld(feld);
                    System.out.println("Spieler 1 hat gesetzt");
                }
            }
        }
        schiffe[countr] = ship;
        countr++;
    }

    public void setShip2(Schiff2 ship2){
        if (Objects.equals(middleMan.getPlayer(), "Player2")) {
            for (int j = 0; j < ship2.posarr.length; j++) {
                if (feld[ship2.posarr[j].getCol()][ship2.posarr[j].getRow()] == 0) {
                    feld[ship2.posarr[j].getCol()][ship2.posarr[j].getRow()] = ship2.id;
                    middleMan.setFeld2(feld);
                    System.out.println("Spieler 2 hat gesetzt");
                }
            }
        }
        schiffe2[countr] = ship2;
        countr++;
    }

    public int askPosition(Position position) {
        if (Objects.equals(middleMan.getPlayer(), "Player1")) {
            return middleMan.getFeld2()[position.getCol()][position.getRow()];
        } else if (Objects.equals(middleMan.getPlayer(), "Player2")) {
            return middleMan.getFeld()[position.getCol()][position.getRow()];
        }else {
            return 0;
        }
    }

    /**
     * //muss noch eingeführt werden
     * derweil treffer = true else false
     *
     * @return 0 = nicht getroffen
     * 1 = treffer
     * 2 = versenkt
     */
    public boolean tipp(Position position) {
        boolean rv = false;
        if (askPosition(position) > 0) {
            if (Objects.equals(middleMan.getPlayer(), "Player1")){
                for (int i = 0; i < schiffe2.length; i++) {
                    if (schiffe2[i] != null) {
                        int gwship = schiffe2[i].hit(position);
                        System.out.println(gwship);
                        if (gwship == 1) {
                            System.out.print("hit");
                            System.out.println(feld[position.getCol()][position.getRow()] + " Spielfeld - Zeile 67");
                            rv = true;
                        } else if (gwship == 2) {
                            System.out.print("down");
                            System.out.println(feld[position.getCol()][position.getRow()]  + " Spielfeld - Zeile 67");
                            rv = true;
                        }
                    }
                }
            }else if (Objects.equals(middleMan.getPlayer(), "Player2")){
                for (int i = 0; i < schiffe.length; i++) {
                    if (schiffe[i] != null) {
                        int gwship = schiffe[i].hit(position);
                        System.out.println(gwship);
                        if (gwship == 1) {
                            System.out.print("hit");
                            System.out.println(feld[position.getCol()][position.getRow()] + " Spielfeld - Zeile 67");
                            rv = true;
                        } else if (gwship == 2) {
                            System.out.print("down");
                            System.out.println(feld[position.getCol()][position.getRow()]  + " Spielfeld - Zeile 67");
                            rv = true;
                        }
                    }
                }
            }
        } else {
            System.out.println("missed");
        }
        return rv;
    }

    public static void main(String[] args) {
        Spielfeld spielfeld = new Spielfeld();
    }

    public boolean checkposition(Position[] positions, String value) {
        int col = positions[0].getCol();
        int row = positions[0].getRow();
        boolean rv = true;
        switch (value) {
            case "Left":
                for (int i = 0; i < positions.length; i++) {
                    if (feld[col - i][row] != 0) {
                        rv = false;
                    }
                }
                break;
            case "Right":
                for (int i = 0; i < positions.length; i++) {
                    if (feld[col + i][row] != 0) {
                        rv = false;
                    }
                }
                break;
            case "Up":
                for (int i = 0; i < positions.length; i++) {
                    if (feld[col][row - i] != 0) {
                        rv = false;
                    }
                }
                break;
            case "Down":
                for (int i = 0; i < positions.length; i++) {
                    if (feld[col][row + i] != 0) {
                        rv = false;
                    }
                }
        }
        return rv;
    }
}