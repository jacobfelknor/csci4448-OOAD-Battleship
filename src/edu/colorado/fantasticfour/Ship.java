package edu.colorado.fantasticfour;

//import org.jetbrains.annotations.NotNull;

// This is the  baseclass for your ship.  Modify accordingly

public abstract class Ship {
    protected String name;
    protected int length;
    protected CaptainsQuartersBehavior captainsQuartersBehavior;
    protected boolean sunk = false;
    protected ShipGPS gps;

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public boolean isSunk(){
       return this.sunk;
    }

    public String resultOfHit(Cell cell) {
        if(this.captainsQuartersBehavior.checkWithCaptainsQuarters(this, cell)){
            if(this.isSunk()){
                return "SUNK";
            }else{
                return "HIT";
            }
        }
        return "MISS";
    }



}