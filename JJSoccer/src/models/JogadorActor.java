package models;

import controller.managers.InputManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import models.interfaces.Action;
import java.util.List;
import models.basics.Habilidades;
import models.basics.Jogador;
import models.comportamentoAtuar.ComportamentoAtuar;
import models.comportamentoAtuar.Player1;
import models.comportamentoAtuar.IAGoleiro;
import models.comportamentoAtuar.IAJogador;
import models.comportamentoAtuar.Player2;
import models.interfaces.PlayerListener;

/**
 *
 * @author
 */
public class JogadorActor extends Actor {

    public enum Comportamentos {
        GOLEIRO_IA, JOGADOR_IA, PLAYER_1, PLAYER_2;
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

    public void setComportamento(Comportamentos comportamento, PlayerListener listener){
        if(comportamento ==Comportamentos.JOGADOR_IA){
            this.comportamento = new IAJogador(listener);
        }
    }
    public void setComportamento(Comportamentos comportamento) {
        switch (comportamento) {
            case GOLEIRO_IA:
                this.comportamento = new IAGoleiro();
                break;
            case JOGADOR_IA:
                this.comportamento = new IAJogador();
                break;
            case PLAYER_1:
                this.comportamento = new Player1();
                break;
            case PLAYER_2:
                this.comportamento = new Player2();
        }
    }

    @Override
    public void act(Action action, List<Actor> collisions) {
        comportamento.agir(this, action, collisions);
    }

    @Override
    public Image getImage() {

        BufferedImage edit = new BufferedImage(super.getImage().getWidth(null), super.getImage().getHeight(null) + 20, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = edit.getGraphics();
        if (comportamento instanceof Player1) {
            graphics.setColor(Color.red);
            graphics.fillOval(10, 60, 50, 50);
        } else if (comportamento instanceof Player2) {
            graphics.setColor(Color.blue);
            graphics.fillOval(10, 60, 50, 50);
        }

        graphics.drawString("" + speedPixel, (super.getImage().getWidth(null) / 2) / 2, 10);

        graphics.drawImage(super.getImage(), 0, 0, null);
        graphics.dispose();
        return edit;
    }

    public boolean mover(Direcao direcao, Polygon limite, List<Actor> collisions) {
        boolean moveu = false;
        if (null != direcao) {
            switch (direcao) {
                case CIMA:
                    moveu = move(0, -1, limite, collisions);
                    break;
                case BAIXO:
                    moveu = move(0, 1, limite, collisions);
                    break;
                case ESQUERDA:
                    moveu = move(-1, 0, limite, collisions);
                    break;
                case DIREITA:
                    moveu = move(1, 0, limite, collisions);
                    break;
                default:
                    break;
            }
        }
        empurrarBola(direcao, collisions);
        return moveu;
    }

    public void empurrarBola(Direcao direcao, List<Actor> collisions) {
        for (Actor actor : collisions) {
            if (this.isColliding(actor) && actor instanceof Bola) {
                switch (direcao) {
                    case CIMA:
                        ((Bola) actor).receberAcao(0 * speedPixel, -1 * speedPixel);
                        break;
                    case BAIXO:
                        ((Bola) actor).receberAcao(0 * speedPixel, 1 * speedPixel);
                        break;
                    case ESQUERDA:
                        ((Bola) actor).receberAcao(-1 * speedPixel, 0 * speedPixel);
                        break;
                    case DIREITA:
                        ((Bola) actor).receberAcao(1 * speedPixel, 0 * speedPixel);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "jogador " + this.jogador.getNome();
    }
}
