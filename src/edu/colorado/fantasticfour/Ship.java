package edu.colorado.fantasticfour;

import org.jetbrains.annotations.NotNull;

import java.util.List;

// This is the  baseclass for your ship.  Modify accordingly

public class Ship {
    private String name;
    private int length;
    private int hitCount;
    private List<Cell> coordinates;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.hitCount = 0;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public boolean isSunk(){
       return this.hitCount == this.length;
    }

    public boolean isCellAHit(Cell cell){
        assert this.coordinates.contains(cell);
        this.hitCount++;
        return true;
    }

    public List<Cell> getCoordinates() {
        if(this.coordinates == null || this.coordinates.size() == 0){
            return null;
        }
        return this.coordinates;
    }

    public void setCoordinates(List<Cell> cells){
        this.coordinates = cells;
    }

}
