package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Renderer extends JPanel {
    protected void paintComponent(Graphics g){
        super.paintComponents(g);
        Pong.pong.render((Graphics2D)g);
    }
}
