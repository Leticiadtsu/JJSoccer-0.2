/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models;

import java.util.ArrayList;
import java.util.List;
import models.interfaces.Action;
import models.interfaces.GolListener;

/**
 *
 * @author Andre Chateaubriand
 */
public class Gol extends Actor {

    private List<GolListener> listeners;
    private boolean timeCasa;

    public Gol(GolListener listener, boolean timeCasa) {
        sprite = new Sprite("gol.png");
        setCollisionArea(new CollisionArea(80, 200));
        this.listeners = new ArrayList<>();
        this.listeners.add(listener);
        this.timeCasa = timeCasa;
    }

    public Gol(int x, int y, GolListener listener, boolean timeCasa) {
        super(x, y);
        sprite = new Sprite("gol.png");
        setCollisionArea(new CollisionArea(80, 200));
        this.listeners = new ArrayList<>();
        this.listeners.add(listener);
        this.timeCasa = timeCasa;
    }

    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        for (Actor actor : areaDeRelevancia) {
            if (getCollisionArea().isColliding(this, actor)) {
                if (actor instanceof Bola) {
                    for (GolListener listener : listeners) {
                        listener.onGoal(isTimeCasa());
                    }

                }
            }
        }
    }
    
    public void addListener(GolListener listener){
        this.listeners.add(listener);
    }

    /**
     * @return the timeCasa
     */
    public boolean isTimeCasa() {
        return timeCasa;
    }

}
