package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;

import java.util.ArrayList;

public class TerminalGrid {
    private int gridRow = 10;
    private int gridCol = 10;
    private String[][] gridSurf = new String[gridRow][gridCol];
    private String[][] gridSurfHit = new String[gridRow][gridCol];
    public ArrayList<String> hitLedger = new ArrayList<String>();
    public ArrayList<String> missLedger = new ArrayList<String>();
    public ArrayList<String> sonarHitLedger = new ArrayList<String>();
    public ArrayList<String> sonarMissLedger = new ArrayList<String>();
    public ArrayList<String> subHitLedger = new ArrayList<String>();
    public ArrayList<String> subMissLedger = new ArrayList<String>();
    private Player player;
    //private  Player player2;

    public TerminalGrid(Player player){
        for (int i = 0; i < gridCol; i++){
            for (int j = 0; j < gridRow; j++){
                this.gridSurf[i][j] = ".";
                this.gridSurfHit[i][j] = ".";
            }

        }
        this.player = player;
        //this.player2 = player.getOpponent();
    }

    public void PrintGrid(){

        int[] tmp = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean playerLaser = player.hasSunkOpponentShip();
        boolean playerSonar = player.getSonar().canUseSonar();
        System.out.println();
        System.out.println("mandem" + "                                  " + " opps");

        System.out.println("   0  1  2  3  4  5  6  7  8  9" + "          " + "   0  1  2  3  4  5  6  7  8  9");

        for (int i = 0; i < gridRow; i++){
            System.out.print(tmp[i] + "  ");

            for (int j = 0; j < gridCol; j++){

                System.out.print(gridSurf[i][j] + "  ");
                if (j == 9){
                    System.out.print("|       " + tmp[i] + "  ");
                    for (int z = 0; z < gridCol; z++ ){
                        System.out.print(gridSurfHit[i][z] + "  ");
                        if (z == 9) {
                            System.out.println("|");

                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("           Laser Enabled: " + playerLaser + "           Sonar Enabled: " + playerSonar);
        System.out.println();
    }

    public void FillGrid(Player player){


        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                Location location = new Location(i, j, 0);
                Location location2 = new Location(i, j, -1);

                if (player.getShipAt(location) == null && player.getShipAt(location2) == null){
                    gridSurf[i][j] = ".";
                }
                else if (player.getShipAt(location) != null && player.getShipAt(location2) != null){
                    gridSurf[i][j] = "B";
                }
                else if (player.getShipAt(location) == null && player.getShipAt(location2) != null){
                    gridSurf[i][j] = "s";
                }else {
                    gridSurf[i][j] = "o";
                }
//                if (player.getShipAt(location) == null){
//                    if (player.getShipAt(location2) == null){
//                        gridSurf[i][j] = ".";
//                    }else {
//                        gridSurf[i][j] = "s";
//                    }
//                }
//                else{
//                    Ship newShip = player.getShipAt(location);
//                    String shipName = newShip.getName();
//                    //if (player.getShipAt(location))
//                    if (shipName == "Submarine"){
//                        if (gridSurf[i][j] == "o") {
//                            gridSurf[i][j] = "B";
//                        }else if(gridSurf[i][j] == "s"){
//                            gridSurf[i][j] = "B";
//                        }
//                    }else {
//                        gridSurf[i][j] = "o";
//                    }
//                }
            }
        }

        for (int i = 0; i < sonarHitLedger.size(); i++){
            String hitStr = sonarHitLedger.get(i);
            int row = Integer.parseInt(hitStr.substring(0, 1));
            int col = Integer.parseInt(hitStr.substring(2, 3));
            gridSurfHit[row][col] = "!";
        }

        for (int i = 0; i < sonarMissLedger.size(); i++){
            String missStr = sonarMissLedger.get(i);
            int row = Integer.parseInt(missStr.substring(0, 1));
            int col = Integer.parseInt(missStr.substring(2, 3));
            gridSurfHit[row][col] = "~";
        }

        for (int i = 0; i < missLedger.size(); i++){
            String missStr = missLedger.get(i);
            int row = Integer.parseInt(missStr.substring(0, 1));
            int col = Integer.parseInt(missStr.substring(2, 3));
            gridSurfHit[row][col] = "x";
        }

        for (int i = 0; i < hitLedger.size(); i++){
            String hitStr = hitLedger.get(i);
            int row = Integer.parseInt(hitStr.substring(0, 1));
            int col = Integer.parseInt(hitStr.substring(2, 3));
            gridSurfHit[row][col] = "o";
        }

        for (int i = 0; i < subMissLedger.size(); i++){
            String missStr = subMissLedger.get(i);
            int row = Integer.parseInt(missStr.substring(0, 1));
            int col = Integer.parseInt(missStr.substring(2, 3));
            if (gridSurfHit[row][col] == "o"){
                gridSurfHit[row][col] = "B";
            }else if(gridSurfHit[row][col] == "x"){
                gridSurfHit[row][col] = "Z";
            }else {
                gridSurfHit[row][col] = "*";
            }
        }

        for (int i = 0; i < subHitLedger.size(); i++){
            String hitStr = subHitLedger.get(i);
            int row = Integer.parseInt(hitStr.substring(0, 1));
            int col = Integer.parseInt(hitStr.substring(2, 3));
            if (gridSurfHit[row][col] == "o"){
                gridSurfHit[row][col] = "E";
            }else if(gridSurfHit[row][col] == "x"){
                gridSurfHit[row][col] = "Q";
            }else {
                gridSurfHit[row][col] = "s";
            }
        }




    }
}