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
 *
 * @author costa
 */
public class IAGoleiro extends InteligenciaArtificial {

    private Direcao direcao;

    public IAGoleiro() {
        direcao = Direcao.BAIXO;
    }

    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> collisions) {
        escolherDirecao(chamador);
        chamador.mover(direcao, action.getLimite(), collisions);
    }

    private void escolherDirecao(JogadorActor chamador) {
        if (chamador.getY() > (Toolkit.getDefaultToolkit().getScreenSize().height / 2)+90) {
            this.direcao = Direcao.CIMA;
        }
        if (chamador.getY() < (Toolkit.getDefaultToolkit().getScreenSize().height / 2)-35) {
            this.direcao = Direcao.BAIXO;
        }
    }

}
