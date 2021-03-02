package edu.colorado.fantasticfour;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int gridCol = 10;
    private int gridRow = 10;
    private Cell[][] grid = new Cell[gridCol][gridRow];
    private Player player;

    public Board(Player player){
        for (int i = 0; i < gridCol; i++){
            for (int j = 0 ; j < gridRow; j++){
                grid[i][j] = new Cell(new Location(i, j));
            }
        }
        this.player = player;
    }

    public static boolean isOnBoard(int x, int y) throws IllegalArgumentException{
        // Currently, board is a 10x10
        if ((x <= 9 && x >= 0) &&  (y <= 9 && y >= 0)){
            return true;
        }else{
            throw new IllegalArgumentException("Cell needs to exist within 10x10 grid");
        }
    }

    public static boolean isOnBoard(Location location) throws IllegalArgumentException{
        int x = location.getX();
        int y = location.getY();
        return isOnBoard(x, y);
    }

    public static boolean inStraightLine(Location ... locations) throws IllegalArgumentException{
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for(Location location : locations){
//          ensure location is on the board
            isOnBoard(location);
            xs.add(location.getX());
            ys.add(location.getY());
        }
        long xDistinct = xs.stream().distinct().count();
        long yDistinct = ys.stream().distinct().count();
        return xDistinct == 1 ^ yDistinct == 1;
    }

    public Cell getCellAt(int x, int y){
        return this.grid[x][y];
    }

    public List<Cell> getCellsAtLocations(Location ... locations){
        List<Cell> cells = new ArrayList<>();
        for(Location location : locations){
            cells.add(this.getCellAt(location.getX(), location.getY()));
        }
        return cells;
    }

    public Player getPlayer() {
        return player;
    }

    public String shootAt(int x, int y) throws IllegalArgumentException{
        Cell targetCell = grid[x][y];
        Ship cellShip = targetCell.getShip();
        if(cellShip == null){
            // target cell does not have a ship --> MISS
            return "MISS";
        }else{
            if(cellShip.isCellAHit(targetCell)){
                if(cellShip.isSunk()){
                    return "SUNK";
                }else{
                    return "HIT";
                }
            }else{
                // coverage won't hit this till we implement Captain's quarters
                // needed to add or code wouldn't compile due to lack of return statement
                return "MISS";
            }
        }
    }

}
