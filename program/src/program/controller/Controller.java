package program.controller;
import program.view.*;

import program.communication.Client;
import program.communication.Server;
import program.communication.Communication;
import program.game.Game;
import program.game.GameSM;

public class Controller {

    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private Game game;
    private boolean gameCreated = false;
    public boolean isHost;
    public Communication comm;
    public Client client;
    public Server server;
    public Thread thread;

    public Controller()
    {
            try {
                   
            // starting the app by creating welcome frame
            this.welcomeFrame = new WelcomeFrame(this);
                
            System.out.println("#1");
            while(!this.gameCreated) {Thread.sleep(10);} // waiting for game creation
               
            if(this.isHost) {
                this.server.setwaitForClientReady(true);   
                this.server.setwaitForClientShips(true);
            } else if(!this.isHost) {
                this.client.setwaitForHostShips(true);
            }
            
            System.out.println("#2");
            while(this.game.gameState.sm != GameSM.Ready) {};
                
            System.out.println("#3");
            while(this.game.gameState.sm != GameSM.Ended) {
                if(this.game.gameState.sm == GameSM.HostTurn) {
                    this.client.setwaitForShot(true);
                    System.out.println("#4.1");
                    while(this.game.gameState.sm == GameSM.HostTurn) {}
                    System.out.println("#4.2");
                }
             
                if(this.game.gameState.sm == GameSM.ClientTurn) {
                    this.server.setwaitForShot(true);
                    System.out.println("#5.1");
                    while(this.game.gameState.sm == GameSM.ClientTurn) {}
                    System.out.println("#5.2");
                }
            }
            System.out.println("#6");
            handleEnding();
        } catch (Exception e) {

        }
    }

    public void create(){
        this.isHost = true;
        System.out.print("Create Game in app \n");
        
        try {            
            this.server = new Server(); // beware, this blocks the code
            this.server.start(); // starting new thread for communications
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.game = new Game(this);
        this.gameFrame = this.game.frame;
        this.gameFrame.setVisible(false);

        this.start();
    }

    public void join(String ip){
        this.game = new Game(this);
        this.gameFrame = this.game.frame;
        this.gameFrame.setVisible(false);
        this.isHost = false;
        

        try {
            this.client = new Client();
            this.client.start(); // starting new thread for communications
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.client.serverIpAddressString = ip;
        
        try {
            client.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
           
        this.start();
    }

    public void start()
    {
        this.welcomeFrame.setVisible(false);
        this.gameFrame.setVisible(true);
        this.gameCreated = true;
        System.out.println("gameCreated set to " + this.gameCreated);
    }

    public void handleEnding() {

    }
}
