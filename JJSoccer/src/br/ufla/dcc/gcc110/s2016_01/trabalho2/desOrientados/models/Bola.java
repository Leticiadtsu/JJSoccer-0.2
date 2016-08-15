package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models;

import java.awt.Point;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;
import java.util.List;

/**
 * Classe responsável pela definição da bola utilizada no jogo. Possui a
 * velocidade da bola tanto na posição x como na posição y. Como estende de
 * Actor, é uma classe que será imprimível na tela, por isso a necessidade de
 * ter posição e sprite relacionado a ela.
 */
public class Bola extends Actor {

    private int speedX;
    private int speedY;

    private static Bola instance;

    /**
     * Construtor que não recebe nenhum parâmetro. Inicializa a velocidade da
     * bola em (0,0). É criado um sprite da bola, passando a imagem
     * "soccerball.png" contida no pacote "resources".
     */
    public Bola() {
        speedX = 0;
        speedY = 0;
        sprite = new Sprite("soccerball.png");

    }

    /**
     * Construtor que recebe como parâmetro as coordenadas x e y para a posição
     * da bola, que é passado para o construtor da superclasse Actor. Os outros
     * atributos, velocidade e sprite, são inicializados em 0 e com a imagem
     * "soccerball.png", respectivamente. Note que esse construtor é privado,
     * podendo ser chamado apenas pela própria classe.
     *
     * @param x
     * @param y
     */
    public Bola(int x, int y) {
        super(x, y);
        speedX = 0;
        speedY = 0;
        sprite = new Sprite("soccerball.png");
    }

    /**
     * Método responsável por retornar uma instância da classe Bola. Caso não
     * exista nenhuma instância, a bola é criada, caso contrário, é retornado a
     * instância existente.
     *
     * @param x posição x.
     * @param y posição y.
     * @return
     */
    public static Bola getInstance(int x, int y) {
        if (instance == null) {
            instance = new Bola(x, y);
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Método responsável pelo deseceleramento da bola. Ao se movimentar a pola
     * possui um certo valor para a sua velocidade, que é decrementado por esse
     * método a medida que a bola de movimenta.
     */
    protected void desacelerar() {
        if (speedX > 0) {
            setSpeedX(speedX - 1);
        } else if (speedX < 0) {
            setSpeedX(speedX + 1);
        }
        if (speedY > 0) {
            setSpeedY(speedY - 1);
        } else if (speedY < 0) {
            setSpeedY(speedY + 1);
        }
    }

    /**
     * Altera a velocidade da bola no eixo x.
     *
     * @param speedX velocidade x.
     */
    protected void setSpeedX(int speedX) {
        // if (speedX >= 0) {
        this.speedX = speedX;
        //}
    }

    /**
     * Altera a velocidade da bola no eixo y.
     *
     * @param speedY velocidade y.
     */
    protected void setSpeedY(int speedY) {
        //if (speedY >= 0) {
        this.speedY = speedY;
        // }
    }

    /**
     * A atuação da bola em campo é dado pelo método act (atuar). Para tanto,
     * recebe por parâmetro uma lista de atores que estão o colidindo e uma
     * Action.
     *
     * @param action dados necessários para a ação.
     * @param collisions lista de atores que estão colidindo com a bola.
     */
    public void act(Action action, List<Actor> collisions) {
        if (!action.getLimite().contains(new Point(getX(), getY()))) {
            reset();
        }
        setX(getX() + speedX);
        setY(getY() + speedY);
        desacelerar();

    }

    /**
     * Ao receber uma ação a bola tem suas velocidades modificadas.
     *
     * @param speedX velocidade x.
     * @param speedY velocidade y.
     */
    public void receberAcao(int speedX, int speedY) {
        //System.err.println("X: " + speedX + "Y " + speedY);
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    /**
     * Testes
     *
     * @return
     */
    @Override
    public String toString() {
        return "bola";
    }

    /**
     * Método responsável por resetar a bola para sua posição inicial, alterando
     * sua posição x e y e sua velocidade para 0.
     */
    @Override
    public void reset() {
        super.reset();
        speedX = 0;
        speedY = 0;
    }

    /* public void retornarPosicaoInicial() {
        setX(inicialX);
        setY(inicialY);
        speedX = 0;
        speedY = 0;
    }*/
}
