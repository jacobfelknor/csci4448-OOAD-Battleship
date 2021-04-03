package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Submarine extends Ship{

    public Submarine(){
        this.name = "Submarine";
        this.length = 5; // NOTE: length in this case simply means # of cells
        this.captainsQuarters = new CaptainsQuarters(this, 1);
        this.gps = new ShipGPS(this);
    }

    private void getDimensionsExceptionHelper(String given){
        String orientationExceptionMsg =
                "Unknown orientation. Submarine requires an orientation in form 'XY', where each is a " +
                        "direction N,E,S,W and describe different axis. i.e 'NS' is not allowed but 'NW' or 'NE' is.";
        throw new IllegalArgumentException(orientationExceptionMsg + " '" + given + "' given");
    }

    @Override
    public List<Location> getDimensions(Location captainsQ, String orientation) {
        // since a submarine is not symmetric, need another direction to fully describe location
        if(orientation.length() != 2){
            this.getDimensionsExceptionHelper(orientation);
        }
        char first = orientation.charAt(0);
        char second = orientation.charAt(1);
        List<Location> dimensions = new ArrayList<>();
        dimensions.add(captainsQ);
        switch (first) {
            case 'N' -> {
                dimensions.add(captainsQ.minus(Location.jHat()));
                dimensions.add(captainsQ.minus(Location.jHat().times(2)));
                dimensions.add(captainsQ.minus(Location.jHat().times(3)));
                switch (second){
                    case 'W' -> // notch is to the west
                            dimensions.add(captainsQ.minus(Location.jHat()).minus(Location.iHat()));
                    case 'E' -> // notch is to the east
                            dimensions.add(captainsQ.minus(Location.jHat()).plus(Location.iHat()));
                    default -> this.getDimensionsExceptionHelper(orientation);
                }
            }
            case 'S' -> {
                dimensions.add(captainsQ.plus(Location.jHat()));
                dimensions.add(captainsQ.plus(Location.jHat().times(2)));
                dimensions.add(captainsQ.plus(Location.jHat().times(3)));
                switch (second){
                    case 'W' -> //notch is to the west
                            dimensions.add(captainsQ.plus(Location.iHat()).plus(Location.jHat()));
                    case 'E' -> // notch is to the east
                            dimensions.add(captainsQ.plus(Location.jHat()).minus(Location.iHat()));
                    default -> this.getDimensionsExceptionHelper(orientation);
                }
            }
            case 'W' -> {
                dimensions.add(captainsQ.plus(Location.iHat()));
                dimensions.add(captainsQ.plus(Location.iHat().times(2)));
                dimensions.add(captainsQ.plus(Location.iHat().times(3)));
                switch (second){
                    case 'N' ->// notch is to the north
                            dimensions.add(captainsQ.plus(Location.iHat()).plus(Location.jHat()));
                    case 'S' -> // notch is to the south
                            dimensions.add(captainsQ.plus(Location.iHat()).minus(Location.jHat()));
                    default -> this.getDimensionsExceptionHelper(orientation);
                }
            }
            case 'E' -> {
                dimensions.add(captainsQ.minus(Location.iHat()));
                dimensions.add(captainsQ.minus(Location.iHat().times(2)));
                dimensions.add(captainsQ.minus(Location.iHat().times(3)));
                switch (second){
                    case 'N' ->// notch is to the north
                            dimensions.add(captainsQ.minus(Location.iHat()).plus(Location.jHat()));
                    case 'S' -> // notch is to the south
                            dimensions.add(captainsQ.minus(Location.iHat()).minus(Location.jHat()));
                    default -> this.getDimensionsExceptionHelper(orientation);
                }
            }
            default -> this.getDimensionsExceptionHelper(orientation);
        }
        assert dimensions.size() == this.getLength();
        this.orientation = orientation;
        return dimensions;
    }
}
