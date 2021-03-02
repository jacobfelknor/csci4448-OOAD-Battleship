package edu.colorado.fantasticfour;

public class Minesweeper extends Ship {

    public Minesweeper(){
        this.name = "Minesweeper";
        this.length = 2;
        this.captainsQ = 0;
    }

    @Override
    public boolean isCellAHit(Cell cell) {
        assert this.getCoordinates().contains(cell);
        if(this.getCoordinates().get(this.captainsQ).equals(cell)){
            this.sunk = true;
            return true;
        }
        return true;
    }


}
