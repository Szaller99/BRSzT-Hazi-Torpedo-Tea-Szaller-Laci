package program.controller;
import program.view.*;
import program.communication.Communication;
import program.game.Game;

public class Controller {

    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private Game game;
    private Communication comm;

    public Controller()
    {
        // this.welcomeFrame = new WelcomeFrame(this);
        System.out.println("my address is:" + this.comm.getMyIpAddressString());

        this.game = new Game(this);
        this.gameFrame = this.game.frame;
    }

    public void start()
    {

    }
}
