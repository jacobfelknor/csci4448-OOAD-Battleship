package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;
import edu.colorado.fantasticfour.ship.ShipOrientationDescriptor;

import java.io.InputStream;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player playerTurn;
    private final Scanner scanner;

    public Player getPlayer(String player){
        if(player.equals("1")){
            return this.player1;
        }else if (player.equals("2")){
            return this.player2;
        }
        throw new IllegalArgumentException("Game only has player '1' and '2'");
    }

    public Game(InputStream instream){
        setUpPlayers();
        // initialize scanner
        this.scanner = new Scanner(instream);
    }

    public Game(){
        setUpPlayers();
        // no scanner given, assume System.in
        this.scanner = new Scanner(System.in);
    }

    private void setUpPlayers(){
        player1 = new Player();
        player2 = new Player();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        // player 1 gets to go first
        playerTurn = player1;
    }

    public Player whoseTurn(){
        return playerTurn;
    }

    public void toggleTurn(){
        playerTurn = playerTurn.equals(player2) ? player1 : player2;
    }

    private void placeShipFromScanner(String shipName, Player player){
        String location = scanner.nextLine();
        String orientation = scanner.nextLine();
        System.out.println(location);
        Location shipPlacement = Location.parseLocationString(location);
        player.placeShip(shipName, shipPlacement, orientation);
    }

    public void collectShipLocationsFromPlayer(String playerStr){
        Player player = this.getPlayer(playerStr);
        System.out.println("Player " + playerStr + ", welcome to Battleship. Please" +
                " enter the locations of your ships.");
        for(Ship ship : player.getAllShips()){
            System.out.print(ship.getName() + " Location: ");
            System.out.print(ship.getName() + " Orientation: ");
            placeShipFromScanner(ship.getName(), player);
        }
    }

    public void start(){
        // first order of business is to ask for Locations of player 1 Ships
        collectShipLocationsFromPlayer("1");
        // now, ask for player 2's
        collectShipLocationsFromPlayer("2");
    }
}
