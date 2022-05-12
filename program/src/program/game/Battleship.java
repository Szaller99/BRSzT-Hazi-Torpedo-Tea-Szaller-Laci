package program.game;

import program.game.*;

public class Battleship {
    
    private boolean isDestroyed;
    private boolean isPlaced;
    private int length;
    private int xPosition;
    private int yPosition;
    private Orient Orientation;


    public Battleship(int len, int xPos, int yPos, Orient or) {
        this.isPlaced = true;
        this.isDestroyed = false;
        this.length = len;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.Orientation = or;
    }

    public Battleship(int len) {
        this.isPlaced = false;
        this.isDestroyed = false;
        this.length = len;
        this.xPosition = 0;
        this.yPosition = 0;
        this.Orientation = Orient.VERTICAL;
    }
    public Battleship() {
        this.isPlaced = false;
        this.isDestroyed = false;
        this.length = 0;
        this.xPosition = 0;
        this.yPosition = 0;
        this.Orientation = Orient.VERTICAL;
    }
    public void placeShip(int xPos, int yPos, Orient or) {
        this.isPlaced = true;
        this.isDestroyed = false;
        //this.length = len; // most akkor mikor állítjuk be a hosszát, amikor létrehozod, vagy amikor lerakod? Lerakáskor megváltozhat a hossza egy hajónak? Ja ez a sor ide nem kell
        this.xPosition = xPos; //létrehozáskor állítom be a hosszát, mert úgy figyelem, hogy melyik hajók vannak már lerakva
        this.yPosition = yPos;
        this.Orientation = or;
    }
    public void rotate() {
        if(this.Orientation == Orient.VERTICAL)
        {
            this.Orientation = Orient.HORIZONTAL;
        }
        else
        {
            this.Orientation = Orient.VERTICAL;
        }
    }

    public int[][] getTileIDs() {
        if(this.isPlaced() == false)
        {
            return null;
        }
        int[][] tiles = new int[this.length][2];
        for(int i = 0; i < this.length; i++)
        {
            if(this.Orientation == Orient.HORIZONTAL)
            {
                tiles[i][0] = this.xPosition + i;
                tiles[i][1] = this.yPosition;
            } else
            {
                tiles[i][0] = this.xPosition;
                tiles[i][1] = this.yPosition + i;
            }
            
        }
        return tiles;
    }



    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public boolean isPlaced() {
        return this.isPlaced;
    }

    public int getLength() {
        return this.length;
    }

    public boolean destroy() {
        if(this.isDestroyed == true) {
            return false;
        } else {
            this.isDestroyed = true;
            return true;
        }
    }

    public boolean deleteShip() {
        if(this.isPlaced == true)
        {
            this.isPlaced = false;
            return true;
        }
        return false;
    }

    public int getX() {
        return this.xPosition;
    }

    public int getY() {
        return this.yPosition;
    }

    public boolean isMe(int x, int y) {
        if(this.isPlaced() == false)
        {
            return false;
        }
        int[][] tiles = this.getTileIDs();
        for(int i = 0; i < this.length; i++)
        {
            if(tiles[i][0] == x && tiles[i][1] == y)
            {
                return true;
            }
        }
        return false;
    }

    public Orient getOrient(){
        return this.Orientation;
    }

    public int getIntOrient(){
        return (this.Orientation == Orient.VERTICAL) ? 0 : 1;
    }
}
