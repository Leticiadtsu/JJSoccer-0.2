/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.comportamentoAtuar;

import java.awt.Toolkit;
import java.util.List;
import models.Actor;
import models.Actor.Direcao;
import models.Dimensao;
import models.JogadorActor;
import models.interfaces.Action;

/**
 * Classe que define a ação do goleiro, simplesmente tem uma direção como
 * atributo.
 */
public class IAGoleiro extends InteligenciaArtificial {

    private Direcao direcao;

    /**
     * Inicialmente a direção que o goleiro está "olhando" é para baixo.
     */
    public IAGoleiro() {
        direcao = Direcao.BAIXO;
    }

    /**
     * Método responsável por fazer com que o goleiro aja. Recebe como
     * parâmetro uma ação e uma lista de atores que a colidem. A sua
     * movimentação é definida apenas alterando de direção.
     *
     * @param chamador JogadorActor que chamou a açãoo.
     * @param action dados necessários para a ação.
     * @param collisions lista de atores que colidem.
     */
    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> collisions) {
        escolherDirecao(chamador);
        chamador.mover(direcao, action.getLimite(), collisions);
    }

    private void escolherDirecao(JogadorActor chamador) {
        if (chamador.getY() > (Toolkit.getDefaultToolkit().getScreenSize().height / 2) + 90) {
            this.direcao = Direcao.CIMA;
        }
        if (chamador.getY() < (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 35) {
            this.direcao = Direcao.BAIXO;
        }
    }

}
