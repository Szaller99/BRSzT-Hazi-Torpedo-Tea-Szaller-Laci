package program.view;

import program.controller.Controller;
import program.view.components.*;
import program.view.components.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeFrame extends Frame {
    private Controller app;
    public WelcomeFrame(Controller app) {
        super(app);
        this.app = app;
        this.setupWelcomeFrame();
    }

    private void setupWelcomeFrame() {
        this.setupFrame(new Dimension(200,200),"Welcome",false,true);
        Container welcomeFrameContainer = this.getContentPane();
        welcomeFrameContainer.setBackground(new Color(60,70,60));
        welcomeFrameContainer.add(new JButton("asd"));
    }
}
