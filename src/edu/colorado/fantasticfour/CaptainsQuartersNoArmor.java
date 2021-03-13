package edu.colorado.fantasticfour;

public class CaptainsQuartersNoArmor implements CaptainsQuartersBehavior{
    private int captainsQ;

    public CaptainsQuartersNoArmor(int captainsQ){
        this.captainsQ = captainsQ;
    }
    @Override
    public boolean checkWithCaptainsQuarters(Ship ship, Cell cell) {
        if(ship.gps.getCoordinates().get(this.captainsQ).equals(cell)){
            ship.sunk = true;
            return true;
        }
        return true;
    }
}
