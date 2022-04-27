package program.view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

enum tileType {water,ship,unknown}

public class GameTile extends Tile{
    public tileType type;
    public boolean isHit;
    public GameTile(int xPosition, int yPosition){
        super(xPosition, yPosition);
        this.type = tileType.unknown;
        this.setBackground(Color.GRAY);
        this.isHit = false;
    }

    public void mouseClicked(MouseEvent e){
        System.out.print("game tile " + this.tileName + " was clicked \n");
        // this.setBackground(Color.RED);
        // if(SwingUtilities.isRightMouseButton(e)){
        //     this.set2EndShip(ShipEndType.right);
        // }
        // else{
        //     // this.set2EndShip(ShipEndType.left);
        // }


        this.set2SingleShip();
        
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
        this.type = tileType.water;
        // this.setBackground(Color.BLUE);
        this.setIcon(new ImageIcon("pics/water.png"));
    }

    public void set2MiddleShip(boolean vertically){
        this.type = tileType.water;
        // this.setBackground(new Color(165,42,42));
        if (vertically){          
            this.setIcon(new ImageIcon("pics/ship_middle_vertical.png"));
            
        }
        else{ 
            this.setIcon(new ImageIcon("pics/ship_middle_horizontal.png"));
        }
    }

    public void set2EndShip(ShipEndType type){
       switch (type){
           case upper:
           this.setIcon(new ImageIcon("pics/ship_upper_end.png"));
           break;

           case lower:
           this.setIcon(new ImageIcon("pics/ship_lower_end.png"));
           break;

           case left:
           this.setIcon(new ImageIcon("pics/ship_left_end.png"));
           break;

           case right:
           this.setIcon(new ImageIcon("pics/ship_right_end.png"));
           break;
       }
       
    }

    public void set2SingleShip(){
        this.setIcon(new ImageIcon("pics/single_ship.png"));
    }
    
}
