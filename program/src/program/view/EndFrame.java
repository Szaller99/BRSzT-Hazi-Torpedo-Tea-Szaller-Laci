package program.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.*;

import program.controller.Controller;

import java.awt.*;

public class EndFrame extends JFrame {
    private Controller app;
    private JLabel title;
    private JLabel text;
    
    public EndFrame(Controller app, Boolean winner) {
        super();
        this.app = app;

        String outText;
        if(winner){
            outText = "You won! Congratulations!";
        }
        else{
            outText = "You lost...";
        }

        this.setVisible(true);
        this.setSize(600, 300);
        this.setResizable(false);
        this.setTitle("Game ended");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container endFrameContainer = this.getContentPane();
        endFrameContainer.setBackground(new Color(60,70,60));

        this.title = new JLabel("Game ended", SwingConstants.CENTER);
        this.title.setForeground(Color.RED);
        this.title.setVisible(true);
        this.title.setFont(new Font("Arial", Font.BOLD, 20));

        this.text = new JLabel(outText, SwingConstants.CENTER);
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
