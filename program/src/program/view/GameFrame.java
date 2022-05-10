package program.view;

import program.controller.Controller;
import program.view.components.Frame;
import program.view.components.*;
import program.game.*;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends Frame {
    private Controller app;
    private Game myGame;
    public TurnSign turnSign;
    public Field myField;
    public Field enemyField;
    public ShipsInfo myShips;
    public ShipsInfo enemyShips;
    private ReadyButton readyButton;
    private DeleteButton deleteButton;
    public GameFrame(Controller app, Game myGame) {
        super(app);
        this.app = app;
        this.myGame = myGame;
        this.setVisible(false);
        setupGameFrame();
    }

    private void setupGameFrame(){
        this.setupFrame(
                new Dimension(600, 600),
                "Battleships Game",
                false,
                true );
        Container gameFrameContainer = this.getContentPane();
        gameFrameContainer.setBackground(new Color(00,00,00));

        this.myField = new Field(false, this);
        this.enemyField = new Field(true, this);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,1,5,5));
        gamePanel.setBackground(new Color(30,30,30));

        gamePanel.add(this.enemyField); // place fields based on order
        gamePanel.add(this.myField);
        this.paintComponents(this.getGraphics());

        this.myShips = new ShipsInfo(false);
        this.enemyShips = new ShipsInfo(true);
        this.turnSign = new TurnSign();

        this.readyButton = new ReadyButton(this);
        this.deleteButton = new DeleteButton(myField, this.turnSign);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(30,30,100));
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 240;
        c.weighty = 230;
        c.ipadx = 35;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(enemyShips, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 240;
        c.weighty = 50;
        c.ipadx = 5;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        infoPanel.add(this.turnSign, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 240;
        c.weighty = 230;
        c.ipadx = 35;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(myShips, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 100;
        c.weighty = 20;
        c.ipadx = 35;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(deleteButton, c);
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 100;
        c.weighty = 20;
        c.ipadx = 35;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(readyButton, c);

        // infoPanel.add(enemyShips); // place fields based on order
        // infoPanel.add(turnSign);
        // infoPanel.add(myShips);
        this.paintComponents(this.getGraphics());

        gameFrameContainer.setLayout(new GridLayout(1,2, 10, 10));
        gameFrameContainer.add(gamePanel);
        gameFrameContainer.add(infoPanel);
        this.pack();
        this.setSize(600,650);
        
    }

    public tileType shootEnemy(int x, int y){
        return this.myGame.shootEnemy(x, y);
    }

    public int[][] deleteShip(int x, int y){
        int[][] ship = this.myGame.deleteShip(x, y);
        if(ship!=null){
            this.turnSign.ShipSetup();
            this.myShips.showShip(ship.length);
        }
        // todo: show again the ship
        return ship;   
    }

    public void ready2Play(){
        boolean canBeReady = this.myGame.ready2Play();
        if (canBeReady){
            // this.myField.clearAllStatus();
            // this.enemyField.clearAllStatus();
            // this.turnSign.Done();
            this.readyButton.setVisible(false);
            this.deleteButton.setVisible(false);
        }
        else{
            this.turnSign.NotDone();
        }
    }

    public boolean placeShip(int len, int xStart, int yStart, int xEnd, int yEnd){

        boolean isSuccess = this.myGame.placeShip(len, xStart, yStart, xEnd, yEnd);
        this.myShips.hideShip(len);
        this.turnSign.ShipSetup();
        return isSuccess;
    }

    public void setHit(int x, int y){
        this.enemyField.setHit(x, y);
    }

    public void gotHit(int x, int y){
        this.myField.gotHit(x, y);
    }

    public boolean isHit(int x, int y){
        return this.enemyField.isHit(x, y);
    }

    public void endEnemyShip(int x, int y, int length, Orient or){
        System.out.print("Ship destroyed \n");
        this.enemyField.endEnemyShip(x, y, length, or);
    }

    public void startFight(){
        this.enemyField.set2Shootable();
        this.myField.set2Hitable();
        this.myShips.showAllShips();
    }

    public void set2setup(){
        this.enemyField.clearAllStatus();
        this.myField.set2ShipSetup();
        this.myField.setMyTurn(true);
        this.turnSign.ShipSetup();
    }

    public void set2ready(){
        this.myField.clearAllStatus();
        this.enemyField.clearAllStatus();
        this.myField.setMyTurn(false);
        this.enemyField.setMyTurn(false);
        this.enemyField.set2Shootable();
        this.myField.set2Hitable();
        this.turnSign.Done();
    }

    public void set2myTurn(){
        this.enemyField.setMyTurn(true);
        this.myField.setMyTurn(false);
        this.turnSign.YourTurn();
    }

    public void set2enemyTurn(){
        this.enemyField.setMyTurn(false);
        this.myField.setMyTurn(true);
        this.turnSign.EnemysTurn();
    }
}