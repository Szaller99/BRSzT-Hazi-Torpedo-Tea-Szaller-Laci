package program.controller;
import program.view.*;
import program.game.Game;

public class Controller {

    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private Game game;

    public Controller()
    {
        this.welcomeFrame = new WelcomeFrame(this);
        
    }

    public void create(){
        System.out.print("Create Game in app \n");
        this.game = new Game(this);
        this.gameFrame = this.game.frame;
        this.gameFrame.setVisible(false);
    }

    public void join(String ip){
        System.out.print("Join Game in app with ip: " + ip + " \n");

        // TODO find and join game

        this.start();
    }

    public void start()
    {
        this.welcomeFrame.setVisible(false);
        this.gameFrame.setVisible(true);
    }
}
