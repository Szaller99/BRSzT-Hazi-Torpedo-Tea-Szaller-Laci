package program.model;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LegendTile extends Tile{
    public LegendTile(int xPosition, int yPosition, String numLabel) {
        super(xPosition, yPosition);
        
        this.setText(numLabel);
        this.setVisible(true);
        // this.setBounds(30+xPosition*20, 30+yPosition*20, 20, 20);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setMargin(new Insets(0, 0, 0, 0));

        // content = new JLabel(numLabel);
        // content.setBackground(Color.GREEN);
        // content.setVisible(true);
        // this.add(content);

        // this.paintComponents(this.getGraphics());
    }

    public void mouseClicked(MouseEvent e){
        System.out.print("legend " + this.tileName + " was clicked \n");
    }
}
