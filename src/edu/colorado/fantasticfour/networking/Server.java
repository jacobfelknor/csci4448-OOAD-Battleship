package edu.colorado.fantasticfour.networking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TransferQueue;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
    }

    public void startConnectionThread() {
        Runnable serverTask = () -> {
            try {
                System.out.println("Waiting for a client...");
                socket = server.accept();
                System.out.println("Connection successful");
            } catch (IOException e){
                System.err.println("Unable to connect to Client");
                e.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public String readLineFromSocket() throws IOException {
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return in.readUTF();
    }

    public void destroy() throws IOException {
        socket.close();
        if(in != null){
            in.close();
        }
        server.close();
    }

}
