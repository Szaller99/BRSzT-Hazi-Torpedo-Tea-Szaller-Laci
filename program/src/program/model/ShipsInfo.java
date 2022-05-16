package program.model;

import javax.swing.*;

import java.awt.*;


public class ShipsInfo extends JComponent {
    private boolean isUpper;

    private Ship ship_5;
    private Ship ship_4;
    private Ship ship_3;
    private Ship ship_2_1;
    private Ship ship_2_2;
    private Ship ship_1_1;
    private Ship ship_1_2;
    
    public ShipsInfo(boolean isUpper){
        super();
        this.isUpper = isUpper;
        this.setup();
       
    }

    private void setup(){
        this.setBounds(20,200,260,200);
        this.setVisible(true);
        if (this.isUpper) {
            JLabel FieldSign = new JLabel("Enemy's Ships:");
            FieldSign.setVisible(true);
            FieldSign.setForeground(Color.WHITE);
            FieldSign.setFont(new Font("Arial", Font.BOLD, 14));
            FieldSign.setBounds(80, 25, 240, 20);
            this.add(FieldSign);
            // this.setBackground(new Color(180,80,80));
        }
        else {
            JLabel FieldSign = new JLabel("My Ships:");
            FieldSign.setVisible(true);
            FieldSign.setForeground(Color.WHITE);
            FieldSign.setFont(new Font("Arial", Font.BOLD, 14));
            FieldSign.setBounds(80, 25, 240, 20);
            this.add(FieldSign);
            // this.setBackground(new Color(80,150,80));
        }

        this.ship_5 = new Ship(5,1);
        // ship_5.setBounds(40, 60, 104, 20);
        // ship_5.setIcon(new ImageIcon("pics/ship_5.png"));
        // ship_5.setVisible(true);
        this.add(ship_5);

        this.ship_4 = new Ship(4,1);
        // ship_4.setBounds(40, 100, 83, 20);
        // ship_4.setIcon(new ImageIcon("pics/ship_4.png"));
        // ship_4.setVisible(true);
        this.add(ship_4);

        this.ship_3 = new Ship(3,1);
        // ship_3.setBounds(40, 140, 62, 20);
        // ship_3.setIcon(new ImageIcon("pics/ship_3.png"));
        // ship_3.setVisible(true);
        this.add(ship_3);

        this.ship_2_1 = new Ship(2,1);
        // ship_2_1.setBounds(40, 180, 41, 20);
        // ship_2_1.setIcon(new ImageIcon("pics/ship_2.png"));
        // ship_2_1.setVisible(true);
        this.add(ship_2_1);

        this.ship_2_2 = new Ship(2,2);
        // ship_2_2.setBounds(100, 180, 41, 20);
        // ship_2_2.setIcon(new ImageIcon("pics/ship_2.png"));
        // ship_2_2.setVisible(true);
        this.add(ship_2_2);

        this.ship_1_1 = new Ship(1,1);
        // ship_1_1.setBounds(40, 220, 20, 20);
        // ship_1_1.setIcon(new ImageIcon("pics/single_ship.png"));
        // ship_1_1.setVisible(true);
        this.add(ship_1_1);

        this.ship_1_2 = new Ship(1,2);
        // ship_1_2.setBounds(100, 220, 20, 20);
        // ship_1_2.setIcon(new ImageIcon("pics/single_ship.png"));
        // ship_1_2.setVisible(true);
        this.add(ship_1_2);

        this.paintComponents(this.getGraphics());
    }

    public void paint(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(20,20,240, 240);
        // g.clearRect(30,30,220,220);
        this.paintComponents(g);
    }

    public void hideShip(int length){
        switch(length){
            case 5:
            this.ship_5.setVisible(false);
            break;

            case 4:
            this.ship_4.setVisible(false);
            break;

            case 3:
            this.ship_3.setVisible(false);
            break;

            case 2:
            if(this.ship_2_1.isVisible()){
                this.ship_2_1.setVisible(false);
            }
            else{
                this.ship_2_2.setVisible(false);
            }
            break;

            case 1:
            if(this.ship_1_1.isVisible()){
                this.ship_1_1.setVisible(false);
            }
            else{
                this.ship_1_2.setVisible(false);
            }
            break;
        }
    }

    public void showShip(int length){
        switch(length){
            case 5:
            this.ship_5.setVisible(true);
            break;

            case 4:
            this.ship_4.setVisible(true);
            break;

            case 3:
            this.ship_3.setVisible(true);
            break;

            case 2:
            if(this.ship_2_1.isVisible()){
                this.ship_2_2.setVisible(true);
            }
            else{
                this.ship_2_1.setVisible(true);
            }
            break;

            case 1:
            if(this.ship_1_1.isVisible()){
                this.ship_1_2.setVisible(true);
            }
            else{
                this.ship_1_1.setVisible(true);
            }
            break;
        }
    }

    public void showAllShips(){
        this.showShip(5);
        this.showShip(4);
        this.showShip(3);
        this.showShip(2);
        this.showShip(2);
        this.showShip(1);
        this.showShip(1);
    }

}
