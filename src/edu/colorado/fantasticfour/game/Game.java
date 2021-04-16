package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public abstract class Game {
    protected Player player1;
    protected Player player2;
    protected Player playerTurn;
    protected final Scanner scanner;

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
        while(true){
            try{
                System.out.print(shipName + " Location: ");
                String location = scanner.nextLine();
                System.out.print(shipName + " Orientation: ");
                String orientation = scanner.nextLine();
                Location shipPlacement = Location.parseLocationString(location);
                player.placeShip(shipName, shipPlacement, orientation);
                break;
            } catch (IllegalArgumentException e){
                // something was invalid, try again
                System.out.println("Try again, " + e.getMessage());
            }
        }
    }

    public void collectShipLocationsFromPlayer(String playerStr){
        Player player = this.getPlayer(playerStr);
        System.out.println("Player " + playerStr + ", welcome to Battleship. Please" +
                " enter the locations of your ships.");
        for(Ship ship : player.getAllShips()){
            placeShipFromScanner(ship.getName(), player);
        }
    }

    public void takeShotFromScanner(String playerStr){
        Player player = getPlayer(playerStr);
        while (true){
            try{
                System.out.print("Player " + playerStr + ", take a shot: ");
                String locationStr = scanner.nextLine();
                Location location = Location.parseLocationString(locationStr);
                String result = gameTakeShot(player, location);
                System.out.println("Result: " + result + "\n");
                break;
            }catch (Exception e){
                System.out.println("Try Again, " + e.getMessage());
            }
        }
    }

    protected abstract String gameTakeShot(Player player, Location location);

    public abstract void start() throws IOException;
    public abstract void loopGame();
}
