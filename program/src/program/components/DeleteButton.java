package program.components;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;


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
            if(myField.set2Delete()){
                myTurnSign.DeleteShip();
            }
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
