package program.game;


import javafx.geometry.Point2D;

public class BattleShip {
    enum Orient {
        VERTICAL,
        HORIZONTAL
    }

    private boolean isDestroyed;
    private int length;
    private Point2D position;
    private Orient Orientation ;

    public BattleShip(int len, Point2D pos, Orient or) {
        this.isDestroyed = false;
        this.length = len;
        this.position = pos;
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

    public int getlength() {
        return this.length;
    }
}
