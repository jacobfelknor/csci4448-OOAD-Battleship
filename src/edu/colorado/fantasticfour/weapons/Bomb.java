package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

public class Bomb extends Weapon{
    public Bomb(Player owner) {
        super(owner);
    }
    private String name = "Bomb";

    @Override
    public String useAt(Location location) {
        if(this.owner.getTheirBoard().isBelowSurface(location)){
            throw new IllegalArgumentException("A Bomb can only be used on the surface. Location given was " + location);
        }
        Cell targetCell = this.owner.getTheirBoard().getCellAt(location);
        return targetCell.notifyObservers();
    }

}
