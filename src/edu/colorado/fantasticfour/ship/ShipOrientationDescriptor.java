package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

public class ShipOrientationDescriptor {
    // this class is designed only to hold a Location and an Orientation, the
    // information necessary to describe where a Ship is
    private Location location;
    private String orientation;

    public ShipOrientationDescriptor(Location location, String orientation){
        this.location = location;
        this.orientation = orientation;
    }

    public Location getLocation() {
        return location;
    }

    public String getOrientation() {
        return orientation;
    }

}
