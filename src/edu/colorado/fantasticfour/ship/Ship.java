package edu.colorado.fantasticfour.ship;

//import org.jetbrains.annotations.NotNull;

// This is the  baseclass for your ship.  Modify accordingly

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.location.Location;

import java.util.List;

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

    public ShipGPS getGps(){
        return this.gps;
    }

    public boolean isSunk(){
       return this.sunk;
    }

    public String resultOfHit(Cell cell) {
        if(this.captainsQuartersBehavior.checkWithCaptainsQuarters(cell.getLocation())){
            if(this.isSunk()){
                return "SUNK";
            }else{
                return "HIT";
            }
        }
        return "MISS";
    }

    public void setCaptainsQuarters(Location captainsQuarters){
        this.captainsQuartersBehavior.setCaptainsQuarters(captainsQuarters);
    }

    public abstract List<Location> getDimensions(Location captainsQ, String orientation);


}