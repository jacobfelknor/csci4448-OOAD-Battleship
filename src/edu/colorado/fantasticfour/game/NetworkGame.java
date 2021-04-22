package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.networking.Client;
import edu.colorado.fantasticfour.networking.Server;
import java.io.IOException;

public class NetworkGame extends Game{
    private Server server;
    private Client client;
    private final int CREATOR_SERVER_PORT = 5001;
    private final int JOINER_SERVER_PORT = 5000;

    private Player thisPlayer;

    public NetworkGame(){
        super();
    }

    private void startServer() throws IOException, InterruptedException {
        // each player needs a client to send and a server to receive
        // the game creator is responsible for creating the servers
        server = new Server(CREATOR_SERVER_PORT);
        server.startConnection();
        // wait a second for the joiner to create their server
        Thread.sleep(1000);
        client = new Client("127.0.0.1", JOINER_SERVER_PORT);
    }

    private void joinServer() throws IOException {
        // join the server the creator should have created for us
        client = new Client("127.0.0.1", CREATOR_SERVER_PORT);
        // now, the server should be looking for our server. Create it now
        server = new Server(JOINER_SERVER_PORT);
        server.startConnection();
    }

    @Override
    protected String gameTakeShot(Player player, Location location) {
        try{
            client.writeToSocket(location.toSimpleString());
            return server.readLineFromSocket();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "ERROR";
    }

    private void sendToServer(String msg) {
        try{
            client.writeToSocket(msg);
        }catch (IOException e){
            //
        }
    }

    private String readFromServer(){
        try {
            return server.readLineFromSocket();
        } catch (IOException e) {
            return "ERROR";
        }
    }

    private String getResultThisPlayerTurn(){
        String result;
        do {
            System.out.print("Type a target location: ");
            String line = scanner.nextLine();
            sendToServer(line);
            System.out.print("Result: ");
            result = readFromServer();
            System.out.println(result);
        } while (result.startsWith("ERROR: "));
        return result;
    }

    private void listenForOtherPlayer(){
        System.out.println("Waiting for other player...");
        boolean shotIsValid = false;
        while(!shotIsValid){
            String shotLocStr = null;
            try {
                shotLocStr = server.readLineFromSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Location target = null;
            try{
                assert shotLocStr != null;
                target = Location.parseLocationString(shotLocStr);
            }catch (IllegalArgumentException e){
                sendToServer("ERROR: " + e.getMessage());
            }
            if(target != null){
                System.out.println("Opponent taking shot at " + shotLocStr);
                String result;
                try{
                    result = thisPlayer.getOpponent().takeShot(target);
                    shotIsValid = true;
                }catch (Exception e){
                    result = "ERROR: " + e.getMessage();
                }

                if(thisPlayer.mustSurrender()){
                    sendToServer("SURRENDER");
                    System.out.println("Oh no! Your opponent has won. Better luck next time!");
                }else{
                    sendToServer(result);
                }
            }
        }
    }

    public void loopGame(){
        while(!thisPlayer.mustSurrender()){
            if(whoseTurn().equals(thisPlayer)){
                // we are going to send our shot over the network
                String result = getResultThisPlayerTurn();
                if(result.equals("SURRENDER")){
                    System.out.println("Congratulations, you have won the game!");
                    break;
                }
            }else{
                // we are going to listen for their shot
                listenForOtherPlayer();
            }
            toggleTurn();
        }
        try {
            client.destroy();
            server.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        while(true){
            System.out.println("Welcome to Network Battleship!\nDo you want to (j)oin or (c)reate a game?");
            String choice = scanner.nextLine();
            if(choice.equals("j")){
                try{
                    // join a game
                    joinServer();
                    // will only use player one in this context
                    collectShipLocationsFromPlayer("2");
                    thisPlayer = player2;
                    break;
                }catch (IOException e){
                    System.out.println("Error, could not find a host so cannot join.");
                }
            }else if(choice.equals("c")){
                try{
                    // create a game
                    startServer();
                    collectShipLocationsFromPlayer("1");
                    thisPlayer = player1;
                    break;
                }catch (IOException | InterruptedException e){
                    System.out.println("Something went wrong... Try again? Port may be in use...");
                }
            }else{
                System.out.println("Invalid option. Try again...");
            }
        }

        loopGame();
    }
}
