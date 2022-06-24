package com.example.scrum;

/** Author: sbegic
 * Spieler Klasse zum Speichern des Namens und Landes
 * @name speichert den Spieler namen
 * @land für eigentlichen Nutzen der Land Klasse gedacht. Siehe Land.java
 * @count fürs hochzählen um 2 Länder Speichern zu können.
 * @land1 speicher das Land des ersten Spielers.
 * @land2 speicher das Land des zweiten Spielers.
 * Spieler() leerer Konstruktor
 * Spieler(String name, String land) Konstruktor.
 * getLand1() gibt das erste Land zurück.
 * getLand2() gibt das zweite Land zurück.
 *
 */
public class Spieler {
    public String name;
    public Land land;
    static int count = 1;
    private static String land1;
    private static String land2;

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

}
