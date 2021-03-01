package edu.colorado.fantasticfour;

public class Cell {
    private int x;
    private int y;
    private Ship ship;
    public Cell(int x, int y) throws IllegalArgumentException{
        if(Board.isOnBoard(x,y)){
            this.x = x;
            this.y = y;
            this.ship = null;
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return y;
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

        return (this.x == c.x) && (this.y == c.y);
    }

    /* NOTE: we should also override the hashCode(). Equal objects should generate equal hashes */

}
