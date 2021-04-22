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

    @Override
    protected String gameTakeShot(Player player, Location location) {
        return player.takeShot(location);
    }

    public void PrintMenu(){
        System.out.println("1. Take Shot");
        System.out.println("2. Use Sonar");
        System.out.println("3. Move Fleet");
        System.out.println("4. Undo Move");
    }

    public void loopGame(){
        int menuInt = 0;

        // loop until surrender
        // flag for toggle turn
        boolean flag = true;
        while(!(player1.mustSurrender() || player2.mustSurrender())){

            if(whoseTurn().equals(player1)){
                // player one takes a shot
                while (flag) {
                    // grid logic/print
                    player1.tGrid.FillGrid();
                    player1.tGrid.PrintGrid();
                    this.PrintMenu();
                    System.out.println("Player 1: ");
                    String menuStr = scanner.nextLine();
                    menuInt = Integer.parseInt(menuStr);

                    // menu logic
                    if (menuInt == 4){
                        try{
                            menuFromScanner("1", menuInt);
                            flag = false;
                        }catch(IllegalArgumentException e){
                            flag = true;
                        }
                    }

                    if (menuInt == 2 && !player1.getSonar().canUseSonar()){
                        menuInt = -1;
                        System.out.println("Sonar is offline");
                        System.out.println("Please choose again");
                        System.out.println();
                    }

                    if (menuInt < 4 && menuInt > 0){
                        menuFromScanner("1", menuInt);
                        flag = false;
                    }
                }

            }else{
                // player two takes a shot
                while (flag) {
                    player2.tGrid.FillGrid();
                    player2.tGrid.PrintGrid();
                    this.PrintMenu();
                    System.out.println("Player 2: ");
                    String menuStr = scanner.nextLine();
                    menuInt = Integer.parseInt(menuStr);

                    if (menuInt == 4){
                        try{
                            menuFromScanner("1", menuInt);
                            flag = false;
                        }catch(IllegalArgumentException e){
                            flag = true;
                        }
                    }

                    if (menuInt == 2 && !player2.getSonar().canUseSonar()){
                        menuInt = -1;
                        System.out.println("Sonar is offline");
                        System.out.println("Please choose again");
                        System.out.println();
                    }

                    if (menuInt < 4 && menuInt > 0){
                        menuFromScanner("2", menuInt);
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

        // prompt players for mine locations
        //collectMineLocationsFromPlayer("1");
        //collectMineLocationsFromPlayer("2");

        // prompt players for ship locations
        collectShipLocationsFromPlayer("1");
        collectShipLocationsFromPlayer("2");

        // start game
        loopGame();
    }
}
