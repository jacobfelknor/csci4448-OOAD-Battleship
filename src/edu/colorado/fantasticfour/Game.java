package edu.colorado.fantasticfour;

public class Game {
    private Player player1;

    public Player getPlayer(String player){
        return this.player1;
    }

    public Game(){
        // does nothing
        player1 = new Player();
    }

    public Player whoseTurn(){
        return this.player1;
    }
}
