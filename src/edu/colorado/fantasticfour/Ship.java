package edu.colorado.fantasticfour;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// This is the  baseclass for your ship.  Modify accordingly

public abstract class Ship {
    protected String name;
    protected int length;
    protected int captainsQ;
    protected boolean sunk = false;
    private List<Cell> coordinates;

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public boolean isSunk(){
       return this.sunk;
    }

    public abstract boolean isCellAHit(Cell cell);

    public List<Cell> getCoordinates() {
        if(this.coordinates == null || this.coordinates.size() == 0){
            return null;
        }
        return this.coordinates;
    }

    public void setCoordinates(List<Cell> cells){
        // sort coordinates first by x's, then by y's.
        // Result is a consistent ordering of cells list regardless of order given
        assert cells.size() == this.length;
        cells.sort(new CellComparator());
        this.coordinates = cells;
    }

}