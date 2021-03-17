package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Cell;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.game.Player;

public class Sonar extends Weapon {
    //gotta think about delegation

    //public int movesLeft = 2;
    public static int sonarMovesLeft = 2;
    private Player owner;
    private boolean[] sonarResults = new boolean[13];
    private Location target;

    public Sonar(Player owner) {
        super(owner);
        this.owner = owner;
        for (int i = 0; i < 13; i++){
            sonarResults[i] = false;
        }
    }

    @Override
    public String useAt(Location location) {
        //checking to see if ysonar is on surface and not under water
        if(!this.owner.getTheirBoard().isOnSurface(location)){
            throw new IllegalArgumentException("Sonar can only be used on the surface. Location given was " + location);
        }
        //checking to see if the sonar is on board and not out of bounds
        if(!this.owner.getTheirBoard().isOnBoard(location)){
            throw new IllegalArgumentException("Location does not exist on this board");
        }

        Cell targetCell = this.owner.getTheirBoard().getCellAt(location);
        return targetCell.notifyObservers();
    }

    /*
    public void setTarget(Location location){
       if (this.player.getMyBoard().isOnBoard(location)){
           player.sonar.target = location;
       }else{
           throw new IllegalArgumentException("Location does not exist on this board");
       }
    }*/

    public int movesRemain() {
        /*since sonar is coming from owner and that owner has been
        //delegated the weapon and the weapon is being extended
        we can just call the variable itself*/
        return sonarMovesLeft;
        //return owner.sonar.movesLeft;
    }

    public boolean canUseSonar(){
        int afloatShips = owner.getOpponent().getAfloatShips().size();
        int allShips = owner.getOpponent().getAllShips().size();
        return ((sonarMovesLeft < 1) && (afloatShips < allShips));
    }

    public void useSonar() {
        if (canUseSonar()) {
            throw new IllegalArgumentException("No moves left");
        }
        else{
            // top row
            sonarResults[0] = getSonarAt(target.getX(), target.getY()-2, target.getZ());
            // second row
            sonarResults[1] = getSonarAt(target.getX()-1, target.getY()-1, target.getZ());
            sonarResults[2] = getSonarAt(target.getX(), target.getY()-1,0);
            sonarResults[3] = getSonarAt(target.getX()+1, target.getY()-1, target.getZ());
            // third row
            // put true for test case, actual code commented out
            sonarResults[4] = getSonarAt(target.getX()-2, target.getY(), target.getZ());
            sonarResults[5] = getSonarAt(target.getX()-1, target.getY(), target.getZ());
            sonarResults[6] = getSonarAt(target.getX(), target.getY(), target.getZ());
            sonarResults[7] = getSonarAt(target.getX()+1,target.getY(), target.getZ());
            sonarResults[8] = getSonarAt(target.getX()+2, target.getY(), target.getZ());
            // fourth row
            sonarResults[9] = getSonarAt(target.getX()-1, target.getY()+1, target.getZ());
            sonarResults[10] = getSonarAt(target.getX(), target.getY()+1, target.getZ());
            sonarResults[11] = getSonarAt(target.getX()+1, target.getY()+1, target.getZ());
            sonarResults[12] = getSonarAt(target.getX(), target.getY()+2, target.getZ());

            sonarMovesLeft -= 1;
        }
    }

    public boolean[] getSonarResults(){
        return sonarResults;
    }

    public boolean getSonarAt(Location location) {
        if(!owner.getMyBoard().isOnBoard(location)){
            return false;
        }

        return owner.getTheirBoard().getCellAt(location).hasObservers();
    }

    public boolean getSonarAt(int x, int y,int z){
        return this.getSonarAt(new Location(x, y,z));
    }
}
