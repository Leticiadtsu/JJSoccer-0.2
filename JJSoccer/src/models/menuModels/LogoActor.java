/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.menuModels;

import java.util.List;
import models.Actor;
import models.Sprite;
import models.interfaces.Action;

/**
 *
 * @author Andre Chateaubriand
 */
public class LogoActor extends Actor {

    private boolean onPosition;
    private int speed;
    private int expectedY;

    public LogoActor(int x, int y, int speed) {
        super(x, -300);
        setSpr(new Sprite("JJSoccer.png"));
        onPosition = false;
        this.speed = speed;
        expectedY = y;
    }

    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        if (!onPosition) {
            setY(speed + getY());
            if (expectedY <= getY()) {
                onPosition = true;
            }
        }
    }

    /**
     * @return the onPosition
     */
    protected boolean isOnPosition() {
        return onPosition;
    }

}

