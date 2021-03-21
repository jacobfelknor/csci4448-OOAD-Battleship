package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

public class CaptainsQuartersNoArmor implements CaptainsQuartersBehavior{
    private Location captainsQ;
    private Ship ship;
    public CaptainsQuartersNoArmor(Ship ship){
        this.ship = ship;
    }

    @Override
    public void setCaptainsQuarters(Location location) {
        this.captainsQ = location;
    }

    @Override
    public Location getCaptainsQuarters() {
        return this.captainsQ;
    }

    @Override
    public boolean checkWithCaptainsQuarters(Location location) {
        if(location.equals(this.captainsQ)){
            ship.sunk = true;
            return true;
        }
        return true;
    }
}
