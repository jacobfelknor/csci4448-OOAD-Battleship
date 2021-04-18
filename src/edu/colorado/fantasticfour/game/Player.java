package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.command.Command;
import edu.colorado.fantasticfour.command.MoveFleetCommand;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.*;
import edu.colorado.fantasticfour.weapons.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Player {

    private Board board;
    private Player opponent;
    private List<Ship> ships;
    public TerminalGrid tGrid;

    private Sonar sonar;
    private Weapon attackWeapon;
    private MineField minefield;
    private Stack<Command> undoCommandStack;
    private Stack<Command> redoCommandStack;

    public Player() {
        this.ships = List.of(
                new Minesweeper(),
                new Destroyer(),
                new Battleship(),
                new Submarine()
        );
        this.board = new Board(this);
        this.attackWeapon = new Bomb(this); // the Bomb is the default Weapon
        this.sonar = new Sonar(this);
        this.minefield = new MineField(this);
        this.undoCommandStack = new Stack<>();
        this.redoCommandStack = new Stack<>();
        this.tGrid = new TerminalGrid(this);
    }

    public Weapon getAttackWeapon() {
        return attackWeapon;
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

    public boolean hasSunkOpponentShip(){
        return this.getOpponent().getAfloatShips().size() < this.getOpponent().getPlacedShips().size();
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
        //throw new IllegalArgumentException("Ship not found");
        //System.out.println("Ship not found");
        return null;
    }

    public void setOpponent(Player opp){
        this.opponent = opp;
    }

    private void setAttackWeapon(){
        if(this.hasSunkOpponentShip() && this.attackWeapon instanceof Bomb){
            this.attackWeapon = new Laser(this);
        }
        // else, Bomb is default and should be left as is, OR we already have the Laser
    }

    public String takeShot(Location location) throws IllegalArgumentException{
        this.setAttackWeapon();
        return this.attackWeapon.useAt(location);
    }

    public List<String> takeDoubleShot(Location location1, Location location2) throws IllegalArgumentException{
        if (this.getAfloatShips().size() > 1) {
            throw new IllegalArgumentException("Double shot can only be used with one ship remaining");
        }else{
            List<String> doubleShotResults = new ArrayList<>();
            doubleShotResults.add(this.attackWeapon.useAt(location1));
            doubleShotResults.add(this.attackWeapon.useAt(location2));
            return doubleShotResults;
        }
    }

    public Sonar getSonar(){
        return this.sonar;
    }

    public void placeShip(String name, Location captainsQ, String orientation) throws IllegalArgumentException{
        Ship ship = this.getShipByName(name);
        List<Location> locations = ship.getDimensions(captainsQ, orientation);
        for(Location location : locations){
            if(!this.getMyBoard().isOnBoard(location)){
                throw new IllegalArgumentException("One or more locations do not exist on this board " + locations);
            }
        }
        ship.setCaptainsQuarters(captainsQ);
        List<Cell> shipCells = this.getMyBoard().getCellsAtLocations(locations);
        ship.getGps().setCoordinates(shipCells);
        // if opponent has a mine here, the moved ship should receive damage (Bomb like)
        List<Location> minesAt = locations.stream()
                .filter(x -> this.getOpponent().minefield.hasMineAt(x)).collect(Collectors.toList());
        if(!minesAt.isEmpty()){
            Bomb tempBomb = new Bomb(this.getOpponent());
            for(Location location : minesAt){
                // ALERT USER THEY PLACED SHIP ON BOMB?
                tempBomb.useAt(location);
            }
        }

    }

    public void placeMine(Location location){
        this.minefield.placeMineAt(location);
    }

    public void moveFleet(String direction){
        Command command = new MoveFleetCommand(this, direction);
        this.undoCommandStack.push(command);
        command.execute();
        // clear the redo command because we pushed a new command
        this.redoCommandStack.removeAllElements();
    }

    public void undoMoveFleet(){
        Command commandToUndo = this.undoCommandStack.pop();
        commandToUndo.undo();
        // I've undone a command, so add it to redo stack
        this.redoCommandStack.push(commandToUndo);
    }

    public void redoMoveFleet(){
        if(!this.redoCommandStack.isEmpty()){
            Command commandToRedo = this.redoCommandStack.pop();
            commandToRedo.execute();
            // I've redone a command, so add it to undo stack
            this.undoCommandStack.push(commandToRedo);
        }
    }

}
