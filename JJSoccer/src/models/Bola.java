package models;

import java.awt.Point;
import models.interfaces.Action;
import java.util.List;

/**
 *
 * @author
 */
public class Bola extends Actor {

    private final int inicialX;
    private final int inicialY;
    private int speedX;
    private int speedY;

    private static Bola instance;

    public Bola() {
        speedX = 0;
        speedY = 0;
        inicialX = 0;
        inicialY = 0;
        sprite = new Sprite("soccerball.png");
    }

    public Bola(int x, int y) {
        super(x, y);
        speedX = 0;
        speedY = 0;
        inicialX = x;
        inicialY = y;
        sprite = new Sprite("soccerball.png");
    }

    public static Bola getInstance(int x, int y) {
        if (instance == null) {
            instance = new Bola(x, y);
            return instance;
        } else {
            return instance;
        }
    }

    protected void desacelerar() {
        if (speedX > 0) {
            setSpeedX(speedX - 1);
        } else if (speedX < 0) {
            setSpeedX(speedX + 1);
        }
        if (speedY > 0) {
            setSpeedY(speedY - 1);
        } else if (speedY < 0) {
            setSpeedY(speedY + 1);
        }
    }

    protected void setSpeedX(int speedX) {
        // if (speedX >= 0) {
        this.speedX = speedX;
        //}
    }

    protected void setSpeedY(int speedY) {
        //if (speedY >= 0) {
        this.speedY = speedY;
        // }
    }

    public void act(Action action, List<Actor> collisions) {
        if (!action.getLimite().contains(new Point(getX(), getY()))) {
            speedX = -speedX;
            speedY = -speedY;
        }
        setX(getX() + speedX);
        setY(getY() + speedY);
        desacelerar();

    }

    public void receberAcao(int speedX, int speedY) {
        //System.err.println("X: " + speedX + "Y " + speedY);
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    /**
     * Testes
     *
     * @return
     */
    @Override
    public String toString() {
        return "bola";
    }

    public void retornarPosicaoInicial() {
        setX(inicialX);
        setY(inicialY);
        speedX = 0;
        speedY = 0;
    }
}
