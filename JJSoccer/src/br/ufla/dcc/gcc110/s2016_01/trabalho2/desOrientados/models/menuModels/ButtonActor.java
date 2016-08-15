/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.menuModels;

import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.controller.managers.ImageManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Sprite;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;

/**
 * Classe que representa uma opcao do MenuScene
 *
 */
public class ButtonActor extends Actor {

    private List<ActionListener> listeners;
    private String text;
    private boolean focused;
    private boolean onPosition;
    private int speed;
    private int expectedX;
    private BufferedImage image;
    private boolean ballroll;
    private final int DELAY = 250;
    private long previuousMillis;

    /**
     * Inicia o botao na posicao Y, e inicia sua animacao ateh a posicao X
     *
     * @param x Posicao X final do botao
     * @param y Posicao Y final do botao
     * @param speed Velocidade da animacao ate o botao alcancar o X final
     * @param text Texto exibido no botao
     */
    public ButtonActor(int x, int y, int speed, String text) {
        super(-300, y);
        setSpr(new Sprite("soccerball.png"));
        onPosition = false;
        this.speed = speed;
        expectedX = x;
        focused = false;
        ballroll = false;
        this.text = text;
        listeners = new LinkedList<>();
        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB_PRE);
        updateImage();
        previuousMillis = System.currentTimeMillis();
    }

    /**
     * Acao do botao. Responsavel por sua animacao. Faz o botao percorrer o
     * caminho até alcancar a posicao X desejada, e alterna o desenho da bola
     * caso o botao seja o botao em foco.
     *
     * @param action Nao utilizado no botao
     * @param areaDeRelevancia Nao utilizado no botao
     */
    @Override
    public void act(Action action, List<Actor> areaDeRelevancia) {
        if (!isOnPosition()) {
            setX(speed + getX());
            if (expectedX <= getX()) {
                onPosition = true;
            }
        }
        if (System.currentTimeMillis() - previuousMillis >= DELAY) {
            updateImage();
            previuousMillis = System.currentTimeMillis();
        }
    }

    /**
     * @return verifica se o botao ja alcançou a posicao X desejada
     */
    public boolean isOnPosition() {
        return onPosition;
    }

    /**
     * @return se o botao esta com o foco
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     * @param focused o foco a ser setado
     */
    public void setFocused(boolean focused) {
        this.focused = focused;
        image = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        updateImage();
    }

    /**
     * Adiona um listener que deseja saber quando o botao foi ativado
     *
     * @param listener listener
     */
    public void add(ActionListener listener) {
        listeners.add(listener);
    }

    /**
     * Percorre todos os listeners avisando que o botao foi apertado para que
     * façam sua ação de acordo com esse botao.
     */
    public void ActionPerformed() {
        ActionEvent e = new ActionEvent(this, 0, text);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(e);
        }
    }

    /**
     *
     * @return a imagem do botao a ser desenhada
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Atualiza a imagem do botao, para sua animacao e aparencia da alteracao do
     * foco.
     */
    private void updateImage() {
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(new Color(10, 10, 10));
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        graphics.setColor(new Color(60, 100, 60));
        graphics.drawString(text, 38, 43);
        graphics.setColor(new Color(10, 10, 10));
        graphics.drawString(text, 35, 40);
        if (isFocused()) {
            if (ballroll) {
                ballroll = false;
                graphics.drawImage(ImageManager.getInstance().loadImage("selectionball1.png"), 0, 15, null);
            } else {
                ballroll = true;
                graphics.drawImage(ImageManager.getInstance().loadImage("selectionball2.png"), 0, 15, null);
            }
        }
    }

}
