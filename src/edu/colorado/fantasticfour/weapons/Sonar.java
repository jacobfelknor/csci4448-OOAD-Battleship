package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.game.Player;

public class Sonar {
    public int movesLeft = 2;
    private Player player;
    private boolean[] sonarResults = new boolean[13];
    private Location target;

    public Sonar(Player player) {
        this.player = player;
        for (int i = 0; i < 13; i++){
            sonarResults[i] = false;
        }
    }

    public void setTarget(Location location){
       if (this.player.getMyBoard().isOnBoard(location)){
           player.sonar.target = location;
       }else{
           throw new IllegalArgumentException("Location does not exist on this board");
       }
    }

    public int movesRemain() {
        return player.sonar.movesLeft;
    }

    public boolean canUseSonar(){
        int afloatShips = player.getOpponent().getAfloatShips().size();
        int allShips = player.getOpponent().getAllShips().size();
        return ((movesLeft < 1) && (afloatShips < allShips));
    }

    public void useSonar() {
        if (canUseSonar()) {
            throw new IllegalArgumentException("No moves left");
        }
        else{
            // top row
            sonarResults[0] = getSonarAt(player.sonar.target.getX(), player.sonar.target.getY()-2);
            // second row
            sonarResults[1] = getSonarAt(player.sonar.target.getX()-1, player.sonar.target.getY()-1);
            sonarResults[2] = getSonarAt(player.sonar.target.getX(), player.sonar.target.getY()-1);
            sonarResults[3] = getSonarAt(player.sonar.target.getX()+1, player.sonar.target.getY()-1);
            // third row
            // put true for test case, actual code commented out
            sonarResults[4] = getSonarAt(player.sonar.target.getX()-2, player.sonar.target.getY());
            sonarResults[5] = getSonarAt(player.sonar.target.getX()-1, player.sonar.target.getY());
            sonarResults[6] = getSonarAt(player.sonar.target.getX(), player.sonar.target.getY());
            sonarResults[7] = getSonarAt(player.sonar.target.getX()+1, player.sonar.target.getY());
            sonarResults[8] = getSonarAt(player.sonar.target.getX()+2, player.sonar.target.getY());
            // fourth row
            sonarResults[9] = getSonarAt(player.sonar.target.getX()-1, player.sonar.target.getY()+1);
            sonarResults[10] = getSonarAt(player.sonar.target.getX(), player.sonar.target.getY()+1);
            sonarResults[11] = getSonarAt(player.sonar.target.getX()+1, player.sonar.target.getY()+1);
            sonarResults[12] = getSonarAt(player.sonar.target.getX(), player.sonar.target.getY()+2);

            movesLeft -= 1;
        }
    }

    public boolean[] getSonarResults(){
        return sonarResults;
    }

    public boolean getSonarAt(Location location) {
        if(!player.getMyBoard().isOnBoard(location)){
            return false;
        }

        return player.getTheirBoard().getCellAt(location).hasObservers();
    }

    public boolean getSonarAt(int x, int y){
        return this.getSonarAt(new Location(x, y));
    }
}
