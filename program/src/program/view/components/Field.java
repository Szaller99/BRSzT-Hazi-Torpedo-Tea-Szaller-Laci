package program.view.components;

import program.view.components.Entity;

import java.awt.*;


public class Field extends Entity{
    private boolean isUpper;
    private boolean isLower;

    public Field(boolean isUpper) {
        super();
        this.isUpper = isUpper;
        this.isLower = !isUpper;
        setupField();
    }

    private void setupField() {
        this.setSize(240,240);
        if (this.isUpper) {
            this.setBackground(new Color(180,80,80));
        }
        else {
            this.setBackground(new Color(80,150,80));
        }
        this.setPosition(20,20);

        this.setVisible(true);

        Tile[][] tiles = new Tile[11][11];
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tiles[i][j] = new Tile(i, j);
                this.add(tiles[i][j]);
            }
        }



    }
}
