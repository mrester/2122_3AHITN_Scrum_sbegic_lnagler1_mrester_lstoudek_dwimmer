package com.example.scrum;

/** Author: sbegic
 * Land Klasse
 * @name für die speicherung des Land namens
 * Land() Konstruktor der Land Klasse
 * getName() liefert den @name Parameter der Klass zurück
 *
 * Eigentlicher Nutzen: Für Wunschkriterien wie z.B.: Fähigkeiten und Gadgets.
 */
public class Land {
    public String name; // Eigenschaft name der Klasse


    public Land(String land) {//Konstruktor Methode
        this.name = land;
    }

    public String getName() {//liefert die Eigenschaft name zurück
        return name;
    }

}
