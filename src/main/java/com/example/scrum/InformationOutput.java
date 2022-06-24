package com.example.scrum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/*
 * @author dwimmer1
 */

public class InformationOutput {

    File f = new File("logs.txt");
    public String Playername;
    String ship;
    MiddleMan middleMan;

    {
        try {
            middleMan = new MiddleMan();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Funktionsbeschreibung
     * fileManager ist dazu da das File zu erstellen
     */
    public void fileManager() throws IOException {
        if (f.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File already exists");
        }


    }
    /**
     * Funktionsbeschreibung
     * whichPlayer dient zur ausgabe welcher Spieler drann ist
     */
    public void whichPlayer() { // Welcher Spieler drann ist

    }

    /**
     * Funktionsbeschreibung
     * ProssesPlayerInfo schreibt ins File wenn ein Spieler mit einem land erstellt wurde
     */
    public void ProssesPlayerInfo(String name, String land) throws IOException {
        System.out.println(name + land);
      //  setName(name);
       // this.Playername = name;
        String text = getTime() + " Spieler mit dem Namen: " +  name + " und dem Land: " + land + " wurde erstellt\r\n";
        FileOutputStream fos = new FileOutputStream("logs.txt", true);
        fos.write(text.getBytes());
        fos.close();
    }
    /**
     * Funktionsbeschreibung
     * shipInfo schreibt ins File wenn ein Schiff an einer position getroffen wurde
     */
    public void shipInfo(String shipRotation, String shipType, String x, String y) throws IOException {
        shipType = ship;

        String text = getTime() + " Ein " + shipType + " wurde an position ( " + x + " / " + y + " ) platziert, mit der Ausrichtung: " + shipRotation + "\r\n";

        FileOutputStream fos = new FileOutputStream("logs.txt", true);
        fos.write(text.getBytes());
        fos.close();
    }
    /*
    public void setName(String name){
        this.Playername = name;
    }
    public String getName(){

        return Playername;
    }
`*/
    /**
     * Funktionsbeschreibung
     * shipInfo gibt den Typ des schiffes weiter
     */
    public void setShipType(int id){
        String shipType = "";
        if (id == 0){
            shipType = "U-Boot";
        }else if (id == 1){
            shipType = "Zerstörer";
        }else if (id == 2){
            shipType = "Kreuzer";
        }else{
            shipType = "Schlachtschiff";
        }
        this.ship = shipType;
    }


    /**
     * Funktionsbeschreibung
     * shipHitorMiss dient dazu auszugeben ob etwas getroffen wurde oder nicht und wo etwas getroffen wurde
     */
    public void shipHitOrMiss(Position pos, int identifier) throws IOException {
        String text = "";

        if (identifier == 0){
             text = getTime() + ship + " wurde an Row: " + pos.row + " und an Colum: " + pos.col + " Getroffen \r\n";

        } else {

            text = getTime() + " Spieler"  + " Hat daneben geschossen. Auf Feld: ( " + pos.row + " / " + pos.col + " )" + "\r\n";
        }

        FileOutputStream fos = new FileOutputStream("logs.txt", true);
        fos.write(text.getBytes());
        fos.close();

    }

    /**
     * Funktionsbeschreibung
     * whichAbility dient dazu auszugeben welches Fähigkeit ausgewählt wurde
     */
    public void whichAbility() {

    }
    /**
     * Funktionsbeschreibung
     * shipHitorMiss dient für die ausgabe des Dates und der Zeit
     */
    public String getTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeDate = date.format(formatter);
        String timeTime = time.format(formatterTime);
        LocalTime parsedDate = LocalTime.parse(timeTime, formatterTime);
        return timeDate + " " + parsedDate;
    }



}