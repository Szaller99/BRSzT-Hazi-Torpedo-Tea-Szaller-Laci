package program.components;

import javax.swing.*;
import java.awt.*;

public class IPSign extends JComponent{
    JLabel Sign;
    public IPSign(){
        this.setBounds(200,150,200,40);
        this.setVisible(true);

        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setFocusable(true);
        this.setVisible(true);

        this.Sign = new JLabel("",SwingConstants.CENTER);
        this.Sign.setVisible(true);
        this.Sign.setForeground(Color.RED);
        this.Sign.setFont(new Font("Arial", Font.BOLD, 16));
        this.Sign.setBounds(0, 0, 200, 30);
        this.add(Sign);

        this.paintComponents(this.getGraphics());
    }

    public void clearSign(){
        this.Sign.setText("");
    }

    public void setSign2IP(String ip){
        this.Sign.setText("Your IP: " + ip);
    }
    
}
