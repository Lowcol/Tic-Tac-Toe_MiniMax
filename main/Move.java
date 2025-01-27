package main;

import java.util.Objects;

public class Move
{
    private int row;
    private int col;
    private int score;

    public Move(){
        row = -1;
        col = -1;
    }

    public Move(int r, int c){
        row = r;
        col = c;
    }

    public Move(int r, int c, int s) {
        row = r;
        col = c;
        score = s;
    }


    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getScore() { return score; }

    public void setRow(int r){
        row = r;
    }

    public void setCol(int c){
        col = c;
    }

    public void setScore(int s) { score = s; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return row == move.row &&
                col == move.col &&
                score == move.score;
    }


    @Override
    public int hashCode() {
        return Objects.hash(row, col, score);
    }

    @Override
    public String toString() {
        return String.format("Move{row=%d, col=%d, score=%d}", row, col, score);
    }
}
