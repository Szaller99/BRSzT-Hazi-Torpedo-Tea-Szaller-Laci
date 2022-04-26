package program.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameTile extends Tile{
    public GameTile(int xPosition, int yPosition){
        super(xPosition, yPosition);
    }

    public void mouseClicked(MouseEvent e){
        System.out.print("game tile " + this.tileName + " was clicked \n");
        this.setBackground(Color.RED);
    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }
}
