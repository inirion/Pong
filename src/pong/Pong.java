package pong;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;
public class Pong implements ActionListener, KeyListener{

    public static Pong pong;
    public Paddle p1, p2;
    public int width = 700, height = 700;
    public boolean bot = false, w, s, up, down;
    
    public Renderer renderer;
    
    public Pong(){
        Timer timer = new Timer(20,this);
        JFrame frame = new JFrame("Pong");
        renderer = new Renderer();
        
        frame.setSize(width + 15, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(renderer);
        frame.addKeyListener(this);
        
        start();
        
        timer.start();
    }
    public void start(){
        p1 = new Paddle(this, 1);
        p2 = new Paddle(this, 2);
    }
    public void update(){   
        if(w == true){
            p1.move(true);
        }
        if(s == true){
            p1.move(false);
        }
        if(up == true){
            p2.move(true);
        }
        if(down == true){
            p2.move(false);
        }
    }
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.GRAY);
        g.setStroke(new BasicStroke(5f));
        g.drawLine(width/2, 0, width/2, height);
        p1.render(g);
        p2.render(g);
    }
    public void actionPerformed(ActionEvent e) {
        update();
        renderer.repaint();
    }
    public static void main(String[] args) {
        pong = new Pong();
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if(id == KeyEvent.VK_W){
            w = true;
        }
        if(id == KeyEvent.VK_S){
            s = true;
        }
        if(id == KeyEvent.VK_UP){
            up = true;
        }
        if(id == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if(id == KeyEvent.VK_W){
            w = false;
        }
        if(id == KeyEvent.VK_S){
            s = false;
        }
        if(id == KeyEvent.VK_UP){
            up = false;
        }
        if(id == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}
