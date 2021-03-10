package edu.colorado.fantasticfour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Location{
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

    public static boolean inStraightLine(Location ... locations) throws IllegalArgumentException{
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for(Location location : locations){
            xs.add(location.getX());
            ys.add(location.getY());
        }
        long xDistinct = xs.stream().distinct().count();
        long yDistinct = ys.stream().distinct().count();
        return xDistinct == 1 ^ yDistinct == 1;
    }

    @Override
    public String toString(){
        return "Location<(" + this.getX() + "," + this.getY() + ")>";
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

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
