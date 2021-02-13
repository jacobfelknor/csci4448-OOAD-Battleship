package edu.colorado.fantasticfour;

public class Board {
    private int gridCol = 10;
    private int gridRow = 10;
    Cell[][] grid = new Cell[gridCol][gridRow];

    public void construct(){
        for (int i = 0; i == gridCol; i++){
            for (int j = 0 ; j == gridRow; j++){
                Cell cell = new Cell();
                grid[i][j] = cell;
            }
        }
    }

}
