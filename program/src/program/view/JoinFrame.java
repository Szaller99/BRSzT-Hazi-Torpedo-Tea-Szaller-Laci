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
    private JLabel title;

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

        this.title = new JLabel("Enter the host's IP",SwingConstants.CENTER);
        this.title.setVerticalAlignment(SwingConstants.CENTER);
        this.title.setFont(new Font("Arial", Font.BOLD, 25));
        this.title.setForeground(new Color(255,0,0));
        this.title.setBounds(0, 0, 600, 100);

        this.IP = new JTextField();
        this.IP.setBounds(200, 150, 200, 30);
        this.IP.setBackground(new Color(200,200,200));
        this.IP.setForeground(Color.BLACK);

        this.joinGame = new JoinGameButton(this);

        createFrameContainer.setLayout(null);
        createFrameContainer.add(title);
        createFrameContainer.add(IP);
        createFrameContainer.add(joinGame);
        this.paintComponents(this.getGraphics());
    }

    public void joinGame(){
        String ip = this.IP.getText();
        this.app.join(ip);
    }
    
}
