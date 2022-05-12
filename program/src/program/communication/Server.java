package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server extends Communication{
    public ServerSocket serverSocket;
    public Socket clientSocket;
    private boolean keepServerThreadAlive = true;
    private boolean waitForClientReady = false;
    private boolean waitForClientShips = false;

    
    public Server() throws IOException{
        super(true);
        System.out.print("Creating server socket \n");
        this.serverSocket = new ServerSocket(this.port, 8, this.myIpAddress);
        System.out.print("Server socket created \n");
        System.out.print("Server socket waiting for client.. \n");
        listen(); // beware, this blocks the code
        System.out.print("Client connected to server! \n");
    }

    private void listen() throws IOException{
        this.clientSocket = this.serverSocket.accept(); // waits for client connection; beware, this blocks the code
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

    @Override
    public void run(){
        while(this.keepServerThreadAlive) { // waiting for tasks
            if(this.waitForClientReady) {
                // TODO implement

                this.setwaitForClientReady(false);
            }
            if(this.waitForClientShips) {
                // TODO implement
                this.setwaitForClientShips(false);
            }
            if(this.waitForShot) {
                // TODO implement
                this.setwaitForShot(false);
            }
        }
        
    }

    public void setkeepServerThreadAlive(boolean value) {
        this.keepServerThreadAlive = value;
    }
    public void setwaitForClientReady(boolean value) {
        this.waitForClientReady = value;
    }
    public void setwaitForClientShips(boolean value) {
        this.waitForClientShips = value;
    }
    public void setwaitForShot(boolean value) {
        this.waitForShot = value;
    }
    
}
