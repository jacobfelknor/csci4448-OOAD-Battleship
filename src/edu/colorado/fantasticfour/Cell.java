package edu.colorado.fantasticfour;

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
