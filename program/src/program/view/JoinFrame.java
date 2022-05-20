package program.view;

import program.controller.communication.Communication;
import program.controller.Controller;
import program.model.*;

import javax.swing.*;

import java.awt.*;


public class JoinFrame extends JFrame{
    private Controller app;
    private JoinGameButton joinGame;
    private JTextField IP;

    public JoinFrame(Controller app) {
        super();
        this.app = app;
        this.setupWelcomeFrame();
    }

    private void setupWelcomeFrame() {
        this.setVisible(true);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Join");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container createFrameContainer = this.getContentPane();
        createFrameContainer.setBackground(new Color(60,70,60));

        this.IP = new JTextField();
        this.joinGame = new JoinGameButton(this);

        createFrameContainer.setLayout(new GridLayout(2,1, 10, 10));
        createFrameContainer.add(IP);
        createFrameContainer.add(joinGame);
        this.paintComponents(this.getGraphics());
    }

    public void joinGame(){
        String ip = this.IP.getText();
        this.app.join(ip);
    }
    
}
