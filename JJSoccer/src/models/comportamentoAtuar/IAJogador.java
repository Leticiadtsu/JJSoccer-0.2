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
public class IAJogador extends InteligenciaArtificial {

    private Direcao direcao;
    private boolean voltarPosicaoOriginal;

    public IAJogador() {
        direcao = Actor.Direcao.BAIXO;
        voltarPosicaoOriginal = true;
    }

    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> collisions) {
        if (voltarPosicaoOriginal) {
            if (chamador.getX() > chamador.getXInicial()) {
                chamador.mover(Direcao.ESQUERDA, new Dimensao(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), collisions);
            } else if (chamador.getX() < chamador.getXInicial()) {
                chamador.mover(Direcao.DIREITA, new Dimensao(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), collisions);
            }
            if (chamador.getY() > chamador.getYInicial()) {
                chamador.mover(Direcao.CIMA, new Dimensao(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), collisions);
            } else if (chamador.getY() < chamador.getYInicial()) {
                chamador.mover(Direcao.BAIXO, new Dimensao(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), collisions);
            }
            if (chamador.getX() > chamador.getXInicial() && chamador.getY() > chamador.getYInicial()) {
                voltarPosicaoOriginal = false;
            }
        } else {
            escolherDirecao(chamador);
            chamador.mover(direcao, new Dimensao(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), collisions);
        }
    }

    private void escolherDirecao(JogadorActor chamador) {
        if (chamador.getY() > (Toolkit.getDefaultToolkit().getScreenSize().height / 2) + 60) {
            this.direcao = Direcao.CIMA;
        }
        if (chamador.getY() < (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 60) {
            this.direcao = Direcao.BAIXO;
        }
    }
}
