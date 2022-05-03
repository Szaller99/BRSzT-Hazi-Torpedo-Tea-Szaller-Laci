package program.view.components;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JComponent {
    private boolean isUpper;
    private boolean isLower;
    private boolean isEditable;
    private int startX;
    private int startY;
    private int prevX;
    private int prevY;
    public Tile[][] tiles;

    public Field(boolean isUpper) {
        super();
        this.isUpper = isUpper;
        this.isLower = !isUpper;
        this.isEditable = false;
        setupField();
    }

    public Tile[][] getHostTiles() {
        return this.tiles;
    }

    private void setupField() {

        this.setBounds(20,200,260,260);
        this.setVisible(true);

        if (this.isUpper) {
            JLabel FieldSign = new JLabel("Enemy's Field:");
            FieldSign.setVisible(true);
            FieldSign.setForeground(Color.BLACK);
            FieldSign.setFont(new Font("Arial", Font.BOLD, 14));
            FieldSign.setBounds(30, 25, 240, 20);
            this.add(FieldSign);
            this.setBackground(new Color(180,80,80));
        }
        else {
            JLabel FieldSign = new JLabel("My Field:");
            FieldSign.setVisible(true);
            FieldSign.setForeground(Color.BLACK);
            FieldSign.setFont(new Font("Arial", Font.BOLD, 14));
            FieldSign.setBounds(30, 25, 240, 20);
            this.add(FieldSign);
            this.setBackground(new Color(80,150,80));
        }

        this.paintComponents(this.getGraphics());
        
       
        this.tiles = new Tile[11][11];

        for (int i=0; i<= 10; i++) {
            tiles[0][i] = new LegendTile(0,i,String.valueOf(i));
            this.add(tiles[0][i]);
        }

        for (int i=0; i<= 10; i++) {
            tiles[i][0] = new LegendTile(i,0,String.valueOf(i));
            this.add(tiles[i][0]);
        }

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j] = new GameTile(i, j, this);
                this.add(tiles[i][j]);
                if(isLower){
                    tiles[i][j].set2Water();
                }
            }
        }

        this.paintComponents(this.getGraphics());
        // this.paint(this.getGraphics());

    }

    public void paint(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(20,20,240, 265);
        // g.clearRect(30,30,220,220);
        this.paintComponents(g);
    }

    public void setEditable(){
        this.isEditable = true;
    }

    public void clearEditable(){
        this.isEditable = false;
    }

    public void testMessage(){
        System.out.print("test\n");
    }
    
    public void startShip(int x, int y){
        this.startX = x;
        this.startY = y;
        tiles[x][y].set2SingleShip();

        this.prevX = x;
        this.prevY = y;
    }

    public void continueShip(int x, int y){
        if (y==startY){
            if(x>startX){
                tiles[startX][startY].set2EndShip(ShipEndType.left);
                tiles[x][y].set2MiddleShip(false);
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.right);
                tiles[x][y].set2MiddleShip(false);
            }
        }
        else if (x==startX){
            if(y>startY){
                tiles[startX][startY].set2EndShip(ShipEndType.upper);
                tiles[x][y].set2MiddleShip(true);
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.lower);
                tiles[x][y].set2MiddleShip(true);
            }
        }

        this.prevX = x;
        this.prevY = y;
    }

    public void endShip(){
        if((prevY==startY) && (prevX==startX)){
            tiles[prevX][prevY].set2SingleShip();
        }
        else if (prevY==startY){
            if(prevX>startX){
                tiles[startX][startY].set2EndShip(ShipEndType.left);
                tiles[prevX][prevY].set2EndShip(ShipEndType.right);
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.right);
                tiles[prevX][prevY].set2EndShip(ShipEndType.left);
            }
        }
        else if (prevX==startX){
            if(prevY>startY){
                tiles[startX][startY].set2EndShip(ShipEndType.upper);
                tiles[prevX][prevY].set2EndShip(ShipEndType.lower);
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.lower);
                tiles[prevX][prevY].set2EndShip(ShipEndType.upper);
            }
        }    
    }
}
