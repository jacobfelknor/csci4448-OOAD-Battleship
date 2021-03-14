package edu.colorado.fantasticfour.location;

import java.util.Objects;

public class Location{
    private final int x;
    private final int y;
    private final int z;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ(){
        return z;
    }

    public Location(int x, int y){
        this.x = x;
        this.y = y;
//      assuming z is 0 if not specified
        this.z = 0;
    }

    public Location(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Location iHat(){
        return new Location(1,0, 0);
    }

    public static Location jHat(){
        return new Location(0, 1, 0);
    }

    public static Location kHat(){
        return new Location(0,0,1);
    }

    @Override
    public String toString(){
        return "Location<(" + this.getX() + "," + this.getY() + "," + this.getZ() + ")>";
    }

    public Location plus(Location l){
        return new Location(
                this.getX() + l.getX(),
                this.getY() + l.getY(),
                this.getZ() + l.getZ()
        );
    }

    public Location minus(Location l){
        return new Location(
                this.getX() - l.getX(),
                this.getY() - l.getY(),
                this.getZ() - l.getZ()
        );
    }

    public Location times(int n){
        return new Location(
                this.getX()*n,
                this.getY()*n,
                this.getZ()*n
        );
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

        return (this.getX() == c.getX()) && (this.getY() == c.getY()) && (this.getZ() == c.getZ());
    }

    /* NOTE: we should also override the hashCode(). Equal objects should generate equal hashes */

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
