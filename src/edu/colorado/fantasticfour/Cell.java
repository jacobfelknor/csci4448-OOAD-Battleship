package edu.colorado.fantasticfour;

import java.util.Comparator;

public class Cell {
    private Location location;
    private Ship ship;
    public Cell(Location location) throws IllegalArgumentException{
        if(Board.isOnBoard(location)){
            this.location = location;
            this.ship = null;
        }
    }

    public Location getLocation(){
        return this.location;
    }

    public void setShip(Ship ship){
        this.ship = ship;
    }

    public Ship getShip(){
        return this.ship;
    }

    @Override
    public String toString(){
        return "Cell<" + getLocation().toString() + ">";
    }

    @Override
    public boolean equals(Object o){
        /* from https://www.geeksforgeeks.org/overriding-equals-method-in-java/ */
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Cell or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Cell)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Cell c = (Cell) o;

        return this.location.equals(c.location);
    }

    /* NOTE: we should also override the hashCode(). Equal objects should generate equal hashes */

}

class CellComparator implements Comparator<Cell>{
    @Override
    public int compare(Cell a, Cell b){
        if(a.getLocation().getX() == b.getLocation().getX()){
            // if x's are equal, sort by y's
            return a.getLocation().getY() - b.getLocation().getY();
        }else{
            // otherwise, sort by x's (y's must be equal)
            assert a.getLocation().getY() == b.getLocation().getY();
            return a.getLocation().getX() - b.getLocation().getX();
        }
    }
}