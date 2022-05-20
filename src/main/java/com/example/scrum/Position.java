package com.example.scrum;

public class Position {
    int col;
    int row;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    Position(){
        col = 0;
        row = 0;
    }

    Position(int col, int row){
        setCol(col);
        setRow(row);
    }
}
