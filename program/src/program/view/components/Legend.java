package program.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Legend extends Tile{
    public JLabel content;
    public Legend(int xPosition, int yPosition, String numLabel) {
        super(xPosition, yPosition);
        
        this.content = new JLabel(numLabel);
    }
}
