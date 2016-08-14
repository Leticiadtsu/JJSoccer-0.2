package models;

import controller.managers.InputManager;
import java.awt.Graphics;
import models.interfaces.Action;
import models.interfaces.Renderable;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa todos os objetos que realizam uma ação no jogo
 * e portanto toda a lógica do jogo. Actor vem do sentido de atuar. Resonsável
 * por alguns aspectos importantes do jogo, tais como, as posições dos objetos
 * na tela, colisão e movimentação.
 */
public abstract class Actor implements Renderable, Comparable<Actor> {

    /**
     * Alteta a área de colisão do ator.
     *
     * @param collisionArea nova área de colisão.
     */
    protected void setCollisionArea(CollisionArea collisionArea) {
        this.collisionArea = collisionArea;
    }

    /**
     * Definição das direções para qual um objeto, jogador ou bola, pode seguir.
     */
    public enum Direcao {
        CIMA, BAIXO, DIREITA, ESQUERDA
    }

    protected Sprite sprite;
    private Direcao direcao;
    private CollisionArea collisionArea;
    protected int speedPixel;
    private int x;
    private int y;
    private int XInicial;
    private int YInicial;

    /**
     * Construtor de Actor que não recebe nenhum parâmetro, as posições x e y
     * são inicializadas em zero, a sua velocidade em pixel é definida em 3, a
     * direção inicializada para a direita e é definido a sua área de colisão
     * com altura e largura em 40.
     */
    public Actor() {
        x = 0;
        y = 0;
        XInicial = 0;
        YInicial = 0;
        direcao = Direcao.DIREITA;
        collisionArea = new CollisionArea(40, 40);
        speedPixel = 3;
    }

    /**
     * Construtor que recebe como parâmetro as posições x e y, todos os outros
     * atributos são definidos como oconstrutor que não possui parâmetros.
     *
     * @param x posição x do objeto.
     * @param y posição y do objeto.
     */
    public Actor(int x, int y) {
        this();
        this.x = x;
        this.y = y;
        XInicial = x;
        YInicial = y;
    }

    /**
     * Retorna a área de colisão do objeto.
     *
     * @return a área de colisão.
     */
    protected CollisionArea getCollisionArea() {
        return collisionArea;
    }

    /**
     * Define a ação do ator a cada gameLoop. Deve ser implementada pelas
     * classes que implementam Actor.
     *
     * @param action dados necessários para a ação.
     * @param areaDeRelevancia lista de atores que colidem com esse.
     */
    public abstract void act(Action action, List<Actor> areaDeRelevancia);

    /**
     * Verifica se a posição desejada é válida ou não.
     *
     * @param nextX o valor X da posição desejada.
     * @param nextY o valor Y da posição desejada.
     * @param limite quadro de limite do campo.
     * @param collisions atores que colidem com esse ator na cena.
     * @return se a posicao é válida ou não.
     */
    protected boolean canMove(int nextX, int nextY, Polygon limite, List<Actor> actorNears) {
        Rectangle r = new Rectangle(nextX, nextY, collisionArea.getLargura(), collisionArea.getAltura());
        if ((!limite.contains(r) && (limite.intersects(r)))) {
            return false;

        }
        Actor futuraPosicao = new ActorBasic(nextX, nextY);
        for (Actor atorAlvo : actorNears) {
            if (this != atorAlvo && !(atorAlvo instanceof Bola) && !(atorAlvo instanceof Gol)) {
                if (collisionArea.isColliding(futuraPosicao, atorAlvo)) {
                    return false;
                }
            }
        }

        // Caso não  há nenhum impedimento
        return true;
    }

    /**
     * Verifica se a posição do ator é válida.
     *
     * @param limite quadro de limite do campo.
     * @param areaRelevante atores que colidem com esse ator na cena.
     * @return se a posição é válida ou não.
     */
    protected boolean isValidPosition(Polygon limite, List<Actor> areaRelevante) {
        return (canMove(x, y, limite, areaRelevante));
    }

    /**
     * Move o actor em direção as coordenadas passadas por parâmetro.
     *
     * @param horizontal coordenada Y.
     * @param vertical coordenada X.
     * @param limite quadro de limite do campo.
     * @param collisions atores que colidem com esse ator na cena.
     */
    protected boolean move(int horizontal, int vertical, Polygon limite, List<Actor> collisions) {
        //colisao com outros Jogadores

        if (canMove(x + speedPixel * horizontal, y + speedPixel * vertical, limite, collisions)) {
            setX(x + speedPixel * horizontal);
            setY(y + speedPixel * vertical);
            setDirecao(horizontal, vertical);
            return true;
        }
        return false;
    }

    /**
     * Altera a direção de acordo com o valor passado por parâmetro.
     *
     * @param horizontal eixo x.
     * @param vertical eixo y.
     */
    private void setDirecao(int horizontal, int vertical) {
        if (horizontal > 0) {
            direcao = Direcao.DIREITA;
        }
        if (horizontal < 0) {
            direcao = Direcao.ESQUERDA;
        }
        if (vertical > 0) {
            direcao = Direcao.BAIXO;
        }
        if (vertical < 0) {
            direcao = Direcao.CIMA;
        }
    }

    /**
     * Retorna a posição x inicial do ator.
     *
     * @return posição x inicial.
     */
    public int getXInicial() {
        return XInicial;
    }

    /**
     * Retorna a posição y inicial do ator.
     *
     * @return posição y inicial.
     */
    public int getYInicial() {
        return YInicial;
    }

    /**
     * Retorna a direção para qual o ator está "olhando".
     *
     * @return direção que o ator está 'olhando'
     */
    public Direcao getDirecao() {
        return direcao;
    }

    @Override
    public int compareTo(Actor actor) {
        return (this.getY() - actor.getY());
    }

    /**
     * Verifica se o ator alvo colide com esse ator
     *
     * @param actor ator alvo
     * @return se o ator alvo colide com esse ator
     */
    public boolean isColliding(Actor actor) {
        return (collisionArea.isColliding(this, actor));
    }

    /**
     * Verificar se o ator passado como parâmetro está próximo.
     *
     * @param actor ator em questão.
     * @return verdadeiro se está próximo, falso caso contrário.
     */
    public boolean isNear(Actor actor) {
        return (collisionArea.isNear(this, actor));
    }

    /**
     * Retorna a posição x.
     *
     * @return posição x.
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna a posição y.
     *
     * @return posição y.
     */
    public int getY() {
        return y;
    }

    /**
     * Retorna o sprite.
     *
     * @return sprite.
     */
    protected Sprite getSprite() {
        return sprite;
    }

    /**
     * Altera o sprite, recebendo como parâmetro um novo sprite.
     *
     * @param spr sprite.
     */
    public void setSpr(Sprite spr) {
        this.sprite = spr;
    }

    /**
     * Altera a posição x.
     *
     * @param x
     */
    protected void setX(int x) {
        this.x = x;
    }

    /**
     * Altera a posição y
     *
     * @param y
     */
    protected void setY(int y) {
        this.y = y;
    }

    /**
     * Retorna a imagem do ator, com a criação de um BufferedImage, a qual
     * recebe largura, altura e o tipo da imagem.
     *
     * @return image.
     */
    public Image getImage() {
        if (InputManager.getInstance().isPressed(KeyEvent.VK_F5)) {
            BufferedImage edit = new BufferedImage(getCollisionArea().getLargura(), getCollisionArea().getAltura(), BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = edit.getGraphics();
            graphics.fillRect(0, 0, getCollisionArea().getLargura(), getCollisionArea().getLargura());
            return edit;
        }
        return getSprite().getImage();

    }

    /**
     * Retorna uma lista de atores que estão colidindo com o ator em questão.
     *
     * @param actor ator em questão.
     * @param areaRelevancia possíveis atores que podem estar colidindo.
     * @return lista de atores que estao colidindo.
     */
    private List<Actor> getCollisionsOn(Actor actor, List<Actor> areaRelevancia) {
        List collisions = new ArrayList<Actor>();
        for (Actor atorVerificado : areaRelevancia) {
            if (actor.isColliding(atorVerificado)) {
                collisions.add(atorVerificado);
            }
        }
        return collisions;
    }

    /**
     * Método responsável por resetar o ator para sua posição inicial, alterando
     * sua posição x e y, passando como parâmetro os valores da sua posição
     * inicial.
     */
    public void reset() {
        setX(XInicial);
        setY(YInicial);
    }

}
