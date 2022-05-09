package program.communication;

import java.io.IOException;
import java.net.*;

public class Server {
    public ServerSocket serverSocket;
    public Socket clientSocket;
    public Server() throws IOException{
        this.serverSocket = new ServerSocket(4999);
        listen();
    }

    private void listen() throws IOException{
        this.clientSocket = this.serverSocket.accept(); // waits for client connection
        System.out.println("client connected");
    }
    
}
