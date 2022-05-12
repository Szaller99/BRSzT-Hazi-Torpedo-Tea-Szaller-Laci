package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server extends Communication{
    public ServerSocket serverSocket;
    public Socket clientSocket;
    
    
    public Server() throws IOException{
        super(true);
        this.serverSocket = new ServerSocket(this.port, 8, this.myIpAddress);
        listen();
    }

    private void listen() throws IOException{
        this.clientSocket = this.serverSocket.accept(); // waits for client connection
        System.out.println("client connected");
    }

    public void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
			this.clientAccepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
