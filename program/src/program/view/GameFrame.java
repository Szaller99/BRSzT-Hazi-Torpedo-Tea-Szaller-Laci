package program.view;

import program.controller.Controller;
import program.game.Game;
import program.view.components.Frame;
import program.view.components.*;

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
    public GameFrame(Controller app, Game myGame) {
        super(app);
        this.app = app;
        this.myGame = myGame;
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

        ShipsInfo myShips = new ShipsInfo(false);
        ShipsInfo enemyShips = new ShipsInfo(true);
        this.turnSign = new TurnSign();
        this.turnSign.ShipSetup(); // for testing

        ReadyButton readyButton = new ReadyButton(this);
        DeleteButton deleteButton = new DeleteButton(myField, this.turnSign);

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
        // todo: got the type of enemy's tile in x,y

        return this.myGame.shootEnemy(x, y);
    }

    public int[][] deleteShip(int x, int y){
        this.turnSign.ShipSetup();
        return this.myGame.deleteShip(x, y);     
    }

    public void ready2Play(){
        boolean canBeReady = this.myGame.ready2Play();
        if (canBeReady){
            this.myField.clearAllStatus();
            this.enemyField.clearAllStatus();
        }
        else{
            this.turnSign.NotDone();
        }
    }
}