/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.comportamentoAtuar;

import controller.managers.GameScene;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import models.Actor;
import models.Actor.Direcao;
import models.Bola;
import models.JogadorActor;
import models.interfaces.Action;
import models.interfaces.PlayerListener;

/**
 *
 * @author costa
 */
public class IAJogador extends InteligenciaArtificial {

    private final int RANGE = 50;
    private final int NUM_DIRECOES = 3;

    Random rand = new Random();
    private Direcao direcao;
    private boolean voltarPosicaoOriginal;
    private List<PlayerListener> listeners;

    public IAJogador(PlayerListener listener) {
        listeners = new ArrayList<>();
        listeners.add(listener);
        voltarPosicaoOriginal = true;
        direcao = gerarDirecaoAleatria();
    }

    public IAJogador() {
        listeners = new ArrayList<>();
        voltarPosicaoOriginal = true;
        direcao = gerarDirecaoAleatria();
    }

    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> actorsNear) {
        Actor bola = proximoBola(actorsNear);
        if (bola != null) {
            seguirBola(chamador, bola, action, actorsNear);
        } else {
            if (chamador.getX() == chamador.getXInicial() && chamador.getY() == chamador.getYInicial()) {
                voltarPosicaoOriginal = false;
            }

            if (voltarPosicaoOriginal) {
                voltarPosicaoOriginal(chamador, action, actorsNear);

            } else {
                escolherDirecao(chamador);
                chamador.mover(direcao, action.getLimite(), actorsNear);
            }
        }
    }

    private Actor proximoBola(List<Actor> actorsNear) {
        for (Actor actor : actorsNear) {
            if (actor instanceof Bola) {
                return actor;
            }
        }
        return null;
    }

    private void seguirBola(JogadorActor chamador, Actor bola, Action action, List<Actor> actorsNear) {
        if (chamador.getX() > bola.getX()) {
            chamador.mover(Direcao.ESQUERDA, action.getLimite(), actorsNear);
        } else if (chamador.getX() < bola.getX()) {
            chamador.mover(Direcao.DIREITA, action.getLimite(), actorsNear);
        }
        if (chamador.getY() > bola.getY()) {
            chamador.mover(Direcao.CIMA, action.getLimite(), actorsNear);
        } else if (chamador.getY() < bola.getY()) {
            chamador.mover(Direcao.BAIXO, action.getLimite(), actorsNear);
        }
    }

    private void escolherDirecao(JogadorActor chamador) {

        if (chamador.getY() > chamador.getYInicial() + RANGE || chamador.getY() < chamador.getYInicial() - RANGE || chamador.getX() > chamador.getXInicial() + RANGE || chamador.getX() < chamador.getXInicial() - RANGE) {
            direcao = gerarDirecaoAleatria();
            voltarPosicaoOriginal = true;
        }
    }

    private void voltarPosicaoOriginal(JogadorActor chamador, Action action, List<Actor> actorsNear) {
        if (chamador.getX() > chamador.getXInicial()) {
            chamador.mover(Direcao.ESQUERDA, action.getLimite(), actorsNear);
        } else if (chamador.getX() < chamador.getXInicial()) {
            chamador.mover(Direcao.DIREITA, action.getLimite(), actorsNear);
        }
        if (chamador.getY() > chamador.getYInicial()) {
            chamador.mover(Direcao.CIMA, action.getLimite(), actorsNear);
        } else if (chamador.getY() < chamador.getYInicial()) {
            chamador.mover(Direcao.BAIXO, action.getLimite(), actorsNear);
        }
    }

    private Direcao gerarDirecaoAleatria() {
        int direcaoAleatoria = rand.nextInt(NUM_DIRECOES);
        switch (direcaoAleatoria) {
            case 0:
                return Actor.Direcao.BAIXO;
            case 1:
                return Actor.Direcao.CIMA;

            case 2:
                return Actor.Direcao.ESQUERDA;
            case 3:
                return Actor.Direcao.DIREITA;

        }
        return Actor.Direcao.DIREITA;
    }

    private void estaComAbola(JogadorActor chamador) {
        for (PlayerListener listener : listeners) {
            listener.onBall(chamador);
        }
    }
}
