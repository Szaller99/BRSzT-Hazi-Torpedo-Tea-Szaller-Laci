package program.controller.game;

import program.controller.Controller;
import program.model.*;
import program.view.GameFrame;

public class Game {
    private Controller app;
    public Player hostPlayer;
    public Player clientPlayer;
    
    public Battleship[] myShips;
    public Battleship[] enemyShips;

    public GameFrame frame;
    public GameSM sm;

    public Game(Controller app) {
        this.app = app;
        this.setupGame();        
    }

    private Battleship[] createShips() {
        Battleship[] ships = new Battleship[7];
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
        this.sm = GameSM.Setup;
        System.out.print("Status should be Setup, is " + this.sm.get() + " \n");

        // TODO setup scipts
        if(this.app.isHost) {
            this.hostPlayer = new Player(true);
            this.clientPlayer = new Player();
        } else {
            this.hostPlayer = new Player();
            this.clientPlayer = new Player(true);
        }

        this.myShips = createShips();
        this.enemyShips = createShips();

        this.frame = new GameFrame(this.app, this);
        this.frame.set2setup();
        

    }

    private void sendShoot(int x, int y){
        if (this.app.isHost) {
            this.app.server.sendShot(x,y);
        } else {
            this.app.client.sendShot(x,y);
        }
        this.updateSM(); // sets SM to ClientTurn
        System.out.print("Status should be ClientTurn, is " + this.sm.get() + " \n");
        this.frame.set2enemyTurn();
    }

    public void receiveEnemysShoot(int x, int y){
        this.frame.gotHit(x, y);

        // TODO test this:
        for(int i = 0; i < 7; i++){
            if(this.myShips[i].isMe(x, y) == true){
                this.frame.setHit(x, y);
                int[][] ShipIDs = this.myShips[i].getTileIDs();
                int length = this.myShips[i].getLength();
                boolean gotDestroyed = true;
                for (int j=0;j<length;j++){
                    if (!this.frame.isHit(ShipIDs[j][0], ShipIDs[j][1])){
                        gotDestroyed = false;
                    }
                }
                if (gotDestroyed){
                    this.myShips[i].destroy();
                    this.frame.myShips.hideShip(length);
                }
                this.isEnded();
            }
        }

        System.out.print("Status is " + this.sm.get() + ";");
        this.updateSM(); // sets SM to HostTurn
        System.out.print("Should be updated, is " + this.sm.get() + " \n");
        this.frame.set2myTurn();


    }

    public void sendClientReadyAndShips(){
        this.app.client.sendReadyMessage();
        this.app.client.sendShips(this.myShips);
        this.updateSM(); // sets State Machine to Ready state
        
    }

    public void sendHostShipsAndSetReady() {
        this.app.server.readyToSendShips = true;
        while(!this.app.server.readyToUpdateSM) { 
            try {
                Thread.sleep(1); 
            } catch (Exception e) { }   
        } // waiting for main thread to act
        this.updateSM();
    }

    private void setStatusReady(){
        this.frame.set2ready();
        if (this.clientPlayer.isMe()) // adatok átküldése
        {
            this.sendClientReadyAndShips(); // csak a kliens küldi ezt át a hálózaton
        } else {
            this.sendHostShipsAndSetReady(); // szerver kezelheti az adatok komminkálását
        }

        if(this.clientPlayer.isMe()){ // saját ready-k beállítása
            this.clientPlayer.setReady();
        }
        else{
            this.hostPlayer.setReady();
        }

        System.out.print("Status should be Ready, is " + this.sm.get() + " \n");
        
        
    }

    public void startGame() {
    
        if(this.hostPlayer.getReady() && this.clientPlayer.getReady()){
            this.updateSM(); // sets state to HostTurn
            System.out.print("Status should be Host Turn, is " + this.sm.get() + " \n");
            if(this.sm == GameSM.HostTurn && this.hostPlayer.isMe()){
                this.frame.set2myTurn();
                System.out.print("Frame set to My Turn\n");
            }
            else if (this.sm == GameSM.HostTurn && this.clientPlayer.isMe()){
                this.frame.set2enemyTurn();
                System.out.print("Frame set to Enemy Turn\n");
            }
            this.frame.startFight();
            this.app.gameStarted = true;
        }
    }

    public void updateSM() {
        this.sm = this.sm.nextState();
    }
    public void endGameSM() {
        this.sm.endGame();
    }


    private void endGame() {
        // TODO ...
        this.sm = this.sm.endGame();
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
            if(this.myShips[i].getLength() == len && this.myShips[i].isPlaced() == false)
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
                int length = this.enemyShips[i].getLength();
                boolean gotDestroyed = true;
                for (int j=0;j<length;j++){
                    if (!this.frame.isHit(ShipIDs[j][0], ShipIDs[j][1])){
                        gotDestroyed = false;
                    }
                }
                if (gotDestroyed){
                    this.enemyShips[i].destroy();
                    this.frame.enemyShips.hideShip(length);
                    this.frame.endEnemyShip(this.enemyShips[i].getX(), this.enemyShips[i].getY(), length, this.enemyShips[i].getOrient());
                }
                this.isEnded();
                return tileType.ship;
            }
        }
        return tileType.water;
    }

    public int[][] deleteShip(int x, int y){
        for(int s = 0; s < 7; s++)
        {
            int[][] tiles = this.myShips[s].getTileIDs();
            for(int t = 0; t < this.myShips[s].getLength(); t++)
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

        this.setStatusReady();
        return true;
    }

    public void isEnded(){
        boolean winner = true;
        boolean loser = true;
        for(int i = 0; i < 7; i++) {
            if(this.myShips[i].isDestroyed() == false) {
                loser = false;
            }
            if(this.enemyShips[i].isDestroyed() == false) {
                winner = false;
            }
        }
        if(loser == false && winner == false) {
            return;
        }
        if(winner) {
            this.endGameSM();
            this.app.end("You are the winner");
        } else {
            this.endGameSM();
            this.app.end("You lost");
        }
        return;
    }
}
