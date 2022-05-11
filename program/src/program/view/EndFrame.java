package program.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;


import javax.swing.JLabel;
import javax.swing.SwingConstants;

import program.controller.Controller;
import program.view.components.Frame;

import java.awt.*;

public class EndFrame extends Frame {
    private Controller app;
    private JLabel title;
    private JLabel text;
    
    public EndFrame(Controller app, String winner) {
        super(app);
        this.app = app;

        this.setupFrame(new Dimension(600,300),"Game ended",false,true);
        Container endFrameContainer = this.getContentPane();
        endFrameContainer.setBackground(new Color(60,70,60));

        this.title = new JLabel("Game ended", SwingConstants.CENTER);
        this.title.setForeground(Color.RED);
        this.title.setVisible(true);
        this.title.setFont(new Font("Arial", Font.BOLD, 20));

        this.text = new JLabel(winner, SwingConstants.CENTER);
        this.text.setForeground(Color.ORANGE);
        this.text.setVisible(true);
        this.text.setFont(new Font("Arial", Font.BOLD, 16));

        endFrameContainer.setLayout(new GridLayout(2,1,20,20));
        endFrameContainer.add(title);
        endFrameContainer.add(text);

        //TODO egyéb információk kiírása

        this.paintComponents(this.getGraphics());
    }
}
