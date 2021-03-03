package edu.colorado.fantasticfour;

public class Sonar {
    public int movesLeft = 2;
    private Player player;
    private boolean[] sonarList;

    public Sonar(Player player) {
        this.player = player;
    }

    public int movesRemain() {
        return player.sonar.movesLeft;
    }

    public void useSonar() {
        if (movesLeft < 1) {
            throw new IllegalArgumentException("No moves left");
        }
        else{
            movesLeft -= 1;
        }
    }

//    public void setSonarCenter(int x, int y){
//        xTarget = x;
//        yTarget = y;
//    }

    public boolean[] getSonarAt(int x, int y) {
        //for (int i = 0; i < 13; i++) {

            if (player.getTheirBoard().getCellAt(x, y).getShip() == null) {
                sonarList[6] = false;
            } else {
                sonarList[6] = true;
            }
        //}
        return sonarList;
    }
}
