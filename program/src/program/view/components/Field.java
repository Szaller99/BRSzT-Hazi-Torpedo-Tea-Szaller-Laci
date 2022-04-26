package program.view.components;

import javax.swing.*;
import java.awt.*;


public class Field extends JComponent {
    private boolean isUpper;
    private boolean isLower;
    public Tile[][] tiles;

    public Field(boolean isUpper) {
        super();
        this.isUpper = isUpper;
        this.isLower = !isUpper;
        setupField();
    }

    public Tile[][] getHostTiles() {
        return this.tiles;
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
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                tiles[i][j] = new Tile(i, j);
                this.add(tiles[i][j]);
            }
        }
    }
    public void paint(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(20,20,240, 240);
        // g.clearRect(30,30,220,220);
    }
}
