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
    private boolean isHit;
    private boolean isEditable; // is true if tile is on my field and player can place ship on this tile
    private boolean isHitable; // is true if tile is on my field and enemy can shoot this tile
    private boolean isShootable; // is trie if tile is on enemy's field and player can shoot this tile
    public Field myField;
    public GameTile(int xPosition, int yPosition, Field field){
        super(xPosition, yPosition);
        this.type = tileType.unknown;
        this.setBackground(Color.GRAY);
        this.setHit(false);
        this.setEditable(false);
        this.setHitable(false);
        this.setShootable(false);
        this.myField = field;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public boolean isHitable() {
        return isHitable;
    }

    public void setHitable(boolean isHitable) {
        this.isHitable = isHitable;
    }

    public boolean isShootable() {
        return isShootable;
    }

    public void setShootable(boolean isShootable) {
        this.isShootable = isShootable;
    }

    public void mouseClicked(MouseEvent e){
        // System.out.print("game tile " + this.tileName + " was clicked \n");

        if(this.isEditable()){
            this.set2SingleShip();
            this.setEditable(false);
        }
        if(this.isShootable()){
            tileType enemyType = myField.shoot(this.x, this.y);
            switch(enemyType){
                case water:
                this.set2Water();
                break;
                
                case ship:
                this.set2SingleShip();
                break;
                
                default:
                    break;
            }
        }

        // if(this.getEditable()){
        //     // this.setBackground(Color.RED);

        
        // this.set2SingleShip();
       
        // }

        // myField.testMessage();
        // System.out.print(this.getParent());

        
    }
    public void mousePressed(MouseEvent e){
        // System.out.print("game tile " + this.tileName + " was pressed \n");
        if (this.isEditable()){
            myField.startShip(this.x,this.y);
        }
    }
    
    public void mouseReleased(MouseEvent e){
        if (this.isEditable()){
            myField.endShip();
            this.setEditable(false);
        }
        if(this.isShootable()){
            tileType enemyType = myField.shoot(this.x, this.y);
            switch(enemyType){
                case water:
                this.set2Water();
                break;
                
                case ship:
                this.set2SingleShip();
                break;
                
                default:
                    break;
            }
        }
    }
    public void mouseEntered(MouseEvent e){
        if(e.getModifiersEx()!=0 && this.isEditable()){
            myField.continueShip(this.x,this.y);
            
        }
    }
    public void mouseExited(MouseEvent e){
        // this.gotHit();
        if(e.getModifiersEx()!=0){
            // this.setEditable(false);
        }
    }

    public void set2Water(){
        this.type = tileType.water;
        // this.setBackground(Color.BLUE);
        this.setIcon(new ImageIcon("pics/water.png"));
        this.type = tileType.water;
    }

    public void set2MiddleShip(boolean vertically){
        this.type = tileType.ship;
        // this.setBackground(new Color(165,42,42));
        if (vertically){          
            this.setIcon(new ImageIcon("pics/ship_middle_vertical.png"));
            
            
        }
        else{ 
            this.setIcon(new ImageIcon("pics/ship_middle_horizontal.png"));
        }
    }

    public void set2EndShip(ShipEndType type){
        this.type = tileType.ship;
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
        this.type = tileType.ship;
        this.setIcon(new ImageIcon("pics/single_ship.png"));
    }

    public void gotHit(){
        if(this.isHitable()){
            this.setHit(true);
            if (this.type == tileType.ship){
                this.setIcon(new ImageIcon("pics/ship_hit.png"));
            }
            else if (this.type == tileType.water){
                this.setIcon(new ImageIcon("pics/water_hit2.png"));
            }
            this.setHitable(false);
        }
          
    }
    
}
