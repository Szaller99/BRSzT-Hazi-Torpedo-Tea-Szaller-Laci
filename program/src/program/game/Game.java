package program.game;

import program.communication.Communication;
import program.controller.Controller;
import program.view.components.*;
import program.view.GameFrame;

import java.awt.*;

public class Game {
    private Controller app;
    private Player hostPlayer;
    private Player clientPlayer;

    public GameFrame frame;

    public Communication communication;
    public GameState gameState;

    public Game(Controller app) {
        this.hostPlayer = new Player(true);
        this.clientPlayer = new Player();
        this.hostships = createShips();
        this.clientships = createShips();
        this.gameState = new GameState();
        // this.communication.init();
        this.startGame();
    }

    private Battleship[] createShips() {
        Battleship[] ships = new Battleship[6];
        Dimension defaultPoint = new Dimension(0,0);
        ships[0]=(new Battleship());
        ships[1]=(new Battleship());
        ships[2]=(new Battleship());
        ships[3]=(new Battleship());
        ships[4]=(new Battleship());
        ships[5]=(new Battleship());
        return ships;
    }

    private void startGame() {
        this.frame = new GameFrame(this.app, this);
        this.gameState.update(this);
    }


    private Battleship[] hostships;
    private Battleship[] clientships;

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
            this.hostships[i] = new Battleship(len);
            this.clientships[i] = new Battleship(len);
        }
        
    }

    public boolean placeShip(int len, int xPos, int yPos, Orient or) {
        int i = 0;
        while (!(this.hostships[i].getlength() == len && this.hostships[i].isPlaced() == false)) {
            i++;
        }
        if(i>7) {
            return false;
        } else {
            this.hostships[i].placeShip(len, xPos, yPos, or);
            return true;
        }
    }

    public tileType shootEnemy(int x, int y){
        // todo: got the type of enemy's tile in x,y

        return tileType.water;
    }
}
