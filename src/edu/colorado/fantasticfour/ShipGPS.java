package edu.colorado.fantasticfour;

import java.util.List;

public class ShipGPS implements Observer{
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
        // sort coordinates by location
        // Result is a consistent ordering of cells list regardless of order given
        for(Cell cell : cells){
            cell.addObserver(this);
        }
        cells.sort(new CellComparator());
        this.coordinates = cells;
    }

    public String update(Object from){
        assert from instanceof Cell;
        Cell c = (Cell) from;
        return this.owner.resultOfHit(c);
    }
}
