package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

public class Bomb extends Weapon{
    public Bomb(Player owner) {
        super(owner);
    }

    @Override
    public String useAt(Location location) {
        Cell targetCell = this.owner.getTheirBoard().getCellAt(location);
        return targetCell.notifyObservers();
    }
}
