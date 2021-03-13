package edu.colorado.fantasticfour;

public class Battleship extends Ship {
    public boolean hasCaptainQArmor = true;
    public Battleship(){
        this.name = "Battleship";
        this.length = 4;
        // a battleship has captains q in position 2
        this.captainsQuartersBehavior = new CaptainsQuartersWithArmor(2);
        this.gps = new ShipGPS(this);
    }
}
