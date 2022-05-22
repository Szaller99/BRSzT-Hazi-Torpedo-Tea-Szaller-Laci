package program.view;

import program.controller.communication.Communication;
import program.controller.Controller;
import program.model.*;

import javax.swing.*;

import java.awt.*;


public class CreateFrame extends JFrame{
    private Controller app;
    private CreateGameButton createGame;
    private IPSign ipSign;
    private JLabel title;
    public CreateFrame(Controller app) {
        super();
        this.app = app;
        this.setupWelcomeFrame();
    }

    private void setupWelcomeFrame() {
        this.setVisible(true);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Create");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container createFrameContainer = this.getContentPane();
        createFrameContainer.setBackground(new Color(60,70,60));

        this.ipSign = new IPSign();
        this.createGame = new CreateGameButton(this);
        this.title = new JLabel("",SwingConstants.CENTER);
        this.title.setVerticalAlignment(SwingConstants.CENTER);
        this.title.setFont(new Font("Arial", Font.BOLD, 25));
        this.title.setForeground(new Color(255,0,0));
        this.title.setBounds(0, 0, 600, 100);

        createFrameContainer.setLayout(null);
        createFrameContainer.add(createGame);
        createFrameContainer.add(ipSign);
        createFrameContainer.add(title);
        this.paintComponents(this.getGraphics());
    
    }

    public void createGame(){
        String ip = Communication.getMyIpAddressString(); 
        this.ipSign.setSign2IP(ip);
        this.title.setText("Waiting for connection...");
        this.paintComponents(this.getGraphics());
        this.app.create(); // beware, this blocks the code
    }
}
