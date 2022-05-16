package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import program.controller.Controller;

public class Server extends Communication{
    public ServerSocket serverSocket;
    public Socket clientSocket;
    private boolean keepServerThreadAlive = true;
    private boolean waitForClientReady = false;
    public boolean readyToSendShips = false;
    public boolean readyToUpdateSM = false;


    
    public Server(Controller app) throws IOException{
        super(app, true);
        System.out.print("[server] Creating server socket \n");
        this.serverSocket = new ServerSocket(this.port, 8, this.myIpAddress);
        System.out.print("[server] Server socket created \n");
        System.out.print("[server] Server socket waiting for client.. \n");
        listen(); // beware, this blocks the code
        System.out.print("[server] Client connected to server! \n");
    }

    private void listen() throws IOException{
        this.clientSocket = this.serverSocket.accept(); // waits for client connection; beware, this blocks the code
        System.out.println("[server] client connected");
        this.dos = new DataOutputStream(clientSocket.getOutputStream());
		this.dis = new DataInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run(){
        try { 
            while(!this.app.gameCreated) { Thread.sleep(1);}
            while(this.keepServerThreadAlive) { // waiting for tasks
                if(this.waitForClientReady) {
                    System.out.println("[server] setwaitForClientReady was set to true");
                    while (this.isClientReady()) { Thread.sleep(1); }         
                    this.app.game.clientPlayer.setReady();  // getting client ready 
                    this.app.game.enemyShips = this.parseShips(receiveEnemyShips());  // getting ships

                    while(!this.readyToSendShips) { Thread.sleep(1); } // waiting for main thread to act
                    System.out.println("[server] sending ships...");
                    this.sendShips(this.app.game.myShips);
                    this.readyToUpdateSM = true;

                    while (!this.app.gameStarted) { } // waiting for main thread to act
                    this.setwaitForClientReady(false);
                }
                if(this.waitForShot) {
					System.out.println("[client] waitForShot was set to true");
                    this.handleEnemyShot();
            	    this.setwaitForShot(false);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setkeepServerThreadAlive(boolean value) {
        this.keepServerThreadAlive = value;
    }
    public void setwaitForClientReady(boolean value) {
        this.waitForClientReady = value;
    }
    public void setwaitForShot(boolean value) {
        this.waitForShot = value;
    }

    public boolean isClientReady() throws IOException {
        String str = (String)dis.readUTF(); 
        System.out.println("[server] got client ready: " + str);
        return (str == Communication.readyMessage);
    }
    
}
