package program.model;

import javax.swing.*;

import program.view.GameFrame;
import program.controller.game.*;

import java.awt.*;

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
        else 
        if (prevY==startY){
            if(prevX>startX){
                tiles[startX][startY].set2EndShip(ShipEndType.left);
                tiles[prevX][prevY].set2EndShip(ShipEndType.right);
                for(int i=startX;i<=prevX;i++){
                    // tiles[i][startY].setEditable(false);
                }
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.right);
                tiles[prevX][prevY].set2EndShip(ShipEndType.left);
                for(int i=prevX;i<=startX;i++){
                    // tiles[i][startY].setEditable(false);
                }
            }
        }
        else if (prevX==startX){
            if(prevY>startY){
                tiles[startX][startY].set2EndShip(ShipEndType.upper);
                tiles[prevX][prevY].set2EndShip(ShipEndType.lower);
                for(int i=startY;i<=prevY;i++){
                    // tiles[startX][i].setEditable(false);
                }
            }
            else{
                tiles[startX][startY].set2EndShip(ShipEndType.lower);
                tiles[prevX][prevY].set2EndShip(ShipEndType.upper);
                for(int i=startY;i<=prevY;i++){
                    // tiles[startX][i].setEditable(false);
                }
            }
        }
        
        // this.thisShipLength ++;
        // this.addTile2Ship(this.thisShipTiles, tiles[prevX][prevY]);

        // System.out.print("ship length: " + String.valueOf(this.thisShipLength) + "\n");
        boolean isSuccess = this.myFrame.placeShip(this.thisShipLength, this.startX, this.startY, this.prevX, this.prevY);

        if (isSuccess){
            for(int i=0; i<this.thisShipLength; i++){
                this.tiles[this.shipX[i]][this.shipY[i]].setEditable(false);
            }
            if(this.startX == this.prevX){
                this.tiles[this.startX][this.startY-1].setEditable(false);
                if(this.startY != 10){ this.tiles[this.startX][this.startY+1].setEditable(false); }
                this.tiles[this.prevX][this.prevY-1].setEditable(false);
                if(this.prevY != 10){ this.tiles[this.prevX][this.prevY+1].setEditable(false); }
                for(int i=0; i<this.thisShipLength; i++){
                    if(this.shipX[i] != 10){ this.tiles[this.shipX[i]+1][this.shipY[i]].setEditable(false); }
                    this.tiles[this.shipX[i]-1][this.shipY[i]].setEditable(false);
                }
            }
            else if(this.startY == this.prevY){
                this.tiles[this.startX-1][this.startY].setEditable(false);
                if(this.startX != 10){ this.tiles[this.startX+1][this.startY].setEditable(false); }
                this.tiles[this.prevX-1][this.prevY].setEditable(false);
                if(this.prevX != 10){ this.tiles[this.prevX+1][this.prevY].setEditable(false); }
                for(int i=0; i<this.thisShipLength; i++){
                    if(this.shipY[i] != 10){ this.tiles[this.shipX[i]][this.shipY[i]+1].setEditable(false); }
                    this.tiles[this.shipX[i]][this.shipY[i]-1].setEditable(false);
                }
            }
        }
        else{
            for(int i=0; i<this.thisShipLength; i++){
                this.tiles[this.shipX[i]][this.shipY[i]].set2Water();
                // System.out.print("tile type: " + String.valueOf(this.tiles[this.shipX[i]][this.shipY[i]].getType()) + "\n");
                this.tiles[this.shipX[i]][this.shipY[i]].setEditable(true);
                this.paintComponents(this.getGraphics());
                
            }
        }
    }

    public void gotHit(int x, int y){
        this.tiles[x][y].gotHit();
    }

    public void shoot(int x, int y){
        tileType enemyType = this.myFrame.shootEnemy(x, y);

        // if ship is destroyed, status already updated in upper levels
        if(tiles[x][y].getType() == tileType.unknown){
            switch(enemyType){
                case water:
                this.tiles[x][y].set2Water();
                if(this.tiles[x-1][y].getType() == tileType.ship){
                    if (this.tiles[x-2][y].getType() == tileType.ship) { this.tiles[x-1][y].set2EndShip(ShipEndType.right); }
                }
                if(this.tiles[x+1][y].getType() == tileType.ship){
                    if ((this.tiles[x+2][y].getType() == tileType.ship) && (x!=10)) { this.tiles[x+1][y].set2EndShip(ShipEndType.left); }
                }
                if(this.tiles[x][y-1].getType() == tileType.ship){
                    if (this.tiles[x][y-2].getType() == tileType.ship) { this.tiles[x][y-1].set2EndShip(ShipEndType.lower); }
                }
                if(this.tiles[x][y+1].getType() == tileType.ship){
                    if ((this.tiles[x][y+2].getType() == tileType.ship) && (x!=10)) { this.tiles[x][y+1].set2EndShip(ShipEndType.upper); }
                }
                break;
                    
                case ship:
                this.tiles[x][y].set2SingleShip();

                if(this.tiles[x-1][y].getType() == tileType.ship){
                    if(x==1){
                        this.tiles[x][y].set2EndShip(ShipEndType.right);
                    }
                    else if(x==10){
                        this.tiles[x][y].set2EndShip(ShipEndType.left);
                    }
                    else{
                        this.tiles[x][y].set2MiddleShip(false);
                    }
                    if (this.tiles[x-1][y].isSingleShip() || (x-1 == 1)) { 
                        if(this.tiles[x-2][y].getType() != tileType.water  && (x-1 != 1)){
                            this.tiles[x-1][y].set2MiddleShip(false); 
                        }
                        else{
                            this.tiles[x-1][y].set2EndShip(ShipEndType.left);
                        }
                    }
                }
                if(this.tiles[x+1][y].getType() == tileType.ship){
                    if(x==1){
                        this.tiles[x][y].set2EndShip(ShipEndType.left);
                    }
                    else if(x==10){
                        this.tiles[x][y].set2EndShip(ShipEndType.right);
                    }
                    else{
                        this.tiles[x][y].set2MiddleShip(false);
                    }
                    if (this.tiles[x+1][y].isSingleShip() || (x+1 == 10)) { 
                        if(this.tiles[x+2][y].getType() != tileType.water  && (x+1 != 10)){
                            this.tiles[x+1][y].set2MiddleShip(false); 
                        }
                        else{
                            this.tiles[x+1][y].set2EndShip(ShipEndType.right);
                        }
                    }
                }
                if(this.tiles[x][y-1].getType() == tileType.ship){
                    if(y==1){
                        this.tiles[x][y].set2EndShip(ShipEndType.upper);
                    }
                    else if(y==10){
                        this.tiles[x][y].set2EndShip(ShipEndType.lower);
                    }
                    else{
                        this.tiles[x][y].set2MiddleShip(true);
                    }
                    if (this.tiles[x][y-1].isSingleShip() || (y-1 == 1)) {
                        if (this.tiles[x][y-2].getType() != tileType.water  && (y-1 != 1)) { 
                            this.tiles[x][y-1].set2MiddleShip(true); 
                        }
                        else{
                            this.tiles[x][y-1].set2EndShip(ShipEndType.upper);
                        }
                    }
                }
                if(this.tiles[x][y+1].getType() == tileType.ship){
                    if(y==1){
                        this.tiles[x][y].set2EndShip(ShipEndType.lower);
                    }
                    else if(y==10){
                        this.tiles[x][y].set2EndShip(ShipEndType.upper);
                    }
                    else{
                        this.tiles[x][y].set2MiddleShip(true);
                    }
                    if (this.tiles[x][y+1].isSingleShip() || (y+1 == 10)) { 
                        if (this.tiles[x][y+2].getType() != tileType.water  && (y+1 != 10)) { 
                            this.tiles[x][y+1].set2MiddleShip(true); 
                        }
                        else{
                            this.tiles[x][y+1].set2EndShip(ShipEndType.lower);
                        }
                    }
                }
                break;
                
                default:
                break;
            }
        }
    }

    private int[] addCoordinate(int[] ship, int n, int tile){
        int[] newShip = new int[n+1];
        for (int i = 0; i<n;i++){
            newShip[i]=ship[i];
            // System.out.print(String.valueOf(newShip[i]) + "\n");
        }
        newShip[n]=tile;
        // System.out.print(String.valueOf(newShip[n]) + "\n");
        // System.out.print("added tile (" + String.valueOf(tile.x) + "," + String.valueOf(tile.y) + ") to the ship \n");
        // System.out.print("length is " + String.valueOf(n+1) + "\n");
        
        return newShip;
    }

    public void set2ShipSetup(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setEditable(true);
            }
        }
    }

    public void setMyTurn(boolean isMyTurn){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setMyTurn(isMyTurn);
            }
        }
    }

    public void set2Hitable(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setHitable(true);
            }
        }
    }

    public void set2Shootable(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j].setShootable(true);
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
        if(ship != null){
            int length = ship.length;

            for(int i = 0; i<length; i++){
                this.tiles[ship[i][0]][ship[i][1]].set2Water();
                this.tiles[ship[i][0]][ship[i][1]].setEditable(true);

                boolean set2false;

                set2false=true;
                if(ship[i][0] != 10){ 
                    if(ship[i][0]+1 != 10){ if(this.tiles[ship[i][0]+2][ship[i][1]].getType() == tileType.ship) { set2false = false; } }
                    if (ship[i][1] != 10){ if(this.tiles[ship[i][0]+1][ship[i][1]+1].getType() == tileType.ship) { set2false = false; } }
                    if(this.tiles[ship[i][0]+1][ship[i][1]-1].getType() == tileType.ship) { set2false = false; }
                    if(set2false){ this.tiles[ship[i][0]+1][ship[i][1]].setEditable(true); }
                }

                set2false=true;
                if(this.tiles[ship[i][0]-2][ship[i][1]].getType() == tileType.ship) { set2false = false; }
                if(ship[i][1] != 10){ if(this.tiles[ship[i][0]-1][ship[i][1]+1].getType() == tileType.ship) { set2false = false; } }
                if(this.tiles[ship[i][0]-1][ship[i][1]-1].getType() == tileType.ship) { set2false = false; }
                if(set2false){ this.tiles[ship[i][0]-1][ship[i][1]].setEditable(true); }

                set2false=true;
                if(ship[i][1] != 10){ 
                    if(ship[i][1]+1 != 10){ if(this.tiles[ship[i][0]][ship[i][1]+2].getType() == tileType.ship) { set2false = false; } }
                    if (ship[i][0] != 10){ if(this.tiles[ship[i][0]+1][ship[i][1]+1].getType() == tileType.ship) { set2false = false; } }
                    if(this.tiles[ship[i][0]-1][ship[i][1]+1].getType() == tileType.ship) { set2false = false; }
                    if(set2false){ this.tiles[ship[i][0]][ship[i][1]+1].setEditable(true); }
                }

                set2false=true;
                if(this.tiles[ship[i][0]][ship[i][1]-2].getType() == tileType.ship) { set2false = false; }
                if(ship[i][0] != 10){ if(this.tiles[ship[i][0]+1][ship[i][1]-1].getType() == tileType.ship) { set2false = false; } }
                if(this.tiles[ship[i][0]-1][ship[i][1]-1].getType() == tileType.ship) { set2false = false; }
                if(set2false){ this.tiles[ship[i][0]][ship[i][1]-1].setEditable(true); }
            }

            this.clear2Delete();
        }
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

    public void setHit(int x, int y){
        this.tiles[x][y].setHit(true);
    }

    public boolean isHit(int x, int y){
        return this.tiles[x][y].isHit();
    }

    public void endEnemyShip(int x, int y, int length, Orient or){
        if(length==1){
            this.tiles[x][y].set2SingleShip();
            this.tiles[x][y].setShootable(false);
        }
        else{
            if (or == Orient.HORIZONTAL){
                for(int i=0; i<length;i++){
                    this.tiles[x+i][y].set2MiddleShip(false);
                    if(y!=1){this.tiles[x+i][y-1].set2Water(); this.tiles[x+i][y-1].setShootable(false);}
                    if(y!=10){this.tiles[x+i][y+1].set2Water(); this.tiles[x+i][y+1]. setShootable(false);}
                }
                this.tiles[x][y].set2EndShip(ShipEndType.left);
                this.tiles[x+length-1][y].set2EndShip(ShipEndType.right);

                if(x!=1){this.tiles[x-1][y].set2Water(); this.tiles[x-1][y].setShootable(false);}
                if(x+length<11){this.tiles[x+length][y].set2Water(); this.tiles[x+length][y].setShootable(false);}
            }
            else{
                for(int i=0; i<length;i++){
                    this.tiles[x][y+i].set2MiddleShip(true);
                    if(x!=1){this.tiles[x-1][y+i].set2Water(); this.tiles[x-1][y+i].setShootable(false);}
                    if(x!=10){this.tiles[x+1][y+i].set2Water(); this.tiles[x+1][y+i].setShootable(false);}
                }
                this.tiles[x][y].set2EndShip(ShipEndType.upper);
                this.tiles[x][y+length-1].set2EndShip(ShipEndType.lower);

                if(y!=1){this.tiles[x][y-1].set2Water(); this.tiles[x][y-1].setShootable(false);}
                if(y+length<11){this.tiles[x][y+length].set2Water(); this.tiles[x][y+length].setShootable(false);}
            }
        }
    }
}
