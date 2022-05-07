package program.game;

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
    public void placeShip(int len, int xPos, int yPos, Orient or) {
        this.isPlaced = true;
        this.isDestroyed = false;
        this.length = len;
        this.xPosition = xPos;
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

    public void getTileIDs() {
        //TODO
    }



    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public boolean isPlaced() {
        return this.isPlaced;
    }

    public int getlength() {
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
}
