package program.view.components;

import javax.swing.*;
import java.awt.*;


public class Ship extends JButton {
    int xPosition;
    int yPosition;
    int ShipLength;
    int number;

    public Ship(int length, int lineNumber){
        switch(length){
            case 5:
            this.setBounds(100, 60, 104, 20);
            this.setIcon(new ImageIcon("pics/ship_5.png"));
            break;

            case 4:
            this.setBounds(100, 100, 83, 20);
            this.setIcon(new ImageIcon("pics/ship_4.png"));
            break;

            case 3:
            this.setBounds(100, 140, 62, 20);
            this.setIcon(new ImageIcon("pics/ship_3.png"));
            break;

            case 2:
            this.setIcon(new ImageIcon("pics/ship_2.png"));
            if(lineNumber==1){this.setBounds(100, 180, 41, 20);}
            if(lineNumber==2){this.setBounds(160, 180, 41, 20);}
            break;

            case 1:
            this.setIcon(new ImageIcon("pics/single_ship.png"));
            if(lineNumber==1){this.setBounds(100, 220, 20, 20);}
            if(lineNumber==2){this.setBounds(160, 220, 20, 20);}
            break;

            default:
        }
        this.setVisible(true);
        this.setFocusable(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
