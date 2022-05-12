package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Communication {
    private Socket clientSocket;

    public Client() throws IOException{
        super(false);
        
    }

    public boolean connect() {
		try {
			System.out.println("connecting to " + this.serverIpAddressString + " on port " +  this.port);
		    clientSocket = new Socket(this.serverIpAddressString,  this.port);
			
            this.dos = new DataOutputStream(clientSocket.   getOutputStream());
		    this.dis = new DataInputStream(clientSocket.    getInputStream());
		    this.clientAccepted = true;
			
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + this.myIpAddress + ":" + this.port + " | Starting a server");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}
    
}
