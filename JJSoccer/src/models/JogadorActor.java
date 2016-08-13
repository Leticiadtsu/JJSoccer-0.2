package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import models.interfaces.Action;
import java.util.List;
import models.basics.Habilidades;
import models.basics.Jogador;
import models.comportamentoAtuar.ComportamentoAtuar;
import models.comportamentoAtuar.Controlado;
import models.comportamentoAtuar.IAGoleiro;
import models.comportamentoAtuar.IAJogador;

/**
 *
 * @author
 */
public class JogadorActor extends Actor {

    public enum Comportamentos {
        GOLEIRO_IA, JOGADOR_IA, CONTROLADO;
    }

    //Remover
    private static int teste = 0;

    private Jogador jogador;
    private ComportamentoAtuar comportamento;
    
    public JogadorActor(Comportamentos comportamento, int x, int y) {
        super(x, y);
        setComportamento(comportamento);
        jogador = new Jogador("" + teste);
        sprite = new Sprite("soccer.png");
        speedPixel = ((jogador.getHabilidade(Habilidades.habilidade.Velociadade) * 3) / 100) + 1;
        teste++;
    }

    public JogadorActor(Comportamentos comportamento) {
        super();
        setComportamento(comportamento);
        jogador = new Jogador("" + teste);
        sprite = new Sprite("soccer.png");
        speedPixel = ((jogador.getHabilidade(Habilidades.habilidade.Velociadade) * 3) / 100) + 1;
        teste++;
    }
    public void setComportamento(Comportamentos comportamento){
        switch(comportamento){
            case GOLEIRO_IA:
                this.comportamento = new IAGoleiro();
                break;
            case JOGADOR_IA:
                this.comportamento = new IAJogador();
                break;
            case CONTROLADO:
                this.comportamento = new Controlado();
                break;
        }
    }
    @Override
    public void act(Action action, List<Actor> collisions) {
        comportamento.agir(this, action, collisions);
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

    public void mover(Direcao direcao, Polygon limite, List<Actor> collisions) {
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

    /**
     * remover depois
     *
     * @return
     */
    @Override
    public String toString() {
        return "jogador" + this.jogador.getNome();
    }
}
