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
 *
 * @author Andre Chateaubriand
 */
public class PaneRenderable implements Renderable {

    private BufferedImage imagem;

    public PaneRenderable(int width, int height) {
        imagem = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) imagem.getGraphics();
        graphics.setColor(new Color(0, 128, 0));
        graphics.fillRect(0, 0, width, height);
    }

    @Override
    public Image getImage() {
        return imagem;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

}
