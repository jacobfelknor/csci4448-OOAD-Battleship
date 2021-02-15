package edu.colorado.fantasticfour;

public class Board {
    private int gridCol = 10;
    private int gridRow = 10;
    private String[][] grid = new String[gridCol][gridRow];
    private Player player;

    public Board(Player player){
        for (int i = 0; i < gridCol; i++){
            for (int j = 0 ; j < gridRow; j++){
                grid[i][j] = "";
            }
        }
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    private boolean shotAt(int x, int y){
        return !grid[x][y].equals("");
    }

    public String shootAt(int x, int y) throws IllegalArgumentException{
        if(shotAt(x,y)){
            throw new IllegalArgumentException("This coordinate has already been shot at");
        }
        if (player.hasShipAt(x, y)){
            grid[x][y] = "HIT";
            if (player.getShipAt(x, y).isSunk()){
                return "SUNK";
            }
            return "HIT";
        }else{
            grid[x][y] = "MISS";
            return "MISS";
        }
    }

}
