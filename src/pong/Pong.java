package pong;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;
public class Pong implements ActionListener, KeyListener{

    public static Pong pong;
    public Paddle p1, p2;
    public Ball ball;
    public int width = 700, height = 700;
    public boolean bot = false, w, s, up, down;
    public int gameStatus = 0; //0 = Stopped, 1= Stop, 2=Playing
    
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
        
        
        timer.start();
    }
    public void start(){
        gameStatus = 2;
        p1 = new Paddle(this, 1);
        p2 = new Paddle(this, 2);
        ball = new Ball(this);
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
        ball.update(p1, p2);
    }
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(gameStatus == 0){
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial",1 ,30));
            g.drawString("Pong", width/2 -35, 50);
            g.setColor(Color.RED);
            g.drawString("Press Space to start game", width/2 -190, 300);
            g.drawString("Press Shift to play with a bot", width/2 -190, 350);
        }
        if(gameStatus == 2 || gameStatus == 1){
            g.setColor(Color.GRAY);
            g.setStroke(new BasicStroke(5f));
            g.drawLine(width/2, 0, width/2, height);
            p1.render(g);
            p2.render(g);
            ball.render(g);
        }
        if(gameStatus == 1){
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial",1 ,30));
            g.drawString("Pause", width/2 -35, 50);
        }
        
    }
    public void actionPerformed(ActionEvent e) {
        if(gameStatus == 2){
           update(); 
        }
        
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
        if(id == KeyEvent.VK_SHIFT && gameStatus == 0){
            bot = true;
            start();
        }
        if(id == KeyEvent.VK_SPACE){
            if(gameStatus == 0){
                start();
                bot = false;
            }
            else if(gameStatus == 1){
                gameStatus = 2;
            }
            else if(gameStatus == 2){
                gameStatus = 1;
            }
            
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
