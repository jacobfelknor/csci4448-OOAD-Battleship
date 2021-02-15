package edu.colorado.fantasticfour;

public class Game {
    private Player player1;
    private Player player2;

    public Player getPlayer(String player){
        if(player.equals("1")){
            return this.player1;
        }else if (player.equals("2")){
            return this.player2;
        }
        throw new IllegalArgumentException("Game only has player '1' and '2'");
    }

    public Game(){
        // does nothing
        player1 = new Player();
        player2 = new Player();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    public Player whoseTurn(){
        return this.player1;
    }
}
