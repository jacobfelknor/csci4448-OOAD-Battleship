package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;

import java.io.InputStream;

public class LocalGame extends Game{

    public LocalGame(){
        super();
    }

    public LocalGame(InputStream in) {
        super(in);
    }
    //private TerminalGrid tGrid;


    @Override
    protected String gameTakeShot(Player player, Location location) {
        return player.takeShot(location);
    }

    public void PrintMenu(){
        System.out.println("1. Take Shot");
        System.out.println("2. Check Current Weapon");
        System.out.println("3. Use Sonar");
        System.out.println("4. Move Fleet");
        System.out.println("5. Undo Move");
    }

    public void loopGame(){
        int menuInt = 0;

        boolean flag = true;
        while(!(player1.mustSurrender() || player2.mustSurrender())){

            if(whoseTurn().equals(player1)){
                // player one takes a shot
                while (flag) {
                    player1.tGrid.FillGrid(player1);
                    player1.tGrid.PrintGrid();
                    this.PrintMenu();
                    System.out.println("Player 1: ");
                    String menuStr = scanner.nextLine();
                    menuInt = Integer.parseInt(menuStr);
                    menuFromScanner("1", menuInt);
                    if (menuInt < 6 && menuInt > 0 && menuInt != 2){
                        flag = false;
                    }
                }

            }else{
                // player two takes a shot
                while (flag) {
                    player2.tGrid.FillGrid(player2);
                    player2.tGrid.PrintGrid();
                    this.PrintMenu();
                    System.out.println("Player 2: ");
                    String menuStr = scanner.nextLine();
                    menuInt = Integer.parseInt(menuStr);
                    menuFromScanner("2", menuInt);
                    if (menuInt < 6 && menuInt > 0 && menuInt != 2){
                        flag = false;
                    }
                }
            }
            flag = true;
            toggleTurn();
        }
        if(player1.mustSurrender()){
            System.out.println("Congratulations Player 2, you have won the game!");
        }else{
            System.out.println("Congratulations Player 1, you have won the game!");
        }
    }

    public void start(){
        // first order of business is to ask for Locations of player 1 Ships
        //collectShipLocationsFromPlayer("1");
        collectShipLocationsFromPlayer();
        // now, ask for player 2's
        //collectShipLocationsFromPlayer("2");
        collectShipLocationsFromPlayer();
        // start a simple game
        loopGame();
    }
}
