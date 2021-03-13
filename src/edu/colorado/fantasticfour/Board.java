package edu.colorado.fantasticfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int gridCol = 10;
    private int gridRow = 10;
    private Map<Location, Cell> grid = new HashMap<>();
    private Player player;

    public Board(Player player){
        for (int i = 0; i < gridCol; i++){
            for (int j = 0 ; j < gridRow; j++){
                    grid.put(new Location(i, j), new Cell(new Location(i,j)));
            }

        }
        this.player = player;
    }

    public boolean isOnBoard(Location location){
        return this.grid.containsKey(location);
    }

    public Cell getCellAt(Location location){
        return this.grid.get(location);
    }

    public List<Cell> getCellsAtLocations(Location ... locations){
        List<Cell> cells = new ArrayList<>();
        for(Location location : locations){
            cells.add(this.getCellAt(location));
        }
        return cells;
    }

    public Player getPlayer() {
        return player;
    }

    public String shootAt(Location location) throws IllegalArgumentException{
        Cell targetCell = this.getCellAt(location);
        return targetCell.notifyObservers();

//        if(cellShip == null){
//            // target cell does not have a ship --> MISS
//            return "MISS";
//        }else{
//            if(cellShip.isHit(targetCell)){
//                if(cellShip.isSunk()){
//                    return "SUNK";
//                }else{
//                    return "HIT";
//                }
//            }else{
//                return "MISS";
//            }
//        }
    }

}
