package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.command.MoveFleetCommand;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;
import edu.colorado.fantasticfour.weapons.Sonar;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

    // method for testing - quick place ship
    public void collectShipLocationsFromPlayer(){
        Player player1 = this.getPlayer("1");
        Player player2 = this.getPlayer("2");
        Location location1 = new Location(6, 4);
        Location location2 = new Location(2, 9);
        Location location3 = new Location(4, 8);
        Location location4 = new Location(5, 5, -1);
        player1.placeShip("Minesweeper",  location1, "E");
        player1.placeShip("Destroyer",  location2, "W");
        player1.placeShip("Battleship",  location3, "N");
        player1.placeShip("Submarine",  location4, "NE");
        player2.placeShip("Minesweeper",  location1, "E");
        player2.placeShip("Destroyer",  location2, "W");
        player2.placeShip("Battleship",  location3, "E");
        player2.placeShip("Submarine",  location4, "NE");

    }
    public void collectShipLocationsFromPlayer(String playerStr){
        Player player = this.getPlayer(playerStr);
        System.out.println("Player " + playerStr + ", welcome to Battleship. Please" +
                " enter the locations of your ships.");
        for(Ship ship : player.getAllShips()){
            placeShipFromScanner(ship.getName(), player);
        }

    }

    public void sonarFromScanner(String playerStr, boolean[] sonarResults, String target){
        Player player = getPlayer(playerStr);
        int targetX = Integer.parseInt(target.substring(0, 1));
        int targetY = Integer.parseInt(target.substring(2, 3));
        //Location sonarLocs[] = new Location[];
        //ArrayList<Location> sonarLocations = new ArrayList<Location>();
        //Player opponent = player.getOpponent();
        if (sonarResults[0]){
            int row = targetX -2;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX -2;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[1]){
            int row = targetX -1;
            int col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX -1;
            int col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[2]){
            int row = targetX -1;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{//#
            int row = targetX -1;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[3]){
            int row = targetX -1;
            int col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX -1;
            int col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[4]){
            int row = targetX;
            int col = targetY -2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX;
            int col = targetY -2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[5]){
            int row = targetX;
            int col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX;
            int col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[6]){
            int row = targetX;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[7]){
            int row = targetX;
            int col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX;
            int col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[8]){
            int row = targetX;
            int col = targetY +2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX;
            int col = targetY +2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[9]){
            int row = targetX +1;
            int col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX +1;
            int col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[10]){
            int row = targetX +1;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX +1;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[11]){
            int row = targetX +1;
            int col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX +1;
            int col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[12]){
            int row = targetX +2;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            int row = targetX +2;
            int col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        //player.tGrid.hitLedger.add(locationStr)

    }

    public void takeShotFromScanner(String playerStr){
        Player player = getPlayer(playerStr);
        while (true){
//            try{
            System.out.print("Player " + playerStr + ", take a shot: ");
            String locationStr = scanner.nextLine();
            Location location = Location.parseLocationString(locationStr);
            String result = gameTakeShot(player, location);
//            if (player.getAfloatShips().size() < 2){
//                String results = gameTakeDoubleShot(location);
//            }
            int zL = location.getZ();

            if (result == "HIT"){
                Ship newShip = player.getOpponent().getShipAt(location);
                if (newShip.getName() == "Submarine"){
                    player.tGrid.subHitLedger.add(locationStr);
                    player.tGrid.hitLedger.add(locationStr);
                }else {
                    player.tGrid.hitLedger.add(locationStr);
                }
                //player.tGrid.hitLedger.add(locationStr);
            }else if(result == "SUNK"){
                //get all cells from ship and add to hitLedger
                // newList = sunk ship cells

                Ship newShip = player.getOpponent().getShipAt(location);
                String orientation = newShip.getOrientation();
                List<Location> sunkLocations = newShip.getDimensions(location, orientation);

                for (int i = 0; i < sunkLocations.size(); i++) {
                    Location sunkCell = sunkLocations.get(i);
                    int x = sunkCell.getX();
                    int y = sunkCell.getY();
                    int z = sunkCell.getZ();

                    String locationStr2 = Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(z);

                    //if (newShip.getName() == "Submarine"){
                    if (z < 0){
                        player.tGrid.subHitLedger.add(locationStr2);
                    }else {
                        player.tGrid.hitLedger.add(locationStr2);
                    }
                }
                player.tGrid.hitLedger.add(locationStr);
            }else{
                //Ship newShip = player.getOpponent().getShipAt(location);
                if (zL < 0){
                    player.tGrid.subMissLedger.add(locationStr);
                }else {
                    player.tGrid.missLedger.add(locationStr);
                }
            }
            System.out.println("Result: " + result + "\n");
            break;
//            }catch (Exception e){
//                System.out.println("Try Again, " + e.getMessage());
//            }
        }
    }

    public void menuFromScanner(String playerStr, int menuOption){
        Player player = getPlayer(playerStr);
        switch (menuOption) {
            case 1:
                takeShotFromScanner(playerStr);
                //System.out.println("test1");
                break;
            case 2:
                System.out.print("Choose an X, Y coordinate: ");
                String locationStr = scanner.nextLine();
                Location location = Location.parseLocationString(locationStr);
                //String result = gameTakeShot(player, location);
                if (player.getSonar().canUseSonar()) {
                    player.getSonar().useAt(location);
                    boolean sonarList[] = player.getSonar().getSonarResults();
                    sonarFromScanner(playerStr, sonarList, locationStr);
                }
                break;
            case 3:
                System.out.println("Available next version");
//                System.out.print("Choose a direction(N, S, E, W): ");
//                String moveStr = scanner.nextLine();
//                player.moveFleet(moveStr);
                break;
            case 4:
                System.out.println("Available next version");
                //player.undoMoveFleet();
                break;
            default:
                System.out.println("Pick Valid Option Please");
                break;
        }

    }

    protected abstract String gameTakeShot(Player player, Location location);
    //protected abstract List<String> gameTakeDoubleShot(Location location, Location location2);

    public abstract void start() throws IOException;
    public abstract void loopGame();
}
