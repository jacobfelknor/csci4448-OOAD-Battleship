package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;

import java.io.InputStream;

public class LocalGame extends Game{

    public LocalGame(){
        super();
    }

    public LocalGame(InputStream in) {
        super(in);
    }

    @Override
    protected String gameTakeShot(Player player, Location location) {
        return player.takeShot(location);
    }

    public void loopGame(){
        while(!(player1.mustSurrender() || player2.mustSurrender())){
            if(whoseTurn().equals(player1)){
                // player one takes a shot
                takeShotFromScanner("1");
            }else{
                // player two takes a shot
                takeShotFromScanner("2");
            }
            toggleTurn();
        }
        if(player1.mustSurrender()){
            System.out.println("Congratulations Player 2, you have won the game!");
        }else{
            System.out.println("Congratulations Player 1, you have won the game!");
        }
    }

    public void start(){
        // first order of business is to ask for Locations of player 1 Ships
        collectShipLocationsFromPlayer("1");
        // now, ask for player 2's
        collectShipLocationsFromPlayer("2");
        // start a simple game
        loopGame();
    }
}
