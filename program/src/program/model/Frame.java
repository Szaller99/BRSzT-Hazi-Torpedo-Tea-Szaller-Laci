package program.model;

import javax.swing.JFrame;
import program.controller.Controller;

import java.awt.*;


public class Frame extends JFrame{
    private Controller app;

    public Frame(Controller app) {
        super();
        this.app = app;
    }

    protected void setupFrame(Dimension size, String  title, boolean isResizable, boolean isVisible){
        this.setVisible(isVisible);
        this.setSize(size.width, size.height);
        this.setResizable(isResizable);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setDefaultLookAndFeelDecorated(true);
    }
}
