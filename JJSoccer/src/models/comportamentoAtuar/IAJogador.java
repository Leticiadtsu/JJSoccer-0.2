/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.comportamentoAtuar;

import java.util.List;
import java.util.Random;
import models.Actor;
import models.Actor.Direcao;
import models.JogadorActor;
import models.interfaces.Action;

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

    public IAJogador() {

        voltarPosicaoOriginal = true;
        direcao = gerarDirecaoAleatria();
    }

    @Override
    public void agir(JogadorActor chamador, Action action, List<Actor> collisions) {
        if (chamador.getX() == chamador.getXInicial() && chamador.getY() == chamador.getYInicial()) {
            voltarPosicaoOriginal = false;
        }
        if (voltarPosicaoOriginal) {
            if (chamador.getX() > chamador.getXInicial()) {
                chamador.mover(Direcao.ESQUERDA, action.getLimite(), collisions);
            } else if (chamador.getX() < chamador.getXInicial()) {
                chamador.mover(Direcao.DIREITA, action.getLimite(), collisions);
            }
            if (chamador.getY() > chamador.getYInicial()) {
                chamador.mover(Direcao.CIMA, action.getLimite(), collisions);
            } else if (chamador.getY() < chamador.getYInicial()) {
                chamador.mover(Direcao.BAIXO, action.getLimite(), collisions);
            }

        } else {
            escolherDirecao(chamador);
            chamador.mover(direcao, action.getLimite(), collisions);
        }
    }

    private void escolherDirecao(JogadorActor chamador) {

        if (chamador.getY() > chamador.getYInicial() + RANGE || chamador.getY() < chamador.getYInicial() - RANGE || chamador.getX() > chamador.getXInicial() + RANGE || chamador.getX() < chamador.getXInicial() - RANGE) {
            direcao = gerarDirecaoAleatria();
            voltarPosicaoOriginal = true;
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
}
