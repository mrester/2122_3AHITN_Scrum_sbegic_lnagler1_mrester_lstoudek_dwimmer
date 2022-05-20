package com.example.scrum;

public class Schiff {

    public  Position[] posarr;
    private int avtreffer = 2;

    /**
     * Ship Id rules
     * 2 digits
     *
     * 1. digit:
     * 1 - U-Boot
     * 2 - Zerstörer
     * 3 - Kreuzer
     * 4 - Schlachtschiff
     *
     * 2. digit:
     * 4 - 1. placed
     * 3 - 2. placed
     * 2 - 3. placed
     * 1 - 4. placed
     */
    public int id = 1;

    Schiff(int schifftype, int placed){
        this.id = (schifftype * 10) + placed;
        switch (schifftype){
            case 2:
                avtreffer++;
                break;
            case 3:
                avtreffer=+2;
            case 4:
                avtreffer=+3;
        }
    }

    public int getAvTreffer() {
        return avtreffer;
    }

    public void decTreffer() {
        this.avtreffer--;
    }

    public boolean hit(Position position){
        boolean rv = false;
        for (int i = 0; i< posarr.length; i++){
            if (posarr[i].getCol() == position.getCol() && posarr[i].getRow() == position.getRow()){
                rv = true;
                decTreffer();
            }
        }
        return rv;
    }

    public void setShip(Position[] positions){
        posarr = positions;
    }

}
