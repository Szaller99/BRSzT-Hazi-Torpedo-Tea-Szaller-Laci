package program.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

enum ShipEndType {upper,lower,left,right}

public class Tile extends JButton implements MouseListener{
    public String tileName;
    public Tile(int xPosition, int yPosition) {
        this.setBounds(30+xPosition*20, 50+yPosition*20, 20, 20);
        this.tileName = "x" + String.valueOf(xPosition) + "y" + String.valueOf(yPosition);
        this.setBackground(new Color(100,100,100));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void mouseClicked(MouseEvent e){
       
    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }

    public void set2Water(){
        
    }

    public void set2MiddleShip(boolean vertically){
       
    }

    public void set2EndShip(ShipEndType type){
       
    }

    public void set2SingleShip(){
       
    }

}
