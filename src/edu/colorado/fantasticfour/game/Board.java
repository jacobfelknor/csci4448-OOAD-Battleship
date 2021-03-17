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

    public boolean isOnSurface(Location location){
        return location.getZ() == 0;
    }

    public boolean isSubSurface(Location location){
        return location.getZ() == 1;
    }

    public Cell getCellAt(Location location){
        return this.grid.get(location);
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

}
