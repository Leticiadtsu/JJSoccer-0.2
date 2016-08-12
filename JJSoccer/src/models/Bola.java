package models;

import models.interfaces.Action;
import java.util.List;

/**
 *
 * @author
 */
public class Bola extends Actor {

    private int speedX;
    private int speedY;

    public Bola() {
        speedX = 0;
        speedY = 0;
        sprite = new Sprite("soccerball.png");

    }

    public Bola(int x, int y) {
        super(x, y);
        speedX = 0;
        speedY = 0;
        sprite = new Sprite("soccerball.png");
    }
    protected void desacelerar(){
        setSpeedX(speedX-1);
        setSpeedY(speedY-1);
    }

    protected void setSpeedX(int speedX) {
        if (speedX >= 0) {
            this.speedX = speedX;
        }
    }
    
    protected void setSpeedY(int speedY) {
        if (speedY >= 0) {
            this.speedY = speedY;
        }
    }
    
    public void act(Action action, List<Actor> collisions) {
        setX(getX() + speedX);
        setY(getY() + speedY);
        desacelerar();
    }
    
    public void receberAcao(int speedX, int speedY){
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    /**
     * remover depois
     *
     * @return
     */
    @Override
    public String toString() {
        return "bola";
    }
}
