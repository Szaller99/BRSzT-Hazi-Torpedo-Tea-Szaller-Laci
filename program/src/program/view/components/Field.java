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

        this.setBounds(20,20,260,260);
        this.setVisible(true);

        if (this.isUpper) {
            this.setBackground(new Color(180,80,80));
        }
        else {
            this.setBackground(new Color(80,150,80));
        }
       
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
                tiles[i][j] = new Tile(i, j);
                this.add(tiles[i][j]);
            }
        }

        // LegendTile[] sideLegends = new LegendTile[11];
        // for (int i=0; i<= 10; i++) {
        //     sideLegends[i] = new LegendTile(0,i,String.valueOf(i));
        //     this.add(sideLegends[i]);
        // }

        // LegendTile[] upperLegends = new LegendTile[11];
        // for (int i=0; i<= 10; i++) {
        //     upperLegends[i] = new LegendTile(i,0,String.valueOf(i));
        //     this.add(upperLegends[i]);
        // }
        
        this.paintComponents(this.getGraphics());
       

    }

    // public void paint(Graphics g) {
    //     g.setColor(this.getBackground());
    //     g.fillRect(20,20,240, 240);
    //     g.clearRect(30,30,220,220);
    // }
}
