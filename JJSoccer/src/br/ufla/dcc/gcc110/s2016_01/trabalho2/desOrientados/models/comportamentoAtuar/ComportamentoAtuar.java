/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.comportamentoAtuar;

import java.util.List;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.JogadorActor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;

/**
 * Interface que define os comportamentos dos atores (obejtos que realizam uma
 * ação).
 */
public interface ComportamentoAtuar {

 /**
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
