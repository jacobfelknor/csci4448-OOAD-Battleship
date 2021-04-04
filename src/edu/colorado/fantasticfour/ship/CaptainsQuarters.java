package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

public class CaptainsQuarters{
    private int armor;
    private Location captainsQ;
    private Ship ship;

    public CaptainsQuarters(Ship ship, int armor){
        this.ship = ship;
        this.armor = armor;
    }

    public void setCaptainsQuarters(Location location) {
        this.captainsQ = location;
    }

    public Location getCaptainsQuarters() {
        return this.captainsQ;
    }

    public boolean checkWithCaptainsQuarters(Location location) {
        if(location.equals(this.captainsQ)){
            if(this.armor > 0){
                this.armor--;
                return false;
            }else {
                ship.sunk = true;
                return true;
            }
        }
        return true;
    }
}
