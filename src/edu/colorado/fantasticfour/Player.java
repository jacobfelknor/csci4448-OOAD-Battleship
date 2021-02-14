package edu.colorado.fantasticfour;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private Board myBoard;
    private Board theirBoard;
    private List<Ship> ships;

    public Board getMyBoard(){
        return myBoard;
    }

    public Board getTheirBoard() {
        return theirBoard;
    }

    public List<Ship> getAfloatShips(){
        return ships.stream().filter(ship -> !ship.isSunk()).collect(Collectors.toList());
    }

    public  Player() {
        // player constructor creates its boards
        this.myBoard = new Board();
        this.theirBoard = new Board();
        this.ships = List.of(new Ship(2), new Ship(3), new Ship(4), new Ship(4), new Ship(5));
    }
}
