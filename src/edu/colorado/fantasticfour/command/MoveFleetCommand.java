package edu.colorado.fantasticfour.command;

import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class MoveFleetCommand implements Command{
    private Player owner;



    private List<Ship> affectedShips;
    private String direction;
    public static String oppositeDirectionOf(String direction){
        String opposite;
        switch (direction){
            case "N" -> opposite = "S";
            case "S" -> opposite = "N";
            case "W" -> opposite = "E";
            case "E" -> opposite = "W";
            default -> throw new IllegalArgumentException("Direction must be one of N, S, E, W. Given + '"
                    + direction + "'");
        }
        return opposite;
    }

    public MoveFleetCommand(Player owner, String direction){
        this.owner = owner;
        this.affectedShips = this.owner.getAfloatShips();
        this.direction = direction;
    }

    private void moveShip(Ship ship, String direction){
        Location moveVector;
        switch (direction){
            case "N" -> moveVector = Location.jHat();
            case "S" -> moveVector = Location.jHat().times(-1);
            case "W" -> moveVector = Location.iHat().times(-1);
            case "E" -> moveVector = Location.iHat();
            default -> throw new IllegalArgumentException("Direction must be one of N, S, E, W. Given + '"
                    + direction + "'");
        }
        this.owner.placeShip(ship.getName(), ship.getCaptainsQuarters().plus(moveVector), ship.getOrientation());
    }

    @Override
    public void execute() {
        // new list of affected ships. Add ships that move successfully here. Avoids removing elements
        // of this.affectedShips while iteration
        List<Ship> newAffectedShips = new ArrayList<>();
        for(Ship ship : this.affectedShips){
            try{
                this.moveShip(ship, this.direction);
                // move was successful. Add ship to newAffectedShips
                newAffectedShips.add(ship);
            }catch (IllegalArgumentException e){
                // do nothing, ship could not have been moved here.
            }
        }
        // reset this.affectedShips
        this.affectedShips = newAffectedShips;
    }

    @Override
    public void undo() {
        // if a move was done, it should always be possible to undo (because it had to start from a legal state)
        // no need to verify as in the execute() method
        for(Ship ship : this.affectedShips){
            this.moveShip(ship, oppositeDirectionOf(this.direction));
        }
    }

}
