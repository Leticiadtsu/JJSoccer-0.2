package models;

import java.util.List;
import models.interfaces.Action;

/**
 * Classe utilizada para a simulação de movimentação de um ator.
 */
class ActorBasic extends Actor {

    /**
     * Utiliza o construtor de Actor, por meio da herança. Passar apenas como
     * parâmetro as posições x e y.
     *
     * @param x posição x.
     * @param y posição y.
     */
    public ActorBasic(int x, int y) {
        super(x, y);
    }

    /**
     * Não realiza nenhuma ação, pois se trata apenas de uma simulação.
     */
    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {

    }

}
