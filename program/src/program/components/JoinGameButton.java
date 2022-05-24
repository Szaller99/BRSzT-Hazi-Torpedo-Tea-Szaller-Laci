package program.components;

import program.view.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

public class JoinGameButton extends JButton implements MouseInputListener{
    private WelcomeFrame myWelcomeFrame;
    private JoinFrame myJoinFrame;
    private boolean join;
    public JoinGameButton(WelcomeFrame myFrame){
        this.setBounds(200, 250, 200, 70);
        this.setText("Join Game");
        this.myWelcomeFrame = myFrame;
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
        this.join = false;
    }
    public JoinGameButton(JoinFrame myFrame){
        this.setBounds(200, 250, 200, 70);
        this.setText("Join Game");
        this.myJoinFrame = myFrame;
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
        this.join = true;
    }

    public void mouseClicked(MouseEvent e) {
        if(join){
            this.myJoinFrame.joinGame();
        }
        else{
            this.myWelcomeFrame.joinGame();
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
