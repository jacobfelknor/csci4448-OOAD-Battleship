package edu.colorado.fantasticfour;

public class Player {
    private Board myBoard;
    private Board theirBoard;

    public Board getMyBoard(){
        return myBoard;
    }

    public Board getTheirBoard() {
        return theirBoard;
    }

    public  Player() {
        // player constructor creates its boards
        this.myBoard = new Board();
        this.theirBoard = new Board();
    }
}
