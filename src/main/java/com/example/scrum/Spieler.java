package com.example.scrum;

public class Spieler {
    String name;
    Land land;
    static int count = 1;
    static String land1;
    static String land2;

    public Spieler() {
    }

    public Spieler(String name, String land) {
        this.name = name;
        this.land = new Land(land);
        if (count == 1) {
            land1 = land;
        } else {
            land2 = land;
        }
        count++;
    }

    public String getLand1() {
        return land1;
    }
    public String getLand2() {
        return land2;
    }

    public String getName() {
        return name;
    }


}
