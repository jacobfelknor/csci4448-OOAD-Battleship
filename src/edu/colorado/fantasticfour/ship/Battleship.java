package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Battleship extends Ship {
    public boolean hasCaptainQArmor = true;
    public Battleship(){
        this.name = "Battleship";
        this.length = 4;
        this.captainsQuartersBehavior = new CaptainsQuartersWithArmor(this);
        this.gps = new ShipGPS(this);
    }

    @Override
    public List<Location> getDimensions(Location captainsQ, String orientation) {
        assert captainsQ.getZ() == 0; // a battleship must be on surface
        List<Location> dimensions = new ArrayList<>();
        dimensions.add(captainsQ);
        switch (orientation) {
            case "N" -> {
                dimensions.add(captainsQ.plus(Location.jHat()));
                dimensions.add(captainsQ.minus(Location.jHat()));
                dimensions.add(captainsQ.minus(Location.jHat().times(2)));
            }
            case "S" -> {
                dimensions.add(captainsQ.minus(Location.jHat()));
                dimensions.add(captainsQ.plus(Location.jHat()));
                dimensions.add(captainsQ.plus(Location.jHat().times(2)));
            }
            case "E" -> {
                dimensions.add(captainsQ.plus(Location.iHat()));
                dimensions.add(captainsQ.minus(Location.iHat()));
                dimensions.add(captainsQ.minus(Location.iHat().times(2)));
            }
            case "W" -> {
                dimensions.add(captainsQ.plus(Location.iHat()));
                dimensions.add(captainsQ.minus(Location.iHat()));
                dimensions.add(captainsQ.plus(Location.iHat().times(2)));
            }
            default -> throw new IllegalArgumentException(
                    "Unknown orientation. Must be N,S,E, or W. '" + orientation + "' given"
            );
        }
        assert dimensions.size() == this.getLength();
        return dimensions;
    }
}
