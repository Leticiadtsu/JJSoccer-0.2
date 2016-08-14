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
 * Interface que define a Inteligência Artificial dos jogadores que estarão em campo.
 * Onde a lógica de toda a movimentação dos jogadores é definifda.
 */
public class IAJogador extends InteligenciaArtificial {

    private final int RANGE = 50;
    private final int NUM_DIRECOES = 3;

    Random rand = new Random();
    private Direcao direcao;
    private boolean voltarPosicaoOriginal;
    private List<PlayerListener> listeners;

    /**
     * Construtor que recebe como parâmetro um ouvinte, esse ouvinte é então adicionado
     * à uma lista de ouvintes, o atributo voltar para a posição original é inicializado com verdadeiro
     * e a direção é definida de forma aleatória.
     * @param listener ouvinte.
     */
    public IAJogador(PlayerListener listener) {
        listeners = new ArrayList<>();
        listeners.add(listener);
        voltarPosicaoOriginal = true;
        direcao = gerarDirecaoAleatoria();
    }

    /**
     * Construtor que não receber nenhum parâmetro, cria uma lista de ouvintes,
     * o atributo voltar para a posição original é inicializado com verdadeiro
     * e a direção é definida de forma aleatória.
     */
    public IAJogador() {
        listeners = new ArrayList<>();
        voltarPosicaoOriginal = true;
        direcao = gerarDirecaoAleatoria();
    }

    /**
     * Definição da ação agir dos jogadores, para a lógica é feita umas verificações tais como, se o jogador está com a bola,
     * se está próximo da bola, escolher direção, seguir a bola e voltar para a posição original.
     * @param chamador JogadorActor que chamou a ação.
     * @param action dados necessários para a ação.
     * @param actorsNear lista de atores que estão próximos
     */
    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> actorsNear) {
        for (Actor actor : actorsNear) {
            if(chamador.isColliding(actor) && actor instanceof Bola){
                estaComAbola(chamador);
            }
        }
        Actor bola = proximoBola(actorsNear);
        if (bola != null && chamador.getY() < chamador.getYInicial() + RANGE*4 & chamador.getY() > chamador.getYInicial() - RANGE*4 && chamador.getX() < chamador.getXInicial() + RANGE*4 && chamador.getX() > chamador.getXInicial() - RANGE*4 && !voltarPosicaoOriginal) {
            seguirBola(chamador, bola, action, actorsNear);
        } else {
            if (chamador.getX() == chamador.getXInicial() && chamador.getY() == chamador.getYInicial()) {
                voltarPosicaoOriginal = false;
            }

            if (voltarPosicaoOriginal) {
                voltarPosicaoOriginal(chamador, action, actorsNear);

            } else {
                escolherDirecao(chamador);
                if(!chamador.mover(direcao, action.getLimite(), actorsNear)){
                    voltarPosicaoOriginal = true;
                }
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
            direcao = gerarDirecaoAleatoria();
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

    private Direcao gerarDirecaoAleatoria() {
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
