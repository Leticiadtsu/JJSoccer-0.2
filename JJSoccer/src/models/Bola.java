package models;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
    public Bola(int x, int y){
        super(x, y);
        speedX = 0;
        speedY = 0;
        sprite = new Sprite("soccerball.png");
    }

    protected void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    protected void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void act(Action action, List<Actor> collisions) {
        setX(getX() + speedX);
        setY(getY() + speedY);
        for (Actor collision : collisions) {
            setX(collision.getX() + collision.getSprite().getImage().getWidth(null) / 2);
            setY(collision.getX() + collision.getSprite().getImage().getHeight(null) / 2);
        }
    }
}
