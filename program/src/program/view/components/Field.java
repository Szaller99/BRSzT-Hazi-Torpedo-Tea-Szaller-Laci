package program.view.components;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import program.view.GameFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JComponent {
    private boolean isUpper;
    private boolean isLower;
    private int startX;
    private int startY;
    private int prevX;
    private int prevY;
    private int thisShipLength;
    private int[] shipX;
    private int[] shipY;
    public Tile[][] tiles;
    private GameFrame myFrame;

    public Field(boolean isUpper, GameFrame myFrame) {
        super();
        this.isUpper = isUpper;
        this.isLower = !isUpper;
        this.myFrame = myFrame;
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
                    tiles[i][j].setEditable(true); // for testing
                }
                else{
                    tiles[i][j].setShootable(true); // for testing
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


    public void testMessage(){
        System.out.print("test\n");
    }
    
    public void startShip(int x, int y){
        this.startX = x;
        this.startY = y;
        tiles[x][y].set2SingleShip();

        this.prevX = x;
        this.prevY = y;

        this.thisShipLength = 1;
        this.shipX = new int[1];
        this.shipX[0] = x;
        this.shipY = new int[1];
        this.shipY[0] = y;
    }


    // todo: ship exeptions

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
            this.prevX = x;
            this.prevY = y;

            this.shipX = this.addCoordinate(this.shipX, this.thisShipLength, x);
            this.shipY = this.addCoordinate(this.shipY, this.thisShipLength, y);
            this.thisShipLength ++;
            // this.thisShipTiles = this.addTile2Ship(this.thisShipTiles, this.thisShipLength, tiles[x][y]);
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
            this.prevX = x;
            this.prevY = y;

            
            // this.thisShipTiles = this.addTile2Ship(this.thisShipTiles, this.thisShipLength, tiles[x][y]);
            this.shipX = this.addCoordinate(this.shipX, this.thisShipLength, x);
            this.shipY = this.addCoordinate(this.shipY, this.thisShipLength, y);
            this.thisShipLength ++;
        }

       
        
    }

    public void endShip(){
        if((prevY==startY) && (prevX==startX)){
            tiles[prevX][prevY].set2SingleShip();
        }
        else if (prevY==startY){
            if(prevX>startX){
                tiles[startX][startY].set2EndShip(ShipEndType.left);
                tiles[prevX][prevY].set2EndShip(ShipEndType.right);
                for(int i=startX;i<=prevX;i++){
                    tiles[i][startY].setEditable(false);
                }
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.right);
                tiles[prevX][prevY].set2EndShip(ShipEndType.left);
                for(int i=prevX;i<=startX;i++){
                    tiles[i][startY].setEditable(false);
                }
            }
        }
        else if (prevX==startX){
            if(prevY>startY){
                tiles[startX][startY].set2EndShip(ShipEndType.upper);
                tiles[prevX][prevY].set2EndShip(ShipEndType.lower);
                for(int i=startY;i<=prevY;i++){
                    tiles[startX][i].setEditable(false);
                }
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.lower);
                tiles[prevX][prevY].set2EndShip(ShipEndType.upper);
                for(int i=startY;i<=prevY;i++){
                    tiles[startX][i].setEditable(false);
                }
            }
        }
        
        // this.thisShipLength ++;
        // this.addTile2Ship(this.thisShipTiles, tiles[prevX][prevY]);

        System.out.print("ship length: " + String.valueOf(this.thisShipLength) + "\n");
        boolean isSuccess = this.myFrame.placeShip(this.thisShipLength, this.startX, this.startY, this.prevX, this.prevY);

        if (isSuccess){
            // todo: set fields around to not editable
        }
        else{
            // todo: set ship fields to water
            for(int i=0; i<this.thisShipLength; i++){
                tiles[this.shipX[i]][this.shipY[i]].set2Water();
                tiles[this.shipX[i]][this.shipY[i]].setEditable(true);
            }
        }
    }

    public void gotHit(int x, int y){
        tiles[x][y].gotHit();
    }

    public tileType shoot(int x, int y){
        return this.myFrame.shootEnemy(x, y);
    }

    private int[] addCoordinate(int[] ship, int n, int tile){
        int[] newShip = new int[n+1];
        for (int i = 0; i<n;i++){
            newShip[i]=ship[i];
            System.out.print(String.valueOf(newShip[i]) + "\n");
        }
        newShip[n]=tile;
        System.out.print(String.valueOf(newShip[n]) + "\n");
        // System.out.print("added tile (" + String.valueOf(tile.x) + "," + String.valueOf(tile.y) + ") to the ship \n");
        System.out.print("length is " + String.valueOf(n+1) + "\n");
        
        return newShip;
    }

    public void set2ShipSetup(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setEditable(true); // for testing
            }
        }
    }

    public void set2Hitable(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setHitable(true); // for testing
            }
        }
    }

    public void set2Shootable(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setShootable(true); // for testing
            }
        }
    }

    public void set2Delete(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setToDelete(true);
            }
        }
    }

    public void clear2Delete(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setToDelete(false);
            }
        }
    }

    public void deleteShip(int x, int y){
        int[][] ship = this.myFrame.deleteShip(x, y);
        int length = ship.length;

        for(int i = 0; i<length; i++){
            tiles[ship[i][0]][ship[i][1]].set2Water();
            tiles[ship[i][0]][ship[i][1]].setEditable(true);
        }

        this.clear2Delete();
    }

    public void clearAllStatus(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setToDelete(false);
                tiles[i][j].setEditable(false);
                tiles[i][j].setHitable(false);
                tiles[i][j].setShootable(false);
            }
        }
    }
}
