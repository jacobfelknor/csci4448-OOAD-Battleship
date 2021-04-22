package edu.colorado.fantasticfour.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream out;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.out = new DataOutputStream(this.socket.getOutputStream());
        System.out.printf("Connected to server at %s:%d%n", address, port);
    }

    public void writeToSocket(String msg) throws IOException {
        out.writeUTF(msg);
    }

    public void destroy() throws IOException {
        socket.close();
        out.close();
    }

}
