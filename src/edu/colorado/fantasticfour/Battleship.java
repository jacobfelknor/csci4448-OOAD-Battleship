package edu.colorado.fantasticfour;

public class Battleship extends Ship {
    public boolean hasCaptainQArmor = true;
    public Battleship(){
        this.name = "Battleship";
        this.length = 4;
        this.captainsQ = 2;
    }

    @Override
    public boolean isCellAHit(Cell cell) {
        assert this.getCoordinates().contains(cell);
        if(this.getCoordinates().get(this.captainsQ).equals(cell)){
            if(hasCaptainQArmor){
                this.hasCaptainQArmor = false;
                return false;
            }else {
                this.sunk = true;
            }
        }
        return true;
    }
}
