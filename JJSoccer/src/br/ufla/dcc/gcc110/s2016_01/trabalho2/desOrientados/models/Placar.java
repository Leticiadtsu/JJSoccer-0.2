package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Renderable;

/**
 * Classe que representa o placar no jogo. Por ser um objeto que será impresso na tela, herda de Renderable.
 * Possui sua posição x e y que não serão alteradas durante a execução, os valores correspondentes aos gols 
 * dos dois times da partida, o nome desses times, a imagem e uma fonte para o texto.
 */
public class Placar implements Renderable {

    private final int x;
    private final int y;
    private int golsTime1;
    private int golsTime2;
    private String nomeTime1;
    private String nomeTime2;
    private int ImageWidth;
    private BufferedImage image;
    private Font font;

    /**
     * Construtor que recebe como parâmetro o as posições x e y do placar e os nomesdos times.
     * Os gols são inicializados em 0 , a fonte utilizada é definida em Sans Serif com tamanho 32, em negrito.
     * E é feito as definições da image.
     * @param x posição x.
     * @param y posição y.
     * @param nomeTime1 nome do time 1.
     * @param nomeTime2 nome do time 2.
     */
    public Placar(int x, int y, String nomeTime1, String nomeTime2) {
        this.y = y;
        golsTime1 = 0;
        golsTime2 = 0;
        this.nomeTime1 = nomeTime1;
        this.nomeTime2 = nomeTime2;
        int fontSize = 32;
        font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        ImageWidth = (fontSize / 2) * (nomeTime1.length() + nomeTime2.length() + 10);
        image = new BufferedImage(ImageWidth, 80, BufferedImage.TYPE_INT_ARGB_PRE);
        updateImage();
        this.x = ((Toolkit.getDefaultToolkit().getScreenSize().width - ImageWidth) / 2) - 10;
    }

    /**
     * Atualização da imagem utilizando as classes BufferedImage e Graphics, definições de como será desenhado o placar.
     */
    private void updateImage() {
        image = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        //graphics.setColor(Color.LIGHT_GRAY);
        //graphics.fillRect(0, 0, ImageWidth, 80);
        graphics.setColor(Color.DARK_GRAY);
        graphics.setFont(font);
        graphics.drawString(nomeTime1 + " " + golsTime1 + " x " + golsTime2 + " " + nomeTime2, 10, 50);
        graphics.dispose();

    }

    /**
     * Adiciona gol para o time 1 e atualiza o placar.
     */
    public void addGolTime1() {
        golsTime1++;
        updateImage();
    }

    
    /**
     * Adiciona gol para o time 2 e atualiza o placar.
     */
    public void addGolTime2() {
        golsTime2++;
        updateImage();
    }

    /**
     * Retorna a quantidade de gols do time 1.
     * @return quantidade de gols do time 1.
     */
    public int getGolTime1() {
        return this.golsTime1;
    }
    
    /**
     * Retorna a quantidade de gols do time 2.
     * @return quantidade de gols do time 2.
     */
    public int getGolTime2() {
        return this.golsTime2;
    }

    /**
     * Retorna a imagem do placar.
     * @return imagem do placar.
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Retorna a posição x do placar.
     * @return posição x do placar.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Retorna a posição y do placar.
     * @return posição y do placar.
     */
    @Override
    public int getY() {
        return y;
    }

}
