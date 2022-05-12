package program.view.components;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import program.view.GameFrame;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ReadyButton extends JButton implements MouseInputListener {
    GameFrame myFrame;
    public ReadyButton(GameFrame myFrame){
        this.setText("Ready to play!");
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.myFrame = myFrame;

        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void mouseClicked(java.awt.event.MouseEvent e)
    {
        System.out.print("Ready Button was pressed \n");
        // todo ready to play function
        this.myFrame.ready2Play();
    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
