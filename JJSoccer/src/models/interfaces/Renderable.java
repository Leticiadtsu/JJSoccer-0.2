package models.interfaces;

import java.awt.Image;

/**
 * O Renderable é uma interface responsável por definir a imagem e a posição
 * dessa na cena. A Visão não tem conhecimento de nenhuma classe dos modelos e
 * do controle, conhecendo apenas Renderables, * pois para a visão só importa
 * qual imagem deve ser desenhada e aonde esse imagem deve ser desenhada.
 */
public interface Renderable {

    /**
     *
     * @return a imagem a ser rederizada
     */
    public Image getImage();

    /**
     *
     * @return posicao X a ser renderizada
     */
    public int getX();

    /**
     *
     * @return posicao Y a ser renderizada
     */
    public int getY();
}
