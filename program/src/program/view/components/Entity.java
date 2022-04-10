package program.view.components;

import javax.swing.JComponent;

import java.awt.*;

public class Entity extends JComponent{
    private Dimension position;
    public Dimension getPosition() {
        return this.position;
    }
    public void setPosition(int x, int y) { this.position.width = x; this.position.height = y;}

    private Dimension size;
    public Dimension getSize() {
        return this.size;
    }
    public void setSize(int x, int y) { this.size.width = x; this.size.height = y;}

    private boolean isActive;
    private String status;
    private String design;

    public Entity(){
        super();
        this.position = new Dimension(0,0);
        this.size = new Dimension(0,0);
    }

    public void paint(Graphics g){
        g.setColor(this.getBackground());
        g.fillRect(this.position.width,
                this.position.height,
                this.size.width,
                this.size.height);
    }
}