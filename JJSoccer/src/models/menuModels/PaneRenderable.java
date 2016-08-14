/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package models.menuModels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import models.interfaces.Renderable;

/**
 * Representa um painel verde escuro que ocupa a tela
 */
public class PaneRenderable implements Renderable {

    private BufferedImage imagem;

    /**
     * Inicializa o PaneRenderable com sua imagem ocupando da posicao 0,0 até o
     * width e height, tendo como objetivo o width e height serem a largura e
     * altura da tela.
     *
     * @param width largura da tela
     * @param height altura da tela
     */
    public PaneRenderable(int width, int height) {
        imagem = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) imagem.getGraphics();
        graphics.setColor(new Color(10, 200, 0));
        graphics.fillRect(0, 0, width, height);
    }

    /**
     *
     * @return a imagem a ser desenhada
     */
    @Override
    public Image getImage() {
        return imagem;
    }

    /**
     *
     * @return posicao X a ser desenhada, sempre em 0
     */
    @Override
    public int getX() {
        return 0;
    }

    /**
     *
     * @return posicao Y a ser desenhada, sempre em 0
     */
    @Override
    public int getY() {
        return 0;
    }

}
