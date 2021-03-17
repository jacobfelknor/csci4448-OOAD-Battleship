package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.*;
import edu.colorado.fantasticfour.weapons.Bomb;
import edu.colorado.fantasticfour.weapons.Laser;
import edu.colorado.fantasticfour.weapons.Sonar;
import edu.colorado.fantasticfour.weapons.Weapon;

import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private Board board;
    private Player opponent;
    private List<Ship> ships;

    private Weapon sonar;
    private Weapon attackWeapon;
    private Weapon laser;

    public Player() {
        this.ships = List.of(
                new Minesweeper(),
                new Destroyer(),
                new Battleship(),
                new Submarine()
        );
        this.board = new Board(this);

        this.attackWeapon = new Bomb(this); // the Bomb is the default Weapon
        //this.laser = new Laser(this);
        this.sonar = new Sonar(this);
    }


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
        return ships.stream().filter(ship -> !ship.isSunk() && ship.getGps().getCoordinates() != null).collect(Collectors.toList());
    }

    public boolean mustSurrender(){
        return getAfloatShips().size() == 0;
    }

    public List<Ship> getPlacedShips(){
        return ships.stream().filter(ship -> ship.getGps().getCoordinates() != null).collect(Collectors.toList());
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
           if(ship.getGps().getCoordinates().contains(new Cell(location))){
               return ship;
           }
        }
        throw new IllegalArgumentException("Ship not found");
    }

    public void setOpponent(Player opp){
        this.opponent = opp;
    }

    public String takeShot(Location location) throws IllegalArgumentException{
        return this.attackWeapon.useAt(location);
    }

    public void placeShip(String name, Location captainsQ, String orientation) throws IllegalArgumentException{
        Ship ship = this.getShipByName(name);
        ship.setCaptainsQuarters(captainsQ);
        List<Location> locations = ship.getDimensions(captainsQ, orientation);
        for(Location location : locations){
            if(!this.getMyBoard().isOnBoard(location)){
                throw new IllegalArgumentException("One or more locations do not exist on this board " + locations);
            }
        }
        List<Cell> shipCells = this.getMyBoard().getCellsAtLocations(locations);
        ship.getGps().setCoordinates(shipCells);
    }


}
