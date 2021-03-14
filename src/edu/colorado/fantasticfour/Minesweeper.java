package edu.colorado.fantasticfour;

import java.util.ArrayList;
import java.util.List;

public class Minesweeper extends Ship {

    public Minesweeper(){
        this.name = "Minesweeper";
        this.length = 2;
        this.captainsQuartersBehavior = new CaptainsQuartersNoArmor(this);
        this.gps = new ShipGPS(this);
    }

    @Override
    public List<Location> getDimensions(Location captainsQ, String orientation) {
        List<Location> dimensions = new ArrayList<>();
        dimensions.add(captainsQ);
        switch (orientation) {
            case "N" -> dimensions.add(captainsQ.minus(Location.jHat()));
            case "S" -> dimensions.add(captainsQ.plus(Location.jHat()));
            case "W" -> dimensions.add(captainsQ.plus(Location.iHat()));
            case "E" -> dimensions.add(captainsQ.minus(Location.iHat()));
            default -> throw new IllegalArgumentException("Unknown orientation. Must be N,S,E, or W");
        }
        assert dimensions.size() == this.getLength();
        return dimensions;
    }
}
