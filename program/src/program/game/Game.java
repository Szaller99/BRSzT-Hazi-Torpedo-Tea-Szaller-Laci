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
    
    private Battleship[] myShips;
    private Battleship[] enemyShips;

    public GameFrame frame;

    public Communication communication;
    public GameState gameState;

    public Game(Controller app) {
        // this.communication.init();

        this.setupGame();        
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

    private void setupGame() {
        this.gameState = new GameState(); // -> game state is setup
        System.out.print("Status should be Setup, is " + this.gameState.getState().get() + " \n");
        // TODO setup scipts
        this.hostPlayer = new Player(true);
        this.clientPlayer = new Player();
        this.clientPlayer.ready(); // for testing
        // TODO set both Player.setMe() false or true
        this.myShips = createShips();
        this.enemyShips = createShips();
        // this.enemyShips[4].placeShip(2, 2, Orient.VERTICAL); // for testing
        // this.enemyShips[5].placeShip(1, 6, Orient.HORIZONTAL); // for testing

        this.frame = new GameFrame(this.app, this);
        this.frame.set2setup();
        
        // ...
        // TODO communication handshakes, so both apps are ready

    }

    private void sendShoot(int x, int y){
        // TODO send message to other player about shooting tile (x,y)

        // TODO: if handshake:
            this.updateSM();
            System.out.print("Status should be ClientTurn, is " + this.gameState.getState().get() + " \n");
            this.frame.set2enemyTurn();

        // wait for enemy to shoot
    }

    public void receiveEnemysShoot(int x, int y){

        // TODO call this function when other player sends where he shoots

        this.frame.gotHit(x, y);
        this.updateSM();
        System.out.print("Status should be host, is " + this.gameState.getState().get() + " \n");
        this.frame.set2myTurn();

    }

    public void sendReady(){
        // TODO
        // communication.send(this.myShips)
    }

    public void receiveEnemyReady(Battleship[] ships){

        // TODO call this function when other player sends he is ready

        this.enemyShips = ships;

        if(this.clientPlayer.isMe()){
            this.hostPlayer.ready();
        }
        else{
            this.clientPlayer.ready();
        }

        this.startGame();
    }

    private void setStatusReady(){
        this.updateSM(); // sets State Machine to Ready state
        System.out.print("Status should be Ready, is " + this.gameState.getState().get() + " \n");
        this.frame.set2ready();
        
        this.sendReady();

        this.startGame();
    }

    private void startGame() {
        if(this.hostPlayer.getReady() && this.clientPlayer.getReady()){
            this.updateSM();
            System.out.print("Status should be Host Turn, is " + this.gameState.getState().get() + " \n");
            if(this.gameState.getState() == GameSM.HostTurn && this.hostPlayer.isMe()){
                this.frame.set2myTurn();
            }
            else if (this.gameState.getState() == GameSM.ClientTurn && this.hostPlayer.isMe()){
                this.frame.set2enemyTurn();
            }
        }
    }

    private void updateSM() {
        // ..
        this.gameState.updateSM();
    }

    private void endGame() {
        // ..
        this.gameState.sm = this.gameState.sm.endGame();
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
        
        for(int i = 0; i < 7; i++)
        {
            if(this.myShips[i].getlength() == len && this.myShips[i].isPlaced() == false)
            {
                this.myShips[i].placeShip(xPos, yPos, or);
                return true;
            }
        }
        return false;
    }

    public tileType shootEnemy(int x, int y){
        this.sendShoot(x, y);
        for(int i = 0; i < 7; i++)
        {
            if(this.enemyShips[i].isMe(x, y) == true)
            {
                this.frame.setHit(x, y);
                int[][] ShipIDs = this.enemyShips[i].getTileIDs();
                int length = this.enemyShips[i].getlength();
                boolean gotDestroyed = true;
                for (int j=0;j<length;j++){
                    if (!this.frame.EnemyIsHit(ShipIDs[j][0], ShipIDs[j][1])){
                        gotDestroyed = false;
                    }
                }
                if (gotDestroyed){
                    this.enemyShips[i].destroy();
                    this.frame.enemyShips.hideShip(length);
                    this.frame.endEnemyShip(this.enemyShips[i].getX(), this.enemyShips[i].getY(), length, this.enemyShips[i].getOrient());
                }
                return tileType.ship;
            }
        }
        return tileType.water;
    }

    public int[][] deleteShip(int x, int y){
        for(int s = 0; s < 7; s++)
        {
            int[][] tiles = this.myShips[s].getTileIDs();
            for(int t = 0; t < this.myShips[s].getlength(); t++)
            {
                if((tiles != null) && (tiles[t][0] == x && tiles[t][1] == y))
                {
                    this.myShips[s].deleteShip();
                    return tiles;
                }
            }
        }
        return null;
    }

    public boolean ready2Play(){
        for(int i = 0; i < 7; i++)
        {
            if(this.myShips[i].isPlaced() == false)
            {
                return false;
            }
        }
        
        if(this.clientPlayer.isMe()){
            this.clientPlayer.ready();
        }
        else{
            this.hostPlayer.ready();
        }

        this.setStatusReady();
        return true;
    }
}
