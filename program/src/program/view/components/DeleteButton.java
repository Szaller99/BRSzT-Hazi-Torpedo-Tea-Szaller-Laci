package program.view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import program.view.components.*;

public class DeleteButton extends JButton implements MouseInputListener {
    Field myField;
    TurnSign myTurnSign;
        public DeleteButton(Field myField, TurnSign myTurnSign){
            this.setText("Delete Ship");
            this.setBackground(Color.LIGHT_GRAY);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            this.myField = myField;
            this.myTurnSign = myTurnSign;
    
            this.addMouseListener(this);
            this.setFocusable(true);
            this.setVisible(true);
        }
    
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            System.out.print("Delete Button was pressed \n");
            // todo delete ship function
            myTurnSign.DeleteShip();
            myField.set2Delete();
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
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
        
}
