package program.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;



public class GameTile extends Tile{
    public tileType type;
    private boolean isHit;
    private boolean isEditable; // is true if tile is on my field and player can place ship on this tile
    private boolean isHitable; // is true if tile is on my field and enemy can shoot this tile
    private boolean isShootable; // is true if tile is on enemy's field and player can shoot this tile
    private boolean isToDelete; // is true if user wants to delete a ship
    private boolean isMyTurn;
    public Field myField;
    private boolean isSingleShip;

    public GameTile(int xPosition, int yPosition, Field field){
        super(xPosition, yPosition);
        this.type = tileType.unknown;
        this.setBackground(Color.GRAY);
        this.setHit(false);
        this.setEditable(false);
        this.setHitable(false);
        this.setShootable(false);
        this.setSingleShip(false);
        this.setMyTurn(false);
        this.myField = field;
    }

    public boolean isSingleShip() {
        return isSingleShip;
    }

    public void setSingleShip(boolean isSingleShip) {
        this.isSingleShip = isSingleShip;
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

    public boolean isToDelete() {
        return isToDelete;
    }

    public void setToDelete(boolean isToDelete) {
        this.isToDelete = isToDelete;
    }

    public void setMyTurn(boolean isMyTurn){
        this.isMyTurn = isMyTurn;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void mouseClicked(MouseEvent e){
        // System.out.print("game tile " + this.tileName + " was clicked \n");
        if(this.isMyTurn()){
            if(this.isToDelete()){
                this.myField.deleteShip(this.x, this.y);
            }
            else{
            
                // if(this.isEditable()){
                //     this.set2SingleShip();
                //     this.setEditable(false);
                // }
                if(this.isShootable()){
                    System.out.print("shoot! \n");
                    myField.shoot(this.x, this.y);
                    this.setShootable(false);
                    // tileType enemyType = myField.shoot(this.x, this.y);
                    // switch(enemyType){
                    //     case water:
                    //     this.set2Water();
                    //     break;
                        
                    //     case ship:
                    //     this.set2SingleShip(); // todo
                    //     break;
                        
                    //     default:
                    //         break;
                    // }
                }
            }

            // if(this.getEditable()){
            //     // this.setBackground(Color.RED);

            
            // this.set2SingleShip();
        
            // }

            // myField.testMessage();
            // System.out.print(this.getParent());
        }

        
    }
    public void mousePressed(MouseEvent e){
        // System.out.print("game tile " + this.tileName + " was pressed \n");
        if(this.isMyTurn()){
            if(!this.isToDelete()){
                if (this.isEditable()){
                    myField.startShip(this.x,this.y);
                }
            }
        }
    }
    
    public void mouseReleased(MouseEvent e){
        if(this.isMyTurn()){
            if(!this.isToDelete()){
                if (this.isEditable()){
                    this.setEditable(false);
                    myField.endShip();   
                }
            }
        }
    }
    public void mouseEntered(MouseEvent e){
        if(this.isMyTurn()){
            if(!this.isToDelete()){
                if(e.getModifiersEx()!=0 && this.isEditable()){
                    myField.continueShip(this.x,this.y);
                    
                }
            }
        }
    }
    public void mouseExited(MouseEvent e){
        if(this.isMyTurn()){
            if(!this.isToDelete()){
                // this.gotHit();
                if(e.getModifiersEx()!=0){
                    // this.setEditable(false);
                }
            }
        }
    }

    public void set2Water(){
        this.type = tileType.water;
        // this.setBackground(Color.BLUE);
        this.setIcon(new ImageIcon("pics/water.png"));
        this.type = tileType.water;
        this.setSingleShip(false);
        // System.out.print("game tile (" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + ") set to: " + String.valueOf(this.isSingleShip()) + "\n");
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
        this.setSingleShip(false);
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
       this.setSingleShip(false);
       
    }

    public void set2SingleShip(){
        this.type = tileType.ship;
        this.setIcon(new ImageIcon("pics/single_ship.png"));
        this.setSingleShip(true);
        // System.out.print("game tile (" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + ") set to: " + String.valueOf(this.isSingleShip()) + "\n");
    }

    public void gotHit(){
        if(this.isMyTurn()){
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

    public tileType getType(){
        return this.type;
    }
    
}
