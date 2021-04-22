package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
            } catch (IllegalArgumentException | IllegalStateException e){
                // something was invalid, try again
                System.out.println("Try again, " + e.getMessage());
            }
        }
    }

    private void placeMineFromScanner(Player player){
        while(true){
            try{
                System.out.print("Mine Location: ");
                String location = scanner.nextLine();
                Location minePlacement = Location.parseLocationString(location);
                player.placeMine(minePlacement);
                break;
            } catch (IllegalArgumentException | IllegalStateException e){
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
            player.tGrid.FillGrid();
            player.tGrid.PrintGrid();
            placeShipFromScanner(ship.getName(), player);
        }
    }

    public void collectMineLocationsFromPlayer(String playerStr){
        Player player = this.getPlayer(playerStr);
        System.out.println("Player " + playerStr + ", Please" +
                " enter the locations of your mines.");
        for(int i = 0; i < 3; i++){
            placeMineFromScanner(player);
        }

    }

    public void sonarFromScanner(String playerStr, boolean[] sonarResults, String target){
        Player player = getPlayer(playerStr);
        int targetX = Integer.parseInt(target.substring(0, 1));
        int targetY = Integer.parseInt(target.substring(2, 3));
        int row = targetX -2;
        int col = targetY;

        if (sonarResults[0]){
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX -2;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[1]){
            row = targetX -1;
            col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX -1;
            col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[2]){
            row = targetX -1;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{//#
            row = targetX -1;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[3]){
            row = targetX -1;
            col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX -1;
            col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[4]){
            row = targetX;
            col = targetY -2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX;
            col = targetY -2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[5]){
            row = targetX;
            col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX;
            col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[6]){
            row = targetX;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[7]){
            row = targetX;
            col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX;
            col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[8]){
            row = targetX;
            col = targetY +2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX;
            col = targetY +2;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[9]){
            row = targetX +1;
            col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX +1;
            col = targetY -1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[10]){
            row = targetX +1;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX +1;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[11]){
            row = targetX +1;
            col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX +1;
            col = targetY +1;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
        if (sonarResults[12]){
            row = targetX +2;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarHitLedger.add(locationStr);
        }else{
            row = targetX +2;
            col = targetY;
            String locationStr = String.valueOf(row) + " " + String.valueOf(col);
            player.tGrid.sonarMissLedger.add(locationStr);
        }
    }

    public void takeShotFromScanner(String playerStr){
        Player player = getPlayer(playerStr);
        List<String> results = new ArrayList<>();
        while (true){
            try{
            System.out.print("Player " + playerStr + ", take a shot: ");
            String locationStr = scanner.nextLine();
            Location location = Location.parseLocationString(locationStr);

            if (player.getAfloatShips().size() < 2){
                System.out.print("Player " + playerStr + ", take a shot: ");
                String locationStr2 = scanner.nextLine();
                Location location2 = Location.parseLocationString(locationStr2);
                results = player.takeDoubleShot(location, location2);
            }else{
                results.add(player.takeShot(location));
            }
            int zL = location.getZ();

            for (int i = 0; i < results.size(); i++){
                if (results.get(i) == "HIT"){
                    Ship newShip = player.getOpponent().getShipAt(location);
                    if (newShip.getName() == "Submarine"){
                        player.tGrid.subHitLedger.add(locationStr);
                        player.tGrid.hitLedger.add(locationStr);
                    }else {
                        player.tGrid.hitLedger.add(locationStr);
                    }
                }else if(results.get(i) == "SUNK"){
                    // get all cells from ship and add to hitLedger

                    Ship newShip = player.getOpponent().getShipAt(location);
                    String orientation = newShip.getOrientation();
                    List<Location> sunkLocations = newShip.getDimensions(location, orientation);

                    for (int j = 0; j < sunkLocations.size(); j++) {
                        Location sunkCell = sunkLocations.get(j);
                        int x = sunkCell.getX();
                        int y = sunkCell.getY();
                        int z = sunkCell.getZ();

                        String locationStr2 = Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(z);

                        if (z < 0){
                            player.tGrid.subHitLedger.add(locationStr2);
                        }else {
                            player.tGrid.hitLedger.add(locationStr2);
                        }
                    }
                    player.tGrid.hitLedger.add(locationStr);
                }else{

                    if (zL < 0){
                        player.tGrid.subMissLedger.add(locationStr);
                    }else {
                        player.tGrid.missLedger.add(locationStr);
                    }
                }
                System.out.println("Result: " + results.get(i) + "\n");
            }
            break;
            }catch (Exception e){
                System.out.println("Try Again, " + e.getMessage());
            }
        }
    }

    public void menuFromScanner(String playerStr, int menuOption){
        Player player = getPlayer(playerStr);
        switch (menuOption) {
            case 1:
                takeShotFromScanner(playerStr);
                break;
            case 2:
                System.out.print("Choose an X, Y coordinate: ");
                String locationStr = scanner.nextLine();
                Location location = Location.parseLocationString(locationStr);
                if (player.getSonar().canUseSonar()) {
                    player.getSonar().useAt(location);
                    boolean sonarList[] = player.getSonar().getSonarResults();
                    sonarFromScanner(playerStr, sonarList, locationStr);
                }
                break;
            case 3:
                System.out.print("Choose a direction(N, S, E, W): ");
                String moveStr = scanner.nextLine();
                player.moveFleet(moveStr);
                break;
            case 4:
                try {
                    player.undoMoveFleet();
                }
                catch(EmptyStackException e){
                    System.out.println("Nothing to undo");
                }
                break;
            default:
                System.out.println("Pick Valid Option Please");
                break;
        }

    }

    protected abstract String gameTakeShot(Player player, Location location);

    public abstract void start() throws IOException;
    public abstract void loopGame();
}
