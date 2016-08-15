package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Renderable;

/**
 * Classe responsável pela definição do campo utilizado no jogo. É uma classe
 * que será renderizada, portanto implementa a interface Renderable. Possui uma
 * imagem que a define, suas dimensões, um polígono que a determina e sua posição.
 */
public class Campo implements Renderable {

    private final Dimensao dimencao;
    private final Image imagem;
    private final Polygon poligono;
    private final Point posicao;

    /**
     * Construtor do campo que recebe como parâmetro a sua posição, dimensão (altura e largura),
     * e a dimensão do gol. É determinado todas as informações do campo, como o seu tamanho e o
     * tamanho do gol. 
     * @param posicao posição do campo.
     * @param dimencao dimensão do campo.
     * @param tamanhoDoGol dimensão do gol.
     */
    public Campo(Point posicao, Dimensao dimencao, Dimensao tamanhoDoGol) {
        this.dimencao = dimencao;
        Dimensao gol = tamanhoDoGol;
        Dimensao quadradoCampo = new Dimensao(dimencao.getLargura(), dimencao.getAltura());
        this.posicao = posicao;

        int leftXcampo = gol.getLargura();
        int rightXcampo = quadradoCampo.getLargura() - gol.getLargura();

        int topYcampo = 0;
        int bottomYcampo = quadradoCampo.getAltura();

        int leftXtela = 0;
        int rightXtela = quadradoCampo.getLargura();

        int topYgol = quadradoCampo.getAltura() / 2 - gol.getAltura() / 2;
        int bottomYgol = quadradoCampo.getAltura() / 2 + gol.getAltura() / 2;

        poligono = new Polygon();
        //limite superior do campo
        poligono.addPoint(leftXcampo, topYcampo);
        poligono.addPoint(rightXcampo, topYcampo);
        //Gol direito
        poligono.addPoint(rightXcampo, topYgol);
        poligono.addPoint(rightXtela, topYgol);
        poligono.addPoint(rightXtela, bottomYgol);
        poligono.addPoint(rightXcampo, bottomYgol);
        //limite inferior do campo
        poligono.addPoint(rightXcampo, bottomYcampo);
        poligono.addPoint(leftXcampo, bottomYcampo);
        //gol esquerdo
        poligono.addPoint(leftXcampo, bottomYgol);
        poligono.addPoint(leftXtela, bottomYgol);
        poligono.addPoint(leftXtela, topYgol);
        poligono.addPoint(leftXcampo, topYgol);
        poligono.translate(posicao.x, posicao.y);

        BufferedImage ground = new BufferedImage(dimencao.getLargura() + posicao.x, dimencao.getAltura() + posicao.y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) ground.getGraphics();
        graphics.setColor(new Color(0, true));
        graphics.fillRect(0, 0, dimencao.getLargura() + posicao.x, dimencao.getAltura() + posicao.y);

        graphics.setColor(Color.green);
        graphics.fillPolygon(poligono);
        graphics.setColor(Color.WHITE);
        graphics.drawPolygon(poligono);
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(Color.WHITE);
        graphics.drawRect(leftXcampo + posicao.x, topYcampo + posicao.y, (dimencao.getLargura() - 2 * gol.getLargura()), (this.dimencao.getAltura()));
        graphics.drawRect(posicao.x + (dimencao.getLargura() / 2), topYcampo + posicao.y, 1, (this.dimencao.getAltura()));
        graphics.fillOval(posicao.x + (dimencao.getLargura() / 2) - 10, posicao.y + (dimencao.getAltura() / 2) - 10, 20, 20);
        int raio = 80;
        graphics.drawOval(posicao.x + (dimencao.getLargura() / 2) - raio, posicao.y + (dimencao.getAltura() / 2) - raio, 2*raio, 2*raio);
        graphics.dispose();
        imagem = ground;

    }

    /**
     * Verifica se o retângula passado como parâmetro, estácontido no polígono.
     * @param rect
     * @return Verdadeiro ou falso.
     */
    public boolean isInside(Rectangle rect) {
        return !poligono.contains(rect);
    }

    /**
     * Retorna o polígono correspondente ao limite do campo.
     * @return polígono correspondente ao limite do campo.
     */
    public Polygon getLimite() {
        return poligono;
    }

    /**
     * Retorna a dimensão do campo.
     *
     * @return dimensão do campo.
     */
    @Deprecated
    public Dimensao getDimencao() {
        return dimencao;
    }

    /**
     * Retorna a imagem do campo.
     *
     * @return imagem do campo.
     */
    @Override
    public Image getImage() {
        return imagem;
    }

    /**
     * Retorna um valor correspondente a posição X do campo, que sempre será -20.
     * @return -20.
     */
    @Override
    public int getX() {
        return -20;
    }

    /**
     * Retorna um valor correspondente a posição Y do campo, que sempre será 0.
     * @return 0.
     */
    @Override
    public int getY() {
        return 0;
    }

}
