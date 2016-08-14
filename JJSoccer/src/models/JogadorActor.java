package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import models.interfaces.Action;
import java.util.List;
import java.util.Random;
import models.basics.Habilidades;
import models.basics.Jogador;
import models.comportamentoAtuar.ComportamentoAtuar;
import models.comportamentoAtuar.Player1;
import models.comportamentoAtuar.IAGoleiro;
import models.comportamentoAtuar.IAJogador;
import models.comportamentoAtuar.Player2;
import models.interfaces.PlayerListener;

/**
 * JogadorActor é um ator, portanto é a classe que define a lógica reservada
 * para os jogadores. Possui um atributo da classe Jogador, apenas para
 * referenciá-lo. Métodos como a sua movimentação, verificação de colisão,
 * controle da direção são idênticos à qualquer ator, por este motivo herda de
 * actor.
 */
public class JogadorActor extends Actor {

    /**
     * Enumerador que serve para definir o comportamento de um jogador. Os
     * comportamentos disponíveis são as IAs de goleiro ou jogador comum, e
     * player 1 e 2, que são controlados pelo usuário.
     */
    public enum Comportamentos {
        GOLEIRO_IA, JOGADOR_IA, PLAYER_1, PLAYER_2;
    }

    private Jogador jogador;
    private ComportamentoAtuar comportamento;

    /**
     * Construtor que recebe como parâmetro o tipo de comportamento do jogador e
     * as suas posições x e y. Este comportamento passado define se o jogador
     * será controlado por uma IA ou por um usuário. Se for por uma IA, deve ser
     * definido se é um goleiro ou um jogador comum. É definido o sprite do
     * jogador como a imagem "soccer.png" presente no pacote "resources".
     *
     * @param comportamento comportamento do jogador (GOLEIRO_IA, JOGADOR_IA,
     * PLAYER_1 ou PLAYER_2).
     * @param x posição x.
     * @param y posição y.
     */
    public JogadorActor(Comportamentos comportamento, int x, int y) {
        super(x, y);
        setComportamento(comportamento);
        jogador = new Jogador(nomeAleatorio());
        sprite = new Sprite("soccer.png");
        speedPixel = ((jogador.getHabilidade(Habilidades.habilidade.Velociadade) * 3) / 100) + 1;
    }

    /**
     * Construtor que recebe como parâmetro apenas o tipo de comportamento do
     * jogador. Este comportamento passado define se o jogador será controlado
     * por uma IA ou por um usuário. Se for por uma IA, deve ser definido se é
     * um goleiro ou um jogador comum. É definido o sprite do jogador como a
     * imagem "soccer.png" presente no pacote "resources".
     *
     * @param comportamento comportamento do jogador (GOLEIRO_IA, JOGADOR_IA,
     * PLAYER_1 ou PLAYER_2).
     */
    public JogadorActor(Comportamentos comportamento) {
        super();
        setComportamento(comportamento);
        jogador = new Jogador(nomeAleatorio());
        sprite = new Sprite("soccer.png");
        speedPixel = ((jogador.getHabilidade(Habilidades.habilidade.Velociadade) * 3) / 100) + 1;
    }

    /**
     * Método responsável por alterar o comportamento de um jogador.
     *
     * @param comportamento novo comportamento.
     * @param listener ouvinte.
     */
    @Deprecated
    public void setComportamento(Comportamentos comportamento, PlayerListener listener) {
        if (comportamento == Comportamentos.JOGADOR_IA) {
            this.comportamento = new IAJogador(listener);
        }
    }

    /**
     * Método responsável por alterar o comportamento de um jogador.
     *
     * @param comportamento novo comportamento.
     */
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

    /**
     *Método responsável pela ação do jogador, a cada gameloop. 
     * @param action dados necessários para a ação.
     * @param collisions lista de atores que colidem com esse.
     */
    @Override
    public void act(Action action, List<Actor> collisions) {
        comportamento.agir(this, action, collisions);
    }

    /**
     * Método responsável pelo retorno da imagem do jogador, é criado um BufferedImage e desenhado a imagem
     * com o auxílio da classe Graphics.
     * @return image.
     */
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
            graphics.setColor(Color.DARK_GRAY);

        graphics.drawString("" + jogador.getNome(), 0, 10);

        graphics.drawImage(super.getImage(), 0, 10, null);
        graphics.dispose();
        return edit;
    }

    /**
     * Método responsável pela movimentação do jogador. Retorna se foi possível mover ou não. Recebe
     * como parâmetros a direção que deve ser seguida, um polígono represetando o limite e uma lista
     * de atores que colidem.
     * @param direcao direção. 
     * @param limite limite.
     * @param collisions lista de atores que colidem.
     * @return verdadeiro se moveu, falso caso contrário.
     */
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

    /**
     * Método que realiza a ação de um jogador empurrar a bola, é recebido a direção e uma lista de atores
     * que colidem, caso o ator que esteja colidindo com o jogador, seja a bola, esta então
     * recebe a ação de ser empurrada, de acordo com o a velocidade definida pelo jogador.
     * @param direcao direção.
     * @param collisions lista de atores que colidem.
     */
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

    private String nomeAleatorio() {
        String nomes = "Dionísio\n"
                + "Malafaia\n"
                + "Vanderlei\n"
                + "Teixeira\n"
                + "Matias\n"
                + "Arouca\n"
                + "Gláucio\n"
                + "Abranches\n"
                + "Saraiva\n"
                + "Eloi\n"
                + "Poças\n"
                + "Xavier\n"
                + "Barata\n"
                + "Ulrico\n"
                + "Guerra\n"
                + "Dinarte\n"
                + "Benedito\n"
                + "Roriz\n"
                + "Cristóvão\n"
                + "Rebotim\n"
                + "Bernardino\n"
                + "Candeias\n"
                + "Jaime\n"
                + "Onofre\n"
                + "Gil\n"
                + "Carvalhal\n"
                + "Quitério\n"
                + "Figueiroa\n"
                + "Levi\n"
                + "Brás\n"
                + "Mauro\n"
                + "Andrade\n"
                + "Abraão\n"
                + "Castanheira\n"
                + "Marco\n"
                + "André\n"
                + "Leticia\n"
                + "Costa\n"
                + "Chateaubriand\n"
                + "Tsuchiya\n"
                + "Matheus\n"
                + "Osmar\n"
                + "Maycon\n"
                + "Diego\n"
                + "Caio\n"
                + "Felipe\n"
                + "Daniel\n"
                + "Norival\n"
                + "Antonio\n"
                + "Highlander\n"
                + "Julio\n"
                + "Dilson\n";
        
        String[] result = nomes.split("\\n");
        Random rand = new Random();
        return result[rand.nextInt(result.length)];
    }
}
