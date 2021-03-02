package edu.colorado.fantasticfour;

public class Minesweeper extends Ship {

    public Minesweeper(){
        this.name = "Minesweeper";
        this.length = 2;
        // a minesweeper has captains q in position 0
        this.captainsQuartersBehavior = new CaptainsQuartersNoArmor(0);
    }

}
