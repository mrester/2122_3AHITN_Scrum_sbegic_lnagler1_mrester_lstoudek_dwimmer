package com.example.scrum;

import java.util.Arrays;
import java.util.Objects;

public class Schiff2 {

    public Position[] posarr;
    private int avtreffer = 2;
    MiddleMan middleMan = MiddleMan.getInstance();

    /**
     * Ship Id rules
     * 2 digits
     * <p>
     * 1. digit:
     * 1 - U-Boot
     * 2 - Zerstörer
     * 3 - Kreuzer
     * 4 - Schlachtschiff
     * <p>
     * 2. digit:
     * 4 - 1. placed
     * 3 - 2. placed
     * 2 - 3. placed
     * 1 - 4. placed
     */
    public int id = 1;
    static int counter = 0;

    Schiff2(int schifftype, int placed) {
        this.id = (schifftype * 10) + placed;
        switch (schifftype) {
            case 2:
                avtreffer++;
                break;
            case 3:
                avtreffer = +2;
            case 4:
                avtreffer = +3;
        }
    }

    public int getAvTreffer() {
        return avtreffer;
    }

    public void decTreffer() {
        this.avtreffer--;
    }

    public int hit(Position position) {
        int rv = 0;
        System.out.println(position);
        System.out.println("I bin in da methode hit");
        if (Objects.equals(middleMan.getPlayer(), "Player1")) {
            for (int i = 0; i < posarr.length; i++) {
                System.out.println("I bin da Player 1 ");
                if (posarr[i].getCol() == position.getCol() && posarr[i].getRow() == position.getRow()) {
                    System.out.println("I bin troffen worden");
                    rv = 1;
                    decTreffer();
                    if (avtreffer == 0) {
                        rv = 2;
                    }
                }

            }
        }
        return rv;
    }

    public void setShip2(Position[] positions) {
        if (Objects.equals(middleMan.getPlayer(), "Player2")) {
            posarr = positions;
            System.out.println(Arrays.toString(positions));
        }

    }

}
