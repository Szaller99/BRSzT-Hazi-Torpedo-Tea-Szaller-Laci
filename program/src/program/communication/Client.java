package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Communication {
    private Socket clientSocket;
	private boolean keepClientThreadAlive = true;
	private boolean waitForHostShips = false;


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
		System.out.println("Client connected to the server.");
		return true;
	}

	@Override
    public void run(){
        while(this.keepClientThreadAlive) { // waiting for tasks
            if(this.waitForHostShips) {
				// TODO implement

                this.setwaitForHostShips(false);
			}
            if(this.waitForShot) {
				// TODO implement

                this.setwaitForShot(false);
			}
        }
    }

    public void setkeepClientThreadAlive(boolean value) {
        this.keepClientThreadAlive = value;
    }
    public void setwaitForHostShips(boolean value) {
        this.waitForHostShips = value;
    }
    public void setwaitForShot(boolean value) {
        this.waitForShot = value;
    }
    
}
