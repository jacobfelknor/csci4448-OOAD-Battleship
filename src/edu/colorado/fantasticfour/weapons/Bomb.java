package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

public class Bomb extends Weapon{
    public Bomb(Player owner) {
        super(owner);
    }

    /*
    The
    player receives the activation codes for the space laser only after sinking the first
    enemy ship (i.e. this weapon is an upgrade, and replaces the conventional bomb in the
            player’s arsenal). In the future we might add new types of weapons*/

    @Override
    public String useAt(Location location) {
        if(!this.owner.getTheirBoard().isOnSurface(location)){
            throw new IllegalArgumentException("A Bomb can only be used on the surface. Location given was " + location);
        }
        Cell targetCell = this.owner.getTheirBoard().getCellAt(location);
        return targetCell.notifyObservers();
    }
}
