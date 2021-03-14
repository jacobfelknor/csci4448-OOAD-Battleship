package edu.colorado.fantasticfour;

import java.util.List;

public class CaptainsQuartersWithArmor implements CaptainsQuartersBehavior{
    private boolean hasCaptainQArmor = true;
    private Location captainsQ;
    private Ship ship;

    public CaptainsQuartersWithArmor(Ship ship){
        this.ship = ship;
    }

    @Override
    public void setCaptainsQuarters(Location location) {
        this.captainsQ = location;
    }

    @Override
    public boolean checkWithCaptainsQuarters(Location location) {
        if(location.equals(this.captainsQ)){
            if(hasCaptainQArmor){
                this.hasCaptainQArmor = false;
                return false;
            }else {
                ship.sunk = true;
                return true;
            }
        }
        return true;
    }
}
