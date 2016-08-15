/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.comportamentoAtuar;

import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.controller.managers.InputManager;
import java.awt.event.KeyEvent;
import java.util.List;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Bola;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.JogadorActor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;

/**
 * Classe responsável por receber os pressionamentos do teclado para movimentar
 * o jogador.
 */
public class Player2 implements ComportamentoAtuar {

    /**
     * Método responsável por fazer com que o jogador aja. Recebbe como
     * parâmetro uma ação e uma lista de atores que a colidem. Indica as
     * direções que o jogador deve seguir.
     *
     * @param chamador JogadorActor que chamou a ação.
     * @param action dados necessários para a ação.
     * @param collisions lista de atores que colidem.
     */
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

    /**
     * Verifica a ação de chutar a bola, qual o sentido que a bola deve seguir
     * ao ser chutada
     *
     * @param chamador JogadorActor que chamou a ação.
     * @param collisions lista de atores que colidem.
     */
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
