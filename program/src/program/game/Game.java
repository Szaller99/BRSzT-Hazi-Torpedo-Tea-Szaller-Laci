package program.game;
import program.game.Battleship;

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
        this.frame = new GameFrame(this.app);
        this.gameState.update(this);
    }



    private Battleship[] hostShips;
    private Battleship[] enemyShips;

    public Game() {
        int len;
        for(int i = 0; i < 7; i++)
        {
            switch (i) {
                case 0:
                case 1:
                    len = 1;
                    break;
                case 2:
                case 3:
                    len = 2;
                    break;
                case 4:
                    len = 3;
                    break;
                case 5:
                    len = 4;
                    break;
                case 6:
                    len = 5;
                    break;
                default:
                    len = 0;
                    break;
            }
            this.hostShips[i] = new Battleship(len);
            this.enemyShips[i] = new Battleship(len);
        }
        
    }

    public boolean placeShip(int len, int xPos, int yPos, Orient or) {
        int i = 0;
        while (!(this.hostShips[i].getlength() == len && this.hostShips[i].isPlaced() == false)) {
            i++;
        }
        if(i>7) {
            return false;
        } else {
            this.hostShips[i].placeShip(len, xPos, yPos, or);
            return true;
        }
    }
}
