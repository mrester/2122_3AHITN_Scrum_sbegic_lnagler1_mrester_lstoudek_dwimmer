package com.example.scrum;

public class ShotKeyBoardInput {
    private int[] shotPos = new int[2];

    ShotKeyBoardInput(int x, int y){
        shotPos[0] = x;
        shotPos[1] = y;
    }

    public int[] getShotPos() {
        return shotPos;
    }
}
