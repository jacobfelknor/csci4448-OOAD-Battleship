package edu.colorado.fantasticfour.location;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.observer.Observer;
import edu.colorado.fantasticfour.ship.Ship;

import java.util.List;

public class ShipGPS extends GPS {
    private Ship owner;

    public ShipGPS(Ship owner){
        this.owner = owner;
    }

    public String update(Object from){
        assert from instanceof Cell;
        Cell c = (Cell) from;
        return this.owner.resultOfHit(c);
    }
}
