package program.view.components;

import program.view.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.MouseEvent;

public class JoinGameButton extends JButton implements MouseInputListener{
    public WelcomeFrame myFrame;
    public JoinGameButton(WelcomeFrame myFrame){
        this.setText("Join Game");
        this.myFrame = myFrame;
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        myFrame.joinGame();
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
