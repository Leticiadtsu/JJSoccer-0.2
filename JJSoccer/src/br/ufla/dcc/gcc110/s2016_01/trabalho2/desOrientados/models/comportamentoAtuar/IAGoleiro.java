/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.comportamentoAtuar;

import java.awt.Toolkit;
import java.util.List;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor.Direcao;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Dimensao;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.JogadorActor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;

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
