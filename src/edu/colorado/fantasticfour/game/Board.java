package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int gridCol = 10;
    private int gridRow = 10;
    private int gridLayers = 2;
    private Map<Location, Cell> grid = new HashMap<>();
    private Player player;

    public Board(Player player){
        for (int i = 0; i < gridCol; i++){
            for (int j = 0; j < gridRow; j++){
                for(int k = 0; k < gridLayers; k++){
                    grid.put(new Location(i, j, -1*k), new Cell(new Location(i,j, -1*k)));
                }
            }

        }
        assert grid.size() == gridCol*gridRow*gridLayers;
        this.player = player;
    }
    //i,j,k:false
    //i,j,k:true
    public boolean isOnBoard(Location location){
        return this.grid.containsKey(location);
    }

    public boolean isBelowSurface(Location location){
        return location.getZ() < 0;
    }

    public Cell getCellAt(Location location){
        if(isOnBoard(location)){
            return this.grid.get(location);
        }
        throw new IllegalArgumentException("Cell does not exist on this board");
    }

    public List<Cell> getCellsInColumn(Location location){
        List<Cell> cells = new ArrayList<>();
        for(int z = 0; z < gridLayers; z++){
            Location layer = new Location(location.getX(), location.getY(), -z);
            cells.add(this.getCellAt(layer));
        }
        return cells;
    }

    public List<Cell> getCellsAtLocations(List<Location> locations){
        List<Cell> cells = new ArrayList<>();
        for(Location location : locations){
            cells.add(this.getCellAt(location));
        }
        return cells;
    }

    public Player getPlayer() {
        return player;
    }

//    @Override
//    public String toString(){
//        // I want to create a print board method here. May be difficult...
//        // How to represent the z layers?
//        // currently aren't keeping track of where shots are attempted. Would need to do this
//        return super.toString();
//    }

}
