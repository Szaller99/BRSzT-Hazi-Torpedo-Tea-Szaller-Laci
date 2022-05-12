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
    private boolean isHost;
    public Communication comm;
    public Client client;
    public Server server;
    public Thread thread;

    public Controller()
    {
        // this.welcomeFrame = new WelcomeFrame(this);
        System.out.println("my address is:" + Communication.getMyIpAddressString());
        this.welcomeFrame = new WelcomeFrame(this);
        
    }

    public void create(){
        this.isHost = true;
        System.out.print("Create Game in app \n");
        
        try {
            System.out.print("Server socket waiting for client.. \n");
            this.server = new Server();

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
            client = new Client();
            System.out.print("Client instance created.. \n");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
        this.client.serverIpAddressString = ip;
        System.out.print("Join Game in app with ip: " + this.client.serverIpAddressString + " \n");

        try {
            client.connect();
            System.out.print("Client sould be connected! \n");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("error here");
        }
        
       
        // TODO find and join game

        this.start();
    }

    public void start()
    {
        this.isHost = true;
        this.welcomeFrame.setVisible(false);
        this.gameFrame.setVisible(true);
    }
}
