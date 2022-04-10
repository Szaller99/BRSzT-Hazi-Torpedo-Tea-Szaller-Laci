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
        this.setSize(250,250);
        if (this.isUpper) {
            // this.setPosition(25,25);
            this.setBackground(new Color(180,80,80));
        }
        else {
            // this.setPosition(25,300);
            this.setBackground(new Color(80,150,80));
        }
        this.setVisible(true);

    }
}
