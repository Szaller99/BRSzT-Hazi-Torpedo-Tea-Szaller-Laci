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
    public Game game;
    private boolean gameCreated = false;
    public boolean gameStarted = false;
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
            while(!this.gameCreated) { Thread.sleep(1); } // waiting for game creation
               
            // System.out.println("#1 - ishost:" + this.isHost);
            if(this.isHost) {
                this.server.setwaitForClientReady(true);  
                // System.out.println("[server] setwaitForClientReady was set to true"); 
            } else if(!this.isHost) {
                // System.out.println("[client] waitForHostShips was set to true");
                this.client.setwaitForHostShips(true);
            }
            
            System.out.println("#2");
            while(this.game.gameState.sm == GameSM.Setup || this.game.gameState.sm == GameSM.Ready) {
                Thread.sleep(1);
                if(this.game.clientPlayer.getReady() && this.game.hostPlayer.getReady()){

                    this.game.startGame();
                }
            };
                
            System.out.println("#3");
            while(this.game.gameState.sm != GameSM.Ended) {
                Thread.sleep(1);
                if(this.game.gameState.sm == GameSM.HostTurn) {
                    if(!this.isHost) {
                        this.client.setwaitForShot(true);
                        System.out.println("#4.1");
                        while(this.game.gameState.sm == GameSM.HostTurn) { Thread.sleep(10); }
                        System.out.println("#4.2");
                    }
                }
             
                if(this.game.gameState.sm == GameSM.ClientTurn) {
                    if(this.isHost) {
                        this.server.setwaitForShot(true);
                        System.out.println("#5.1");
                        while(this.game.gameState.sm == GameSM.ClientTurn) { Thread.sleep(10); }
                        System.out.println("#5.2");
                    }
                }
            }
            System.out.println("#6");
            handleEnding();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void create(){
        this.isHost = true;
        System.out.print("Create Game in app \n");
        
        try {            
            this.server = new Server(this); // beware, this blocks the code
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
            this.client = new Client(this);
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
