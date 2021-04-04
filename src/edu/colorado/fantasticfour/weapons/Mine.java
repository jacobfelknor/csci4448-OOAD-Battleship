package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

public class Mine extends Weapon{

    public Mine(Player owner) {
        super(owner);
    }

    @Override
    public String useAt(Location location) {
        return null;
    }
}
