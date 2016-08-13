/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models;

import java.util.List;
import models.interfaces.Action;

/**
 *
 * @author costa
 */
class ActorBasic extends Actor {

    public ActorBasic(int x, int y) {
        super(x, y);
    }

    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        
    }
    
}
