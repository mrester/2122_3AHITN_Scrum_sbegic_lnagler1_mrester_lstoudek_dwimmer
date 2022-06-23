package com.example.scrum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InformationOutput {

    Path path = Paths.get("logs.txt");
    File f = new File("logs.txt");
    String Playername = "";


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
    public FileOutputStream WriteIntoFile() throws IOException {

        FileOutputStream fos = new FileOutputStream("logs.txt", true);
        return fos;
    }

    public void ProssesPlayerInfo(String name, String land) throws IOException {
        System.out.println(name + land);
        setName(name);
        this.Playername = name;
        String text = getTime() + " Spieler mit dem Namen: " +  name + " und dem Land: " + land + " wurde erstellt\r\n";
        FileOutputStream fos = new FileOutputStream("logs.txt", true);
        fos.write(text.getBytes());
        fos.close();
    }

    public void setName(String name){
        this.Playername = name;
    }
    public String getName(){

        return Playername;
    }
    public String setShipType(int id){
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
        return shipType;
    }




    public void isHit() {

    }


    public void whichFieldHit() {

    }
    /**
     * Funktionsbeschreibung
     * shipHitorMiss dient dazu auszugeben ob etwas getroffen wurde oder nicht und wo etwas getroffen wurde
     */
    public void shipHitOrMiss(Position pos, int identifier) throws IOException {
        String text = "";
       // String shipType = setShipType();

        if (identifier == 0){
             text = getTime() + " Schiff wurde an Row: " + pos.row + " und an Colum: " + pos.col + " Getroffen \r\n";

        } else {
            String player = getName();
            text = getTime() + player + " Hat daneben geschossen. Auf Feld: ( " + pos.row + " / " + pos.col + " )" + "\r\n";
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