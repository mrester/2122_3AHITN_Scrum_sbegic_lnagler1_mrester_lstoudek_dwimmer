package com.example.scrum;


import java.io.IOException;

public class Spielfeld {

    /**
     * feld[x][y] =
     * 0 = Wasser
     * 1 = U-Boot
     * 2 = Zerstörer
     * 3 = Kreuzer
     * 4 = Schlachtschiff
     */



    static int[][] feld = new int[15][15];
    InformationOutput log = new InformationOutput();
    static Schiff[] schiffe = new Schiff[10];

    int countr = 0;
    int id = 1;

    Spielfeld() {
        for (int i = feld.length; i > 0; i--) {
            for (int j = feld.length; j > 0; j--) {
                feld[i - 1][j - 1] = 0;
                System.out.println(feld[i - 1][j - 1]);
            }
        }
    }


    public void setShip(Schiff ship) {
        for (int j = 0; j < ship.posarr.length; j++) {
            if (feld[ship.posarr[j].getCol()][ship.posarr[j].getRow()] == 0) {
                feld[ship.posarr[j].getCol()][ship.posarr[j].getRow()] = ship.id;
            }
        }
        schiffe[countr] = ship;
        countr++;


    }

    public int askPosition(Position position) {
        return feld[position.getCol()][position.getRow()];
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
            for (int i = 0; i < schiffe.length; i++) {
                if (schiffe[i] != null) {
                    if (schiffe[i].hit(position)) {
                        //Todo mark ship as shot on

                        System.out.print("hit");
                        System.out.println(feld[position.getCol()][position.getRow()]);
                        rv = true;
                    }
                }
            }
        } else {
            try {
                log.shipHitOrMiss(position, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    if(feld[col - i][row] != 0){
                        rv = false;
                    }
                }
                break;
            case "Right":
                for (int i = 0; i < positions.length; i++) {
                    if(feld[col + i][row] != 0){
                        rv = false;
                    }
                }
                break;
            case "Up":
                for (int i = 0; i < positions.length; i++) {
                    if(feld[col][row - i] != 0){
                        rv = false;
                    }
                }
                break;
            case "Down":
                for (int i = 0; i < positions.length; i++) {
                    if(feld[col][row+i] != 0){
                        rv = false;
                    }
                }
        }
                return rv;

    }
}



