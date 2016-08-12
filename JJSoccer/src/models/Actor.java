/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models;

import models.interfaces.Action;
import models.interfaces.Renderable;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author
 */
public abstract class Actor implements Renderable, Comparable<Actor> {

    public enum Direcao {
        CIMA, BAIXO, DIREITA, ESQUERDA
    }

    protected Sprite sprite;
    private Direcao direcao;
    private CollisionArea collisionArea;
    protected int speedPixel;
    private int x;
    private int y;

    public Actor() {
        x = 0;
        y = 0;
        direcao = Direcao.DIREITA;
        collisionArea = new CollisionArea(40, 40);
        speedPixel = 3;
    }

    public Actor(int x, int y) {
        this();
        this.x = x;
        this.y = y;

    }

    /**
     * @return the collision
     */
    protected CollisionArea getCollisionArea() {
        return collisionArea;
    }

    /**
     * define a acao do ator a cada gameLoop
     *
     * @param action dados necessarios para a acao
     * @param collisions lista de atores que colidem com esse
     */
    public abstract void act(Action action, List<Actor> collisions);

    /**
     * Verifica se a posicao desejada eh valida ou nao
     *
     * @param nextX o valor X da posicao desejada
     * @param nextY o valor Y da posicao desejada
     * @param limite quadro de limite do campo
     * @param collisions atores que colidem com esse ator na cena
     * @return se a posicao eh valida ou nao
     */
    protected boolean canMove(int nextX, int nextY, Dimensao limite, List<Actor> collisions) {
        //limites superiores do campo
        if (nextX < 0 || nextY < 0) {
            return false;
        }
        //limites inferiores do campo
        if (nextX > limite.getLargura() || nextY > limite.getAltura()) {
            return false;
        }

        for (Actor collision : collisions) {
            if (collision instanceof JogadorActor) {
                return false;
            }
        }
        // Caso não  há nenhum impedimento
        return true;
    }

    /**
     * Verifica se a posicao do ator eh valida
     *
     * @param limite quadro de limite do campo
     * @param collisions atores que colidem com esse ator na cena
     * @return se a posicao eh valida ou nao
     */
    protected boolean isValidPosition(Dimensao limite, List<Actor> collisions) {
        return (canMove(x, y, limite, collisions));
    }

    /**
     * Move o actor em direcao as coordenadas passadas por parametro
     *
     * @param horizontal coordenada Y
     * @param vertical coordenada X
     * @param limite
     * @param collisions
     */
    protected void move(int horizontal, int vertical, Dimensao limite, List<Actor> collisions) {
        //colisao com outros Jogadores

        if (canMove(x + speedPixel * horizontal, y + speedPixel * vertical, limite, collisions)) {
            setX(x + speedPixel * horizontal);
            setY(y + speedPixel * vertical);
            setDirecao(horizontal, vertical);
        }
    }

    private void setDirecao(int horizontal, int vertical) {
        if (horizontal > 0) {
            direcao = Direcao.BAIXO;
        }
        if (horizontal < 0) {
            direcao = Direcao.CIMA;
        }
        if (vertical > 0) {
            direcao = Direcao.DIREITA;
        }
        if (vertical < 0) {
            direcao = Direcao.ESQUERDA;
        }
    }

    /**
     *
     * @return direcao que o ator esta 'olhando'
     */
    public Direcao getDirecao() {
        return direcao;
    }

    @Override
    public int compareTo(Actor o) {
        return (this.getY() - o.getY());
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
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the sprite
     */
    protected Sprite getSprite() {
        return sprite;
    }

    /**
     * @param spr the sprite to set
     */
    protected void setSpr(Sprite spr) {
        this.sprite = spr;
    }

    /**
     * @param x the x to set
     */
    protected void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    protected void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return getSprite().getImage();
        //return new BufferedImage(getCollisionArea().getLargura(), getCollisionArea().getAltura(), BufferedImage.TYPE_INT_RGB);
    }

}
