package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import models.interfaces.Renderable;

/**
 *
 * @author 
 */
public class Campo implements Renderable{

    private final Dimensao dimencao;
    private final Image imagem;

    public Campo(int largura, int altura) {
        dimencao = new Dimensao(largura, altura);
        BufferedImage ground = new BufferedImage(dimencao.getLargura(),dimencao.getAltura(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics =  ground.getGraphics();
        graphics.setColor(Color.green);
        graphics.fillRect(0, 0, dimencao.getLargura(), dimencao.getAltura());
        imagem = ground;
    }

    public Dimensao getDimencao() {
        return dimencao;
    }
    
    @Override
    public Image getImage() {
        return  imagem;
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
