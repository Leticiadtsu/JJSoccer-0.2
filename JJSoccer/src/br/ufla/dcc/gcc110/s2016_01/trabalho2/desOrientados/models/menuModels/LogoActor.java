/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.menuModels;

import java.util.List;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Sprite;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;

/**
 * Classe que representa a logo desenhada no MenuScene
 *
 */
public class LogoActor extends Actor {

    private boolean onPosition;
    private int speed;
    private int expectedY;

    /**
     * Inicia a imagem na posicao X, e inicia sua animacao ate a posicao Y
     *
     * @param x Posicao X final da imagem
     * @param y Posicao Y final da imagem
     * @param speed Velocidade da animacao ate a imagem alcancar o X final
     */
    public LogoActor(int x, int y, int speed) {
        super(x, -300);
        setSpr(new Sprite("JJSoccer.png"));
        onPosition = false;
        this.speed = speed;
        expectedY = y;
    }

    /**
     * Acao da Logo. Responsavel por sua animacao. Faz a imagem percorrer o
     * caminho até alcancar a posicao Y desejada.
     *
     * @param action Nao utilizado no botao
     * @param areaDeRelevancia Nao utilizado no botao
     */
    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        if (!onPosition) {
            setY(speed + getY());
            if (expectedY <= getY()) {
                onPosition = true;
            }
        }
    }

    /**
     * @return verifica se a imagem ja alcançou a posicao Y desejada
     */
    protected boolean isOnPosition() {
        return onPosition;
    }

}
