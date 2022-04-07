package program.view;

import javax.swing.JFrame;
import program.controller.Controller;

public class Frame extends JFrame{
    private Controller app;

    public Frame(Controller app) {
        super();
        this.app = app;

        setupFrame();
    }

    private void setupFrame(){
        this.setVisible(true);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("My Frame");

    }
}
