package edu.colorado.fantasticfour;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    public Sonar sonar;
    private Board board;
    private Player opponent;
    private List<Ship> ships;

    public Board getTheirBoard() {
        return opponent.board;
    }
    public Board getMyBoard(){
        return this.board;
    }

    public List<Ship> getAllShips(){
        return ships;
    }

    public Player getOpponent(){
        return this.opponent;
    }

    public List<Ship> getAfloatShips(){
        return ships.stream().filter(ship -> !ship.isSunk() && ship.gps.getCoordinates() != null).collect(Collectors.toList());
    }

    public boolean mustSurrender(){
        return getAfloatShips().size() == 0;
    }

    public List<Ship> getPlacedShips(){
        return ships.stream().filter(ship -> ship.gps.getCoordinates() != null).collect(Collectors.toList());
    }

    public Ship getShipByName(String name) throws IllegalArgumentException{
        for(Ship ship : this.ships){
            if(ship.getName().equals(name)){
                return ship;
            }
        }
        throw new IllegalArgumentException("Ship not found");
    }

    public Ship getShipAt(Location location) throws IllegalArgumentException{
        for(Ship ship : getPlacedShips()){
           if(ship.gps.getCoordinates().contains(new Cell(location))){
               return ship;
           }
        }
        throw new IllegalArgumentException("Ship not found");
    }

    public Player() {
        this.ships = List.of(
                new Minesweeper(),
                new Destroyer(),
                new Battleship()
        );
        this.board = new Board(this);
        this.sonar = new Sonar(this);
    }

    public void setOpponent(Player opp){
        this.opponent = opp;
    }

    public String takeShot(Location location) throws IllegalArgumentException{
        return getTheirBoard().shootAt(location);
    }

    public void placeShip(String name, Location @NotNull ... locations) throws IllegalArgumentException{
        Ship ship = this.getShipByName(name);
        if(locations.length != ship.getLength()){
            throw new IllegalArgumentException("Number of cells must match ship length");
        }
        // calculate Ship locations here, using a ShipDimension class
        for(Location location : locations){
            if(!this.getMyBoard().isOnBoard(location)){
                throw new IllegalArgumentException("One or more locations do not exist on this board");
            }
        }
        if(Location.inStraightLine(locations)){
            List<Cell> shipCells = this.getMyBoard().getCellsAtLocations(locations);
            ship.gps.setCoordinates(shipCells);
        }else{
            throw new IllegalArgumentException("Cells are not on a straight line");
        }
    }

}
