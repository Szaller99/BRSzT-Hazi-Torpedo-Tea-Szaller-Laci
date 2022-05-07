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
    private Battleship[] clientships; //ezeket lehet egy tömbbe fogom rakni a me/enemy miatt

    public GameFrame frame;

    public Communication communication;
    public GameState gameState;

    public Game(Controller app) {
        this.hostPlayer = new Player(true);
        this.clientPlayer = new Player();
        this.hostships = createShips();
        this.clientships = createShips();
        this.clientships[4].placeShip(2, 2, Orient.VERTICAL); // for testing
        // Battleship/placeShip comment folyt.: szóvel pl itt ha megadnám hogy legyen ez a hajó egy 4-es akkor az megváltoztatja a hosszát és 2 4-es hajó lesz de 0 3-as... -> nem kell abba a függvénybe a length csak bennemaradt
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



    public boolean placeShip(int len, int xStart, int yStart, int xEnd, int yEnd) { //ide kell length, hogy tudjuk melyik hajót kell lerakni
        int xPos = 0;
        int yPos = 0;
        Orient or = Orient.VERTICAL;
        if (xStart == xEnd){
            xPos = xStart;
            or = Orient.HORIZONTAL;
            if(yStart<yEnd){
                yPos = yStart;
            }
            else{
                yPos = yEnd;
            }
        }
        else if(yStart == yEnd){
            yPos = yStart;
            or = Orient.VERTICAL;
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
                if(tiles[t][0] == x && tiles[t][1] == y)
                {
                    return tiles;
                }
            }
        }
        return null;
    }

    public boolean ready2Play(){
        // todo:
        // biztos kell ez? nem egy gomb lesz erre? -> ezt a függvényt hívja meg a gomb listenerje... csak a gomb nem látja, hogy le van e helyezve minden hajó, ezért itt kell megmondani neki... szóval kell -> Okés
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
}
