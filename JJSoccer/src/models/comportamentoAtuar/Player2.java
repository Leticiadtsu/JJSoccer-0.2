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
public class Player2 implements ComportamentoAtuar {

    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> collisions) {
        if (InputManager.getInstance().isPressed(KeyEvent.VK_UP)) {
            chamador.mover(Actor.Direcao.CIMA, action.getLimite(), collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_DOWN)) {
            chamador.mover(Actor.Direcao.BAIXO, action.getLimite(), collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
            chamador.mover(Actor.Direcao.DIREITA, action.getLimite(), collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)) {
            chamador.mover(Actor.Direcao.ESQUERDA, action.getLimite(), collisions);
        }
        if ((InputManager.getInstance().isPressed(KeyEvent.VK_SHIFT))) {
            chutar(chamador, collisions);

        }
    }

    public static void chutar(Actor chamador, List<Actor> collisions) {
        for (Actor actor : collisions) {
            if (actor instanceof Bola) {
                if (chamador.isColliding(actor) && actor instanceof Bola) {
                    switch (chamador.getDirecao()) {
                        case CIMA:
                            ((Bola) actor).receberAcao(0, -20);
                            break;
                        case BAIXO:
                            ((Bola) actor).receberAcao(0, 20);
                            break;
                        case ESQUERDA:
                            ((Bola) actor).receberAcao(-20, 0);
                            break;
                        case DIREITA:
                            ((Bola) actor).receberAcao(20, 0);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}