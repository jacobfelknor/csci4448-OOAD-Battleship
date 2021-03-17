package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

public class Laser extends Weapon{

    public Laser(Player owner) {
        super(owner);
    }

    //if an opponents ship has been sunk then the player
    //can access lasers
    public boolean canUseLaser(){
        int afloatShips = owner.getOpponent().getAfloatShips().size();
        int allShips = owner.getOpponent().getAllShips().size();
        return (afloatShips < allShips);
    }

    @Override
    public String useAt(Location location) {
        //x,y,z:,k
        Cell targetCell = this.owner.getTheirBoard().getCellAt(location);

        //if (this.owner.getTheirBoard().isSubSurface(location) || this.owner.getTheirBoard().isOnSurface(location)) {
            //throw new IllegalArgumentException("A Bomb can only be used on the surface. Location given was " + location);
        targetCell.notifyObservers();

        //}
        //Cell targetCell = this.owner.getTheirBoard().getCellAt(location);
        return targetCell.notifyObservers();
    }
}
