package program.view;

import program.controller.Controller;
import program.view.components.*;
import program.view.components.Frame;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        this.app.create();
        String ip = "42"; // TODO get game's IP
        this.ipSign.setSign2IP(ip);
    }

    public void joinGame(){
        String ip = this.IP.getText();
        this.app.join(ip);
    }
}
