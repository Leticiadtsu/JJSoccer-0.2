/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models;

import java.util.List;
import models.interfaces.Action;

/**
 *
 * @author Andre Chateaubriand
 */
public class Gol extends Actor{
   public Gol() {
        sprite = new Sprite("gol.png");
    }

    public Gol(int x, int y) {
        super(x, y);
        sprite = new Sprite("gol.png");
    }
    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        
    }
    
}
