package program.view;

import program.communication.Communication;
import program.controller.Controller;
import program.model.*;
import program.model.Frame;

import javax.swing.*;

import java.awt.*;


public class WelcomeFrame extends Frame {
    private Controller app;
    private CreateGameButton createGame;
    private JoinGameButton joinGame;
    private JTextField IP;
    private IPSign ipSign;
    public WelcomeFrame(Controller app) {
        super(app);
        this.app = app;
        this.setupWelcomeFrame();
    }

    private void setupWelcomeFrame() {
        this.setupFrame(new Dimension(600,600),"Welcome",false,true);
        Container welcomeFrameContainer = this.getContentPane();
        welcomeFrameContainer.setBackground(new Color(60,70,60));

        this.createGame = new CreateGameButton(this);
        this.joinGame = new JoinGameButton(this);
        this.IP = new JTextField();
        this.ipSign = new IPSign();

        welcomeFrameContainer.setLayout(new GridLayout(4,1, 10, 10));
        welcomeFrameContainer.add(createGame);
        welcomeFrameContainer.add(ipSign);
        welcomeFrameContainer.add(IP);
        welcomeFrameContainer.add(joinGame);
        this.paintComponents(this.getGraphics());
    }

    public void createGame(){
        String ip = Communication.getMyIpAddressString(); 
        this.ipSign.setSign2IP(ip);
        this.paintComponents(this.getGraphics());
        this.app.create(); // beware, this blocks the code
    }

    public void joinGame(){
        String ip = this.IP.getText();
        this.app.join(ip);
    }
}
