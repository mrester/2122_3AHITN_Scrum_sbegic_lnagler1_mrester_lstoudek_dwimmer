package com.example.scrum;

import java.util.Arrays;
import java.util.Objects;

public class Schiff {

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

    Schiff(int schifftype, int placed) {
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
        if (Objects.equals(middleMan.getPlayer(), "Player2")) {
            for (int i = 0; i < posarr.length; i++) {
                if (posarr[i].getCol() == position.getCol() && posarr[i].getRow() == position.getRow()) {
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

    public void setShip(Position[] positions) {
        if (Objects.equals(middleMan.getPlayer(), "Player1")) {
            posarr = positions;
        }

    }

}
