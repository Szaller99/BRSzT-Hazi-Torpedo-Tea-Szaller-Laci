package program.view;

import program.controller.Controller;
import program.view.components.Field;
import program.view.components.Frame;
import program.view.components.ShipsInfo;
import program.view.components.TurnSign;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  

import javax.swing.*;
import java.awt.*;

public class GameFrame extends Frame {
    private Controller app;
    public GameFrame(Controller app) {
        super(app);
        this.app = app;
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

        Field myField = new Field(false);
        Field enemyField = new Field(true);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,1,5,5));
        gamePanel.setBackground(new Color(30,30,30));

        gamePanel.add(enemyField); // place fields based on order
        gamePanel.add(myField);
        this.paintComponents(this.getGraphics());

        ShipsInfo myShips = new ShipsInfo(false);
        ShipsInfo enemyShips = new ShipsInfo(true);
        TurnSign turnSign = new TurnSign();
        turnSign.ShipSetup(); // for testing

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(30,30,100));
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 240;
        c.weighty = 230;
        c.ipadx = 35;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(enemyShips, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 240;
        c.weighty = 50;
        c.ipadx = 35;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        infoPanel.add(turnSign, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 240;
        c.weighty = 230;
        c.ipadx = 35;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(myShips, c);

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
}