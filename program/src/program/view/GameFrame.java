package program.view;

import program.controller.Controller;
import program.view.components.Field;
import program.view.components.Frame;

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

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1,1,5,5));
        infoPanel.setBackground(new Color(30,30,100));


        gameFrameContainer.setLayout(new GridLayout(1,2, 10, 10));
        gameFrameContainer.add(gamePanel);
        gameFrameContainer.add(infoPanel);
        this.pack();
        this.setSize(600,650);
        
    }
}