/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models;

import java.util.List;
import models.interfaces.Action;
import models.interfaces.GolListener;

/**
 *
 * @author Andre Chateaubriand
 */
public class Gol extends Actor {

    private GolListener listener;
    private boolean timeCasa;

    public Gol(GolListener listener, boolean timeCasa) {
        sprite = new Sprite("gol.png");
        setCollisionArea(new CollisionArea(80, 200));
        this.listener = listener;
        this.timeCasa = timeCasa;
    }

    public Gol(int x, int y, GolListener listener, boolean timeCasa) {
        super(x, y);
        sprite = new Sprite("gol.png");
        setCollisionArea(new CollisionArea(80, 200));
        this.listener = listener;
        this.timeCasa = timeCasa;
    }

    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        for (Actor actor : areaDeRelevancia) {
            if (getCollisionArea().isColliding(this, actor)) {
                if (actor instanceof Bola) {
                    listener.onGoal(this);
                }
            }
        }
    }

    /**
     * @return the timeCasa
     */
    public boolean isTimeCasa() {
        return timeCasa;
    }

}
