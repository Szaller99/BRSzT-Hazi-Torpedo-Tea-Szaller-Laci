package program.controller.communication;

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
		try {	
			while(!this.app.gameCreated) { Thread.sleep(1);}
        	while(this.keepClientThreadAlive) { // waiting for tasks
				Thread.sleep(1);
        	    if(this.waitForHostShips) {
					System.out.println("[client] waitForHostShips was set to true");
					this.app.game.enemyShips = this.parseShips(this.receiveEnemyShips());
					this.app.game.hostPlayer.setReady();
					while (!this.app.gameStarted) { Thread.sleep(1); } // waiting for main thread to act
        	        this.setwaitForHostShips(false);
					System.out.println("[client] waitForHostShips was set to false");
				}
        	    if(this.waitForShot) {
					System.out.println("[client] waitForShot was set to true");
					this.handleEnemyShot();
        	        this.setwaitForShot(false);
					System.out.println("[client] waitForShot was set to false");
				}
			}
		} catch (Exception e) {
				System.out.println(e);
		}
    }

    public void setkeepClientThreadAlive(boolean value) {
        this.keepClientThreadAlive = value;
    }
    public void setwaitForHostShips(boolean value) {
        this.waitForHostShips = value;
    }
    
}
