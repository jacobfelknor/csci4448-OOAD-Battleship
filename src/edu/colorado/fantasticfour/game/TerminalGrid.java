package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;

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
    private Player player;

    public TerminalGrid(Player player){
        for (int i = 0; i < gridCol; i++){
            for (int j = 0; j < gridRow; j++){
                this.gridSurf[i][j] = ".";
                this.gridSurfHit[i][j] = ".";
            }

        }
        this.player = player;
    }

    public void PrintGrid(){

        int[] tmp = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
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
    }

    public void FillGrid(Player player){

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                Location location = new Location(i, j);
                if (player.getShipAt(location) == null){
                    gridSurf[i][j] = ".";
                }
                else{
                    gridSurf[i][j] = "o";
                }
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

        for (int i = 0; i < hitLedger.size(); i++){
            String hitStr = hitLedger.get(i);
            int row = Integer.parseInt(hitStr.substring(0, 1));
            int col = Integer.parseInt(hitStr.substring(2, 3));
            gridSurfHit[row][col] = "o";
        }

        for (int i = 0; i < missLedger.size(); i++){
            String missStr = missLedger.get(i);
            int row = Integer.parseInt(missStr.substring(0, 1));
            int col = Integer.parseInt(missStr.substring(2, 3));
            gridSurfHit[row][col] = "x";
        }


    }
}