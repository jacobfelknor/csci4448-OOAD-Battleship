package edu.colorado.fantasticfour;

public class Location {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location(int x, int y){
        this.x = x;
        this.y = y;
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
        if (!(o instanceof Location)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Location c = (Location) o;

        return (this.getX() == c.getX()) && (this.getY() == c.getY());
    }

    /* NOTE: we should also override the hashCode(). Equal objects should generate equal hashes */

}
