package edu.colorado.fantasticfour;

import java.util.List;

public class CaptainsQuartersWithArmor implements CaptainsQuartersBehavior{
    private boolean hasCaptainQArmor = true;
    private int captainsQ;

    public CaptainsQuartersWithArmor(int captainsQ){
        this.captainsQ = captainsQ;
    }

    @Override
    public boolean checkWithCaptainsQuarters(Ship ship, Cell cell) {
        if(ship.getCoordinates().get(this.captainsQ).equals(cell)){
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
