package models;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import models.interfaces.Action;
import java.util.List;
import models.basics.Habilidades;
import models.basics.Jogador;

/**
 *
 * @author
 */
public class JogadorActor extends Actor {

    private Jogador jogador;

    public JogadorActor(int x, int y) {
        super(x, y);
        jogador = new Jogador("Teste");
        sprite = new Sprite("soccer.png");
        speedPixel = ((jogador.getHabilidade(Habilidades.habilidade.Velociadade) * 3) / 100) + 1;
    }

    public JogadorActor() {
        super();
        jogador = new Jogador("Teste");
        speedPixel = ((jogador.getHabilidade(Habilidades.habilidade.Velociadade) * 3) / 100) + 1;
        sprite = new Sprite("soccer.png");

    }

    @Override
    public void act(Action action, List<Actor> collisions) {

    }

    @Override
    public Image getImage() {
        BufferedImage edit = new BufferedImage(super.getImage().getWidth(null), super.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = edit.getGraphics();
        graphics.drawString("" + speedPixel, (super.getImage().getWidth(null) / 2) / 2, 10);
        graphics.setColor(Color.red);
        graphics.drawImage(super.getImage(), 0, 10, null);
        graphics.dispose();

        return edit;
    }

    protected void mover(Direcao direcao, Dimencao limite, List<Actor> collisions) {
        if (null != direcao) {
            switch (direcao) {
                case CIMA:
                    move(0, -1, limite, collisions);
                    break;
                case BAIXO:
                    move(0, 1, limite, collisions);
                    break;
                case ESQUERDA:
                    move(-1, 0, limite, collisions);
                    break;
                case DIREITA:
                    move(1, 0, limite, collisions);
                    break;
                default:
                    break;
            }
        }
    }
}
