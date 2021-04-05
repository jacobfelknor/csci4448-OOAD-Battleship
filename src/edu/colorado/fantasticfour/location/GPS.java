package edu.colorado.fantasticfour.location;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.observer.Observer;

import java.util.List;

public abstract class GPS implements Observer {
    protected List<Cell> coordinates;

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
}
