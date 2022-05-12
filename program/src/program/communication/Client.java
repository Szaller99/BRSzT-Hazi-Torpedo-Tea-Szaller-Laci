package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import program.controller.Controller;

public class Client extends Communication {
    private Socket clientSocket;
	private boolean keepClientThreadAlive = true;
	private boolean waitForHostShips = false;


    public Client(Controller app) throws IOException{
        super(app, false);
        
    }

    public boolean connect() {
		try {
			System.out.println("[client] connecting to " + this.serverIpAddressString + " on port " +  this.port);
		    clientSocket = new Socket(this.serverIpAddressString,  this.port);
            this.dos = new DataOutputStream(clientSocket.   getOutputStream());
		    this.dis = new DataInputStream(clientSocket.    getInputStream());
		    this.clientAccepted = true;
			
		} catch (IOException e) {
			System.out.println("[client] Unable to connect to the address: " + this.myIpAddress + ":" + this.port + " | Starting a server");
			return false;
		}
		System.out.println("[client] Client connected to the server.");
		return true;
	}

	@Override
    public void run(){
        while(this.keepClientThreadAlive) { // waiting for tasks
			try {		
            	if(this.waitForHostShips) {
					System.out.println("[client] waitForHosrShips was set to true");

					this.app.game.enemyShips = this.parseShips(this.receiveEnemyShips());
					this.app.game.hostPlayer.setReady();
            	    this.setwaitForHostShips(false);
				}
            	if(this.waitForShot) {
					// TODO implement
					System.out.println("[client] waitForShot was set to true");
            	    this.setwaitForShot(false);
				}
			} catch (Exception e) {
				//TODO: handle exception
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
