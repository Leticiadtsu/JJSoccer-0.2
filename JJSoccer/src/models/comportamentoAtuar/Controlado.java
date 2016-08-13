/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.comportamentoAtuar;

import controller.managers.InputManager;
import java.awt.event.KeyEvent;
import java.util.List;
import models.Actor;
import models.Bola;
import models.JogadorActor;
import models.interfaces.Action;

/**
 *
 * @author costa
 */
public class Controlado implements ComportamentoAtuar{

    @Override
    public void agir(JogadorActor chamador,Action action, List<Actor> collisions) {
        if (InputManager.getInstance().isPressed(KeyEvent.VK_UP)) {
            chamador.mover(Actor.Direcao.CIMA, action.getLimite(),collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_DOWN)) {
            chamador.mover(Actor.Direcao.BAIXO, action.getLimite(),collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
             chamador.mover(Actor.Direcao.DIREITA, action.getLimite(),collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)) {
             chamador.mover(Actor.Direcao.ESQUERDA, action.getLimite(),collisions);
        }
        if((InputManager.getInstance().isPressed(KeyEvent.VK_SPACE))){
            for (Actor actor : collisions) {
                if(actor instanceof Bola){
                    System.err.print("Era uma bola");
                }
            }
        }
    }

    
    
}
