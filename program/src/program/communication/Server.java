package program.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import program.controller.Controller;
import program.game.Battleship;
import program.game.GameSM;

public class Server extends Communication{
    public ServerSocket serverSocket;
    public Socket clientSocket;
    private boolean keepServerThreadAlive = true;
    private boolean waitForClientReady = false;
    private boolean waitForClientShips = false;


    
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
            while(this.keepServerThreadAlive) { // waiting for tasks
                if(this.waitForClientReady) {
                    System.out.println("[server] setwaitForClientReady was set to true");
                    while (this.isClientReady()) { }         
                    System.out.println("beep");
                     this.app.game.clientPlayer.setReady();
                    this.setwaitForClientReady(false);
                }
                if(this.waitForClientShips) {
                    System.out.println("[server] setwaitForClientShips was set to true");
                    Battleship[] clientShips = this.parseShips(receiveEnemyShips());
                    this.app.game.enemyShips = clientShips;

                    while(this.app.game.gameState.sm  != GameSM.Ready) { Thread.sleep(10); }
                    this.sendShips(this.app.game.myShips);

                    this.setwaitForClientShips(false);
                }
                if(this.waitForShot) {
                    // TODO implement
                    System.out.println("[server] setwaitForShot was set to true");
                    this.setwaitForShot(false);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
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

    public boolean isClientReady() throws IOException {
        String str = (String)dis.readUTF(); 
        System.out.println("[server] got message: " + str);
        return (str == this.readyMessage);
    }
    
}
