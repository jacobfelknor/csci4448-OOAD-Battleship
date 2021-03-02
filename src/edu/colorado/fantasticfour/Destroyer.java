package edu.colorado.fantasticfour;

public class Destroyer extends Ship {
    private boolean hasCaptainQArmor = true;
    public Destroyer(){
        this.name = "Destroyer";
        this.length = 3;
        this.captainsQ = 1;
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
