package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.observer.Observer;

import java.util.List;

public class ShipGPS implements Observer {
    private List<Cell> coordinates;
    private Ship owner;

    public ShipGPS(Ship owner){
        this.owner = owner;
    }

    public List<Cell> getCoordinates() {
        if(this.coordinates == null || this.coordinates.size() == 0){
            return null;
        }
        return this.coordinates;
    }

    public void setCoordinates(List<Cell> cells){
        // Need to clear any of its previous subscriptions if they exist
        if(this.coordinates != null){
            for(Cell cell : this.coordinates){
                cell.removeObserver(this);
            }
        }
        for(Cell cell : cells){
            cell.addObserver(this);
        }
        this.coordinates = cells;
    }

    public String update(Object from){
        assert from instanceof Cell;
        Cell c = (Cell) from;
        return this.owner.resultOfHit(c);
    }
}
