package program.model;

import javax.swing.*;

import java.awt.*;

public class TurnSign extends JComponent {
    JLabel FieldSign;
    
    public TurnSign(){
        this.setBounds(20,200,240,50);
        this.setVisible(true);

        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setFocusable(true);
        this.setVisible(true);

        this.FieldSign = new JLabel("",SwingConstants.CENTER);
        this.FieldSign.setVisible(true);
        this.FieldSign.setForeground(Color.RED);
        this.FieldSign.setFont(new Font("Arial", Font.BOLD, 16));
        this.FieldSign.setBounds(30, 15, 240, 30);
        this.add(FieldSign);

        this.paintComponents(this.getGraphics());
    }

    public void YourTurn(){
        this.FieldSign.setText("Your Turn!");
    }

    public void EnemysTurn(){
        this.FieldSign.setText("Enemy's Turn...");
    }

    public void ShipSetup(){
        this.FieldSign.setText("Set up your Field!");
    }

    public void DeleteShip(){
        this.FieldSign.setText("Choose your ship to delete!");
    }

    public void NotDone(){
        this.FieldSign.setText("You have some ship left!");
    }

    public void Done(){
        this.FieldSign.setText("Waiting for enemy...");
    }

    public void clearSign(){
        this.FieldSign.setText("");
    }

    // public void paint(Graphics g) {
    //     super.paintComponent(g);
    //     g.setColor(this.getBackground());
    //     this.setBorder(BorderFactory.createLineBorder(Color.RED));
    //     g.fillRect(20,200,240, 40);
    //     // g.clearRect(30,30,220,220);
    //     this.paintComponents(g);
    // }

}
