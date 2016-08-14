package models.comportamentoAtuar;

import java.util.List;
import models.Actor;
import models.JogadorActor;
import models.interfaces.Action;

/**
 * Interface que define os comportamentos dos atores (obejtos que realizam uma
 * ação).
 */
public interface ComportamentoAtuar {

    /**
     * 
     * Método responsável por fazer com que o objeto aja. Recebbe como parâmetro
     * uma ação e uma lista de atores que a colidem. Deve ser implementado pelas
     * classes que implementam a interface.
     *
     * @param chamador JogadorActor que chamou a ação.
     * @param action dados necessários para a ação.
     * @param collisions lista de atores que colidem.
     */
    void agir(JogadorActor chamador, Action action, List<Actor> collisions);

}
