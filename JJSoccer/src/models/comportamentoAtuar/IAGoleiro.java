package models.comportamentoAtuar;

import java.awt.Toolkit;
import java.util.List;
import models.Actor;
import models.Actor.Direcao;
import models.Dimensao;
import models.JogadorActor;
import models.interfaces.Action;

/**
<<<<<<< HEAD
 * Classe que define a ação do goleiro, simplesmente tem uma direção como
 * atributo.
=======
 * Classe que define a ação do goleiro, simplesmente tem uma direção como atributo.
>>>>>>> 7b43b1ca94aae14ed8cd8577777e1e2b67427adb
 */
public class IAGoleiro extends InteligenciaArtificial {

    private Direcao direcao;

    /**
<<<<<<< HEAD
     * Inicialmente a direção que o goleiro está "olhando" é para baixo.
=======
     * Inicialmente a direção que o goleiro está "olhando" é para baixo. 
>>>>>>> 7b43b1ca94aae14ed8cd8577777e1e2b67427adb
     */
    public IAGoleiro() {
        direcao = Direcao.BAIXO;
    }

    /**
<<<<<<< HEAD
     * Método responsável por fazer com que o goleiro aja. Recebe como
     * parâmetro uma ação e uma lista de atores que a colidem. A sua
     * movimentação é definida apenas alterando de direção.
     *
     * @param chamador JogadorActor que chamou a açãoo.
=======
     * Método responsável por fazer com que o goleiro aja. Recebbe como parâmetro
     * uma ação e uma lista de atores que a colidem. A sua movimentação é definida apenas alterando de direção.
     *
     * @param chamador JogadorActor que chamou a ação.
>>>>>>> 7b43b1ca94aae14ed8cd8577777e1e2b67427adb
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
