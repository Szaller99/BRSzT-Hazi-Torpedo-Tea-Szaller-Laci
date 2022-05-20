package program.view;

import program.controller.communication.Communication;
import program.controller.Controller;
import program.model.*;

import javax.swing.*;

import java.awt.*;


public class WelcomeFrame extends JFrame{
    private Controller app;
    private CreateGameButton createGame;
    private JoinGameButton joinGame;
    private JoinFrame joinFrame;
    private CreateFrame createFrame;
    private boolean create = false;
    private boolean join = false;
    public WelcomeFrame(Controller app) {
        super();
        this.app = app;
        this.setupWelcomeFrame();
    }

    private void setupWelcomeFrame() {

        this.setVisible(true);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Welcome");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container welcomeFrameContainer = this.getContentPane();
        welcomeFrameContainer.setBackground(new Color(60,70,60));

        this.createGame = new CreateGameButton(this);
        this.joinGame = new JoinGameButton(this);

        welcomeFrameContainer.setLayout(new GridLayout(2,1, 10, 10));
        welcomeFrameContainer.add(createGame);
        welcomeFrameContainer.add(joinGame);
        this.paintComponents(this.getGraphics());
    }

    public void createGame(){
        this.createFrame = new CreateFrame(app);
        this.setVisible(false);
        this. create = true;
    }

    public void joinGame(){
        this.joinFrame = new JoinFrame(app);
        this.setVisible(false);
        this.join = true;
    }
    
    public void hideAllFrames(){
        this.setVisible(false);
        if(this.create){
            this.createFrame.setVisible(false);
        }
        if (join){
            this.joinFrame.setVisible(false);
        }
    }

    
}
