package com.example.scrum;

public class Spieler {
    String name;
    Land land;


    public Spieler() {
    }

    public Spieler(String name, String land) {
        this.name = name;
        this.land = new Land(land);
    }


    public String getName() {
        return name;
    }


}
