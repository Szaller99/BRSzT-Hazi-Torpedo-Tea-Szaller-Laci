package program.controller;
import program.view.*;

import java.net.InetAddress;

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
        this.thread = new Thread();
        this.welcomeFrame = new WelcomeFrame(this);
        
    }

    public void create(){
        this.isHost = true;
        thread.start();
        System.out.print("Create Game in app \n");
        
        try {
            this.server = new Server();
            System.out.print("Server socket should listen.. \n");

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        this.game = new Game(this);
        this.gameFrame = this.game.frame;
        this.gameFrame.setVisible(false);
    }

    public void join(String ip){
        this.isHost = false;
        System.out.print("Join Game in app with ip: " + ip + " \n");
        this.client.serverIpAddressString = ip;

        try {
            client = new Client();
            System.out.print("Client socket created.. \n");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }

        try {
            client.connect();
            System.out.print("Client sould be connected! \n");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        this.game = new Game(this);
        this.gameFrame = this.game.frame;
        this.gameFrame.setVisible(false);
        
       
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
