package edu.colorado.fantasticfour;

import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.NetworkGame;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Game networkGame = new NetworkGame();
        Game localGame = new LocalGame();
        localGame.start();
    }
}
