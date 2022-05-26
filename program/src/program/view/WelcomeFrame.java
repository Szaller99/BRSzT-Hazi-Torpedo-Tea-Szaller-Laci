package program.view;

import program.controller.Controller;
import program.components.*;

import javax.swing.*;

import java.awt.*;


public class WelcomeFrame extends JFrame{
    private Controller app;
    private CreateGameButton createGame;
    private JoinGameButton joinGame;
    private JLabel title;
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
        this.title = new JLabel("Battleship",SwingConstants.CENTER);
        this.title.setVerticalAlignment(SwingConstants.CENTER);
        this.title.setFont(new Font("Arial", Font.BOLD, 25));
        this.title.setForeground(new Color(255,0,0));
        this.title.setBounds(0, 0, 600, 100);


        

        welcomeFrameContainer.setLayout(null);
        welcomeFrameContainer.add(title);
        welcomeFrameContainer.add(createGame);
        welcomeFrameContainer.add(joinGame);
        this.paintComponents(this.getGraphics());
    }

    public void createGame(){
        this.createFrame = new CreateFrame(app);
        this.setVisible(false);
        this.create = true;
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
        if (this.join){
            this.joinFrame.setVisible(false);
        }
    }

    
}
