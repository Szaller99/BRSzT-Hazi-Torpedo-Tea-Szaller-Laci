package program.controller;
import program.view.*;
import program.game.Game;

public class Controller {

    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private Game game;

    public Controller()
    {
        // this.welcomeFrame = new WelcomeFrame(this);

        this.game = new Game(this);
        this.gameFrame = this.game.frame;
    }

    public void start()
    {

    }
}
