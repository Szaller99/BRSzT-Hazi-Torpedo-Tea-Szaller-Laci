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
    
    private Battleship[] hostships;
    private Battleship[] clientships;

    public GameFrame frame;

    public Communication communication;
    public GameState gameState;

    public Game(Controller app) {
        this.hostPlayer = new Player(true);
        this.clientPlayer = new Player();
        this.hostships = createShips();
        this.clientships = createShips();
        this.clientships[4].placeShip(2, 2, Orient.VERTICAL);
        this.clientships[5].placeShip(1, 6, Orient.HORIZONTAL);
        this.gameState = new GameState();
        // this.communication.init();
        this.startGame();
    }

    private Battleship[] createShips() {
        Battleship[] ships = new Battleship[7];
        Dimension defaultPoint = new Dimension(0,0);
        ships[0]=(new Battleship(1));
        ships[1]=(new Battleship(1));
        ships[2]=(new Battleship(2));
        ships[3]=(new Battleship(2));
        ships[4]=(new Battleship(3));
        ships[5]=(new Battleship(4));
        ships[6]=(new Battleship(5));
        return ships;
    }

    private void startGame() {
        this.frame = new GameFrame(this.app, this);
        this.gameState.update(this);
    }



    public boolean placeShip(int len, int xStart, int yStart, int xEnd, int yEnd) {
        int xPos = 0;
        int yPos = 0;
        Orient or = Orient.VERTICAL;
        if (xStart == xEnd){
            xPos = xStart;
            or = Orient.VERTICAL;
            if(yStart<yEnd){
                yPos = yStart;
            }
            else{
                yPos = yEnd;
            }
        }
        else if(yStart == yEnd){
            yPos = yStart;
            or = Orient.HORIZONTAL;
            if(xStart<xEnd){
                xPos=xStart;
            }
            else{
                xPos=xEnd;
            }
        }
        
        // todo -> corrected
        for(int i = 0; i < 7; i++)
        {
            if(this.hostships[i].getlength() == len && this.hostships[i].isPlaced() == false)
            {
                this.hostships[i].placeShip(xPos, yPos, or);
                return true;
            }
        }
        return false;
    }

    public tileType shootEnemy(int x, int y){
        // todo: got the type of enemy's tile in x,y
        for(int i = 0; i < 7; i++)
        {
            if(this.clientships[i].isMe(x, y) == true)
            {
                this.frame.setHit(x, y);
                int[][] ShipIDs = this.clientships[i].getTileIDs();
                int length = this.clientships[i].getlength();
                boolean gotDestroyed = true;
                for (int j=0;j<length;j++){
                    if (!this.frame.EnemyIsHit(ShipIDs[j][0], ShipIDs[j][1])){
                        gotDestroyed = false;
                    }
                }
                if (gotDestroyed){
                    this.clientships[i].destroy();
                    this.frame.enemyShips.hideShip(length);
                    this.frame.endEnemyShip(this.clientships[i].getX(), this.clientships[i].getY(), length, this.clientships[i].getOrient());
                }
                return tileType.ship;
            }
        }
        return tileType.water;
    }

    public int[][] deleteShip(int x, int y){

        // todo: delete ship on (x,y) tile, return tiles where the ship was (it will desappear already from field I hope)
        // note: (x,y) tile is not the start of the ship, it could be any tile which is part of the ship!!! -> Done
        for(int s = 0; s < 7; s++)
        {
            int[][] tiles = this.hostships[s].getTileIDs();
            for(int t = 0; t < this.hostships[s].getlength(); t++)
            {
                if((tiles != null) && (tiles[t][0] == x && tiles[t][1] == y))
                {
                    this.hostships[s].deleteShip();
                    return tiles;
                }
            }
        }
        return null;
    }

    public boolean ready2Play(){
        // todo:
        // check if all the ships are placed
        // if yes, set the status to ready to play
        // if no then don't do anything and return false

        for(int i = 0; i < 7; i++)
        {
            if(this.hostships[i].isPlaced() == false)
            {
                return false;
            }
        }
        //this.hostPlayer.ready();
        return true;
    }

    public void gotHit(int x, int y){
        // todo: call this function when get tile ID where enemy shoots from communication
        this.frame.gotHit(x, y);
    }
}
