package program.view.components;

import program.view.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.MouseEvent;

public class CreateGameButton extends JButton implements MouseInputListener{
    private WelcomeFrame myFrame;
    public CreateGameButton(WelcomeFrame myFrame){
        this.setText("Create Game");
        this.myFrame = myFrame;
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        // System.out.print("Create Game in button \n");
        this.myFrame.createGame();
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
