package edu.colorado.fantasticfour;

public class Destroyer extends Ship {
    private boolean hasCaptainQArmor = true;
    public Destroyer(){
        this.name = "Destroyer";
        this.length = 3;
        // a destroyer has captains q in position 1
        this.captainsQuartersBehavior = new CaptainsQuartersWithArmor(1);
    }
}
