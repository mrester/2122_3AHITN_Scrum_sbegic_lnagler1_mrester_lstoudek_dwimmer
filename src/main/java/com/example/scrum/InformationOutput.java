package com.example.scrum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InformationOutput {


    /**
     * Funktionsbeschreibung
     * whichPlayer dient zur ausgabe welcher Spieler drann ist
     */

    public void fileManager() throws IOException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeDate = date.format(formatter);
        String timeTime = time.format(formatterTime);
        LocalTime parsedDate = LocalTime.parse(timeTime, formatterTime);

        Path path = Paths.get("logs.txt");
        File f = new File("logs.txt");
        if (f.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File already exists");
        }

        String text = timeDate + " " + parsedDate + " Schiff versenggdt an pos 1\r\n";
        FileOutputStream fos = new FileOutputStream("logs.txt", true);
        fos.write(text.getBytes());
        // fos.flush();
        // fos.close();
    }

    public void whichPlayer() { // Welcher Spieler drann ist

    }
    public void PlayerCreated(String name){

    }

    /**
     * Funktionsbeschreibung
     * isHit dient dazu auszugeben ob etwas getroffen wurde oder nicht
     */
    public void isHit() {

    }

    /**
     * Funktionsbeschreibung
     * whichFieldHit dient dazu wenn ein Feld getroffen wurde auszugeben welches getroffen wurde
     */
    public void whichFieldHit() {

    }

    /**
     * Funktionsbeschreibung
     * whichAbility dient dazu auszugeben welches Fähigkeit ausgewählt wurde
     */
    public void whichAbility() {

    }

}