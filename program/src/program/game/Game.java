package program.game;

import program.communication.Communication;
import program.controller.Controller;
import program.view.GameFrame;
import java.awt.*;

public class Game {
    private Controller app;
    private Player hostPlayer;
    private Player clientPlayer;
    private BattleShip[] hostShips;
    private BattleShip[] clientShips;

    public GameFrame frame;

    public Communication communication;
    public GameState gameState;

    public Game(Controller app) {
        this.hostPlayer = new Player(true);
        this.clientPlayer = new Player();
        this.hostShips = createShips();
        this.clientShips = createShips();
        this.gameState = new GameState();
        // this.communication.init();
        this.startGame();
    }

    private BattleShip[] createShips() {
        BattleShip[] ships = new BattleShip[6];
        Dimension defaultPoint = new Dimension(0,0);
        ships[0]=(new BattleShip(5, defaultPoint, 0));
        ships[1]=(new BattleShip(4, defaultPoint, 0));
        ships[2]=(new BattleShip(3, defaultPoint, 0));
        ships[3]=(new BattleShip(3, defaultPoint, 0));
        ships[4]=(new BattleShip(2, defaultPoint, 0));
        ships[5]=(new BattleShip(2, defaultPoint, 0));
        return ships;
    }

    private void startGame() {
        this.gameState.update(this);
        this.frame = new GameFrame(this.app);
    }
}
