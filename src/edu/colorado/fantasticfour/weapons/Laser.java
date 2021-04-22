package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import java.util.ArrayList;
import java.util.List;

public class Laser extends Weapon{
    private String name = "Laser";

    public Laser(Player owner) {
        super(owner);
    }

    @Override
    public String useAt(Location location) {
        List<String> results = new ArrayList<>();
        List<Cell> targets = this.owner.getTheirBoard().getCellsInColumn(location);
        for(Cell cell : targets){
            results.add(cell.notifyObservers());
        }
        if(results.contains("SUNK")){
            return "SUNK";
        }else if(results.contains("HIT")){
            return "HIT";
        }else {
            return "MISS";
        }
    }
}
