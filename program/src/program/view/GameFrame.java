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
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

        JButton readyButton = new JButton("Ready to play!");
        readyButton.setBackground(Color.LIGHT_GRAY);
        readyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        readyButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                System.out.print("Ready Button was pressed \n");
                // todo ready to play function
            }
        });
        JButton deleteButton = new JButton("Delete Ship");
        deleteButton.setBackground(Color.LIGHT_GRAY);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                System.out.print("Delete Button was pressed \n");
                // todo delete ship function
            }
        });

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
        infoPanel.add(turnSign, c);
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
}