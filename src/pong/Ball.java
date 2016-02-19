package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    
    public int x, y, width =25 , height = 25, motionX, motionY, ammountOfHits;
    
    public Random random;
    
    private Pong pong;
    
    public Ball(Pong pong){
        this.pong = pong;
        this.random = new Random();
        spawn();
    }
    public void update(Paddle p1, Paddle p2){
        int speed = 5;
        this.x += motionX * speed;
        this.y += motionY * speed;
        
        if(this.y + height > pong.height || this.y < 0){
                if(this.motionY <0){
                    this.motionY = random.nextInt(4);
                }
                else{
                    this.motionY = -random.nextInt(4);
                }
            }
        
        if(checkCollision(p1) == 1){
            this.motionX = 1 + (ammountOfHits / 5);
            this.motionY = -2 + random.nextInt(4);
            if(motionY == 0){
                motionY =1;
            }
            ammountOfHits++;
        }else if(checkCollision(p2) == 1){
            this.motionX = -1 - (ammountOfHits / 5);
            this.motionY = -2 + random.nextInt(4);
            if(motionY == 0){
                motionY =1;
            }
            ammountOfHits++;
        }if(checkCollision(p1) == 2){
            p2.score++;
            spawn();
        }
        else if(checkCollision(p2) == 2){
            p1.score++;
            spawn();
        }
    }
    
    public void spawn(){
        ammountOfHits = 0;
        this.x = pong.width / 2 - this.width /2;
        this.y = pong.height /2 - this.height /2;
        motionY = -2 + random.nextInt(4);
        if(motionY == 0){
            motionY =1;
        }
        if(random.nextBoolean()){
            motionX = 1;
        }else {
            motionX = -1;
        }
    }
    
    public int checkCollision(Paddle paddle){
        
            if(this.x < paddle.x + paddle.width && this.x + width > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y){
                return 1; // hit
                
            } else if((paddle.x > x && paddle.paddleNumber == 1) || (paddle.x < x && paddle.paddleNumber == 2)) {
                return 2; // score
            }
        return 0; //nothing
    }
    
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
