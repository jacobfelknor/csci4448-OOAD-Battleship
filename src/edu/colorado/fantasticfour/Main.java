package edu.colorado.fantasticfour;

import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.NetworkGame;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
<<<<<<< Updated upstream
        //Game game = new NetworkGame();
        Game game = new LocalGame();
        game.start();
    }
}
