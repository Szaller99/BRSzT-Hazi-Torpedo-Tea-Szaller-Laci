package program.controller;
import program.view.*;

import program.communication.Client;
import program.communication.Server;
import program.communication.Communication;
import program.game.Game;

public class Controller {

    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private Game game;
    public boolean isHost;
    public Communication comm;
    public Client client;
    public Server server;
    public Thread thread;

    public Controller()
    {
        // this.welcomeFrame = new WelcomeFrame(this);
        // System.out.println("my address is:" + Communication.getMyIpAddressString());
        this.welcomeFrame = new WelcomeFrame(this);
        
    }

    public void create(){
        this.isHost = true;
        System.out.print("Create Game in app \n");
        
        try {            
            this.server = new Server(); // beware, this blocks the code
            this.server.start(); // starting new thread for communications
        } catch (Exception e) {
            //TODO: handle exception
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
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
        this.client.serverIpAddressString = ip;
        
        try {
            client.connect();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
        
       
        // TODO find and join game

        this.start();
    }

    public void start()
    {
        this.welcomeFrame.setVisible(false);
        this.gameFrame.setVisible(true);
    }
}
