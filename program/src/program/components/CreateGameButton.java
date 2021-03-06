package program.components;

import program.view.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.MouseEvent;

public class CreateGameButton extends JButton implements MouseInputListener{
    private WelcomeFrame myWelcomeFrame;
    private CreateFrame myCreateFrame;
    private boolean create;
    public CreateGameButton(WelcomeFrame myFrame){
        this.setBounds(200, 150, 200, 70);
        this.setText("Create Game");
        this.myWelcomeFrame = myFrame;
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
        this.create = false;
    }
    public CreateGameButton(CreateFrame myFrame){
        this.setBounds(200, 250, 200, 70);
        this.setText("Create Game");
        this.myCreateFrame = myFrame;
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
        this.create = true;
    }

    public void mouseClicked(MouseEvent e) {
        // System.out.print("Create Game in button \n");
        if(this.create){
            this.myCreateFrame.createGame();
        }
        else{
            this.myWelcomeFrame.createGame();
        }
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    public void mouseDragged(MouseEvent e) {
        
    }

    public void mouseMoved(MouseEvent e) {
        
    }
}
