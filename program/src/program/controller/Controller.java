package program.controller;
import program.view.*;

import program.controller.communication.Client;
import program.controller.communication.Server;
import program.controller.communication.Communication;
import program.controller.game.Game;
import program.controller.game.GameSM;

public class Controller {

    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private EndFrame endFrame;
    public Game game;
    public boolean gameCreated = false;
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
            while(this.game.sm == GameSM.Setup || this.game.sm == GameSM.Ready) {
                Thread.sleep(1);
                if(this.game.clientPlayer.getReady() && this.game.hostPlayer.getReady()){

                    this.game.startGame();
                    while(!this.gameStarted && this.game.sm!=GameSM.HostTurn) { Thread.sleep(1); }
                }
            };
                
            System.out.println("#3");
            while(this.game.sm != GameSM.Ended) {
                Thread.sleep(1);
                if(this.game.sm == GameSM.HostTurn) {
                    if(!this.isHost) {  // host turn van és client vagyok
                        this.client.setwaitForShot(true); // várok a lövésre
                        System.out.println("#4.1");
                        while(this.game.sm == GameSM.HostTurn) { Thread.sleep(1); }
                        System.out.println("#4.2");
                    }
                }
             
                if(this.game.sm == GameSM.ClientTurn) { 
                    if(this.isHost) { // client turn van és host vagyok
                        this.server.setwaitForShot(true); // várok a lövésre
                        System.out.println("#5.1");
                        while(this.game.sm == GameSM.ClientTurn) { Thread.sleep(1); }
                        System.out.println("#5.2");
                    }
                }
            }
            System.out.println("#6");
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
        this.welcomeFrame.hideAllFrames();
        this.gameFrame.setVisible(true);
        this.gameCreated = true;
        System.out.println("gameCreated set to " + this.gameCreated);
    }

    public void end(Boolean winner)
    {
        // this.gameFrame.setVisible(false);
        this.endFrame = new EndFrame(this, winner);
    }
}
