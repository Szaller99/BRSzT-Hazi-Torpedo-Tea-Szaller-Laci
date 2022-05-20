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

        createFrameContainer.setLayout(new GridLayout(2,1, 10, 10));
        createFrameContainer.add(createGame);
        createFrameContainer.add(ipSign);
        this.paintComponents(this.getGraphics());
    
    }

    public void createGame(){
        String ip = Communication.getMyIpAddressString(); 
        this.ipSign.setSign2IP(ip);
        this.paintComponents(this.getGraphics());
        this.app.create(); // beware, this blocks the code
    }
}
