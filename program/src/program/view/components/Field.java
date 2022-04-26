package program.view.components;

import javax.swing.*;
import java.awt.*;


public class Field extends JComponent {
    private boolean isUpper;
    private boolean isLower;

    public Field(boolean isUpper) {
        super();
        this.isUpper = isUpper;
        this.isLower = !isUpper;
        setupField();
    }

    private void setupField() {

        this.setBounds(20,200,260,260);
        this.setVisible(true);

        if (this.isUpper) {
            JLabel FieldSign = new JLabel("Enemy's Field:");
            FieldSign.setVisible(true);
            FieldSign.setForeground(Color.BLACK);
            FieldSign.setFont(new Font("Arial", Font.BOLD, 14));
            FieldSign.setBounds(30, 25, 240, 20);
            this.add(FieldSign);
            this.setBackground(new Color(180,80,80));
        }
        else {
            JLabel FieldSign = new JLabel("My Field:");
            FieldSign.setVisible(true);
            FieldSign.setForeground(Color.BLACK);
            FieldSign.setFont(new Font("Arial", Font.BOLD, 14));
            FieldSign.setBounds(30, 25, 240, 20);
            this.add(FieldSign);
            this.setBackground(new Color(80,150,80));
        }

        this.paintComponents(this.getGraphics());
        
       
        Tile[][] tiles = new Tile[11][11];

        for (int i=0; i<= 10; i++) {
            tiles[0][i] = new LegendTile(0,i,String.valueOf(i));
            this.add(tiles[0][i]);
        }

        for (int i=0; i<= 10; i++) {
            tiles[i][0] = new LegendTile(i,0,String.valueOf(i));
            this.add(tiles[i][0]);
        }

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j] = new GameTile(i, j);
                this.add(tiles[i][j]);
            }
        }

        this.paintComponents(this.getGraphics());
        // this.paint(this.getGraphics());

    }

    public void paint(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(20,20,240, 265);
        // g.clearRect(30,30,220,220);
        this.paintComponents(g);
    }
}
