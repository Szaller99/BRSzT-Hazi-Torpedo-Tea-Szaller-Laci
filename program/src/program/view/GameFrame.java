package program.view;

import program.controller.Controller;
import program.view.components.Field;
import program.view.components.Frame;
import program.view.components.Entity;
import javax.swing.JPanel;
import java.awt.*;

public class GameFrame extends Frame {
    private Controller app;
    public GameFrame(Controller app) {
        super(app);
        this.app = app;
        setupGameFrame();
    }
    private void setupGameFrame(){
        this.setupFrame(
                new Dimension(600, 600),
                "Battleships Game",
                false,
                true );
        Container c = this.getContentPane();
        c.setBackground(new Color(30,30,30));

        Field myField = new Field(false);
        Field enemyField = new Field(true);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2,1,10,10));
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1,1,10,10));

        fieldPanel.add(myField);
        fieldPanel.add(enemyField);

        this.setLayout(new GridLayout(1,2, 10, 10));
        this.add(fieldPanel);
        this.add(infoPanel);
        this.pack();
        this.setSize(600,600);

    }
}