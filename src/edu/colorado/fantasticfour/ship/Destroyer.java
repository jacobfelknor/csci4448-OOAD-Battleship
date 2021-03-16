package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Destroyer extends Ship {
    private boolean hasCaptainQArmor = true;
    public Destroyer(){
        this.name = "Destroyer";
        this.length = 3;
        this.captainsQuartersBehavior = new CaptainsQuartersWithArmor(this);
        this.gps = new ShipGPS(this);
    }

    @Override
    public List<Location> getDimensions(Location captainsQ, String orientation) {
        assert captainsQ.getZ() == 0; // a destroyer must be on surface
        List<Location> dimensions = new ArrayList<>();
        dimensions.add(captainsQ);
        switch (orientation) {
            case "N", "S" -> {
                dimensions.add(captainsQ.plus(Location.jHat()));
                dimensions.add(captainsQ.minus(Location.jHat()));
            }
            case "W", "E" -> {
                dimensions.add(captainsQ.plus(Location.iHat()));
                dimensions.add(captainsQ.minus(Location.iHat()));
            }
            default -> throw new IllegalArgumentException(
                    "Unknown orientation. Must be N,S,E, or W. '" + orientation + "' given"
            );
        }
        assert dimensions.size() == this.getLength();
        return dimensions;
    }
}
