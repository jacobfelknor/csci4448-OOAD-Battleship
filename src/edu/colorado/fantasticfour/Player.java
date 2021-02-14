package edu.colorado.fantasticfour;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private Board theirBoard;
    private Player opponent;
    private List<Ship> ships;

    public Board getTheirBoard() {
        return theirBoard;
    }

    public List<Ship> getAllShips(){
        return ships;
    }

    public List<Ship> getAfloatShips(){
        return ships.stream().filter(ship -> !ship.isSunk() && ship.getCoordinates() != null).collect(Collectors.toList());
    }

    public List<Ship> getPlacedShips(){
        return ships.stream().filter(ship -> ship.getCoordinates() != null).collect(Collectors.toList());
    }

    public Ship getShipByName(String name){
        for(Ship ship : this.ships){
            if(ship.getName().equals(name)){
                return ship;
            }
        }
        return null;
    }

    public Ship getShipAt(int x, int y){
        for(Ship ship : getPlacedShips()){
            int[] coordinates = ship.getCoordinates();
            int x0 = coordinates[0];
            int y0 = coordinates[1];
            int x1 = coordinates[2];
            int y1 = coordinates[3];
            if((x >= x0 && x <= x1) || (x >= x1 && x <= x0)){
                if((y >= y0 && y <= y1) || (y >= y1 && y <= y0)){
                    return ship;
                }
            }
        }
        return null;
    }

    public Player() {
        // player constructor creates its boards
        this.ships = List.of(
                new Ship("Minesweeper", 2),
                new Ship("Destroyer",3),
                new Ship("Battleship",4)
        );
    }

    public void setOpponent(Player opp){
        this.opponent = opp;
        this.theirBoard = new Board(opponent);
    }

    public String takeShot(int x, int y) throws IllegalArgumentException{
        return theirBoard.shootAt(x, y);
    }

    public boolean hasShipAt(int x, int y){
        for(Ship ship : getPlacedShips()){
            int[] coordinates = ship.getCoordinates();
            int x0 = coordinates[0];
            int y0 = coordinates[1];
            int x1 = coordinates[2];
            int y1 = coordinates[3];
            if((x >= x0 && x <= x1) || (x >= x1 && x <= x0)){
                if((y >= y0 && y <= y1) || (y >= y1 && y <= y0)){
                    ship.gotHit();
                    return true;
                }
            }
        }
        return false;
    }

}
