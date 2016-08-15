package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.controller.managers;

import java.applet.AudioClip;
import java.awt.Point;
import java.awt.Polygon;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Actor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Bola;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Action;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Renderable;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.view.Frame;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Campo;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Dimensao;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Gol;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.JogadorActor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.JogadorActor.Comportamentos;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Placar;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.Sprite;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.ChangeSceneListener;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.GolListener;

/**
 * A classe MatchScne é uma cena da partida, responsável por controlar a
 * “jogabilidade” desta. Possui uma lista de atores que compõe a partida, um
 * campo que define o limite do mapa, a bola, os audios, placar, ou seja, todos
 * os aspectos relacionados à uma partida estão declarados nesta classe.
 *
 */
public class MatchScene extends GameScene implements GolListener {

    private int posPlayer1;
    private int posPlayer2;
    private AudioClip audioGol;
    private AudioClip BGM;
    private Frame tela;
    private Campo campo;
    private Bola bola;
    private Point posInicialBola;
    private JogadorActor player1;
    private JogadorActor player2;
    private Placar placar;
    private List<Actor> todos;
    private List<Actor> atoresCasa;
    private List<Actor> atoresVisitantes;
    private List<Actor> atoresProximos;
    private List<ChangeSceneListener> sceneListeners;
    private Action genericAction;//Mudar depois no projeto final
    private long inicioPartida;
    private int tempoDeJogo;
    private boolean tocandoTheme;

    private long ultimaTrocPlayer1;

    /**
     * Construtor que recebe a tela e os ouvintes da cena, determina o tempo de
     * jogo em 10 minutos, cria uma lista de ouvintes da cena, lista de atores
     * da casa e os visitantes. Cria a cena e inicializa o áudio.
     *
     * @param tela tela.
     * @param lisntener ouvinte.
     */
    public MatchScene(Frame tela, ChangeSceneListener lisntener) {
        tocandoTheme = true;
        tempoDeJogo = 10;
        this.tela = tela;
        sceneListeners = new ArrayList<>();
        sceneListeners.add(lisntener);

        posPlayer1 = 0;
        posPlayer1 = 0;
        inicioPartida = System.currentTimeMillis();
        this.tela = tela;
        todos = new ArrayList<>();
        atoresCasa = new ArrayList<>();
        atoresVisitantes = new ArrayList<>();

        genericAction = new Action() {
            @Override
            public Polygon getLimite() {
                return campo.getLimite();
            }
        };
        criarCenario();
        initAudio();

    }

    private void criarCampo(Frame tela) {
        Dimensao tamanhoDoCampo = new Dimensao(tela.getWidth(), tela.getHeight() - 100);
        Dimensao tamanhoDoGol = new Dimensao(80, 200);
        campo = new Campo(new Point(20, 100), tamanhoDoCampo, tamanhoDoGol);
    }

    private void initAudio() {
        long previousMillis = System.currentTimeMillis();
        try {
            BGM = AudioManager.getInstance().loadAudio("Theme2.wav");

            int delay = 1000;
            while (System.currentTimeMillis() - previousMillis < delay) {
            }
            BGM.loop();

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void criarCenario() {

        criarCampo(tela);

        gerarTimeCasa();
        gerarTimeVisitante();
        initPlayers();

        posInicialBola = new Point(tela.getWidth() / 2, tela.getHeight() / 2 + 50);
        bola = new Bola(posInicialBola.x, posInicialBola.y);

        placar = new Placar(tela.getWidth() / 2, 10, "Time da Casa", "Time Visitante");

        todos.add(bola);
        todos.add(new Gol(0, (tela.getHeight() / 2) - 50, this, false));
        todos.add(new Gol(tela.getWidth() - 80, tela.getHeight() / 2 - 50, this, true));

    }

    private void initPlayers() {

        player1 = (JogadorActor) atoresCasa.get(posPlayer1);
        player1.setComportamento(Comportamentos.PLAYER_1);
        player2 = (JogadorActor) atoresVisitantes.get(posPlayer1);
        player2.setComportamento(Comportamentos.PLAYER_2);
    }

    private void adicionarJogadorVisitante(Actor a) {
        a.setSpr(new Sprite("soccer-inimigo.png"));
        todos.add(a);
        atoresVisitantes.add(a);
    }

    @Deprecated
    private void adicionarJogadorInimigo(int x, int y) {
        JogadorActor jogador = new JogadorActor(Comportamentos.JOGADOR_IA, x, y);
        jogador.setSpr(new Sprite("soccer-inimigo.png"));
        adicionarJogadorVisitante(jogador);
    }

    private void adicionarJogadorCasa(Actor a) {
        todos.add(a);
        atoresCasa.add(a);
    }

    @Deprecated
    private void adicionarJogadorCasa(int x, int y) {
        JogadorActor jogador = new JogadorActor(Comportamentos.JOGADOR_IA, x, y);
        adicionarJogadorCasa(jogador);
    }

    private void gerarTimeCasa() {
        Polygon limiteCampo = campo.getLimite();
        int xMinimo = limiteCampo.getBounds().x + 90;
        int yMinimo = limiteCampo.getBounds().y + 10;
        int gridX = (tela.getWidth() / 20) + 5;
        int gridY = (tela.getHeight() / 20) + 5;

        adicionarJogadorCasa(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 9, gridY * 9));
        adicionarJogadorCasa(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 9, gridY * 11));
        adicionarJogadorCasa(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 6, gridY * 6));

        adicionarJogadorCasa(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 6, gridY * 14));

        adicionarJogadorCasa(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 3, gridY * 10));
        adicionarJogadorCasa(new JogadorActor(Comportamentos.GOLEIRO_IA, xMinimo + 5, gridY * 10));
    }

    private void gerarTimeVisitante() {
        Polygon limiteCampo = campo.getLimite();
        int xMinimo = limiteCampo.getBounds().x + 90;
        int yMinimo = limiteCampo.getBounds().y + 10;
        int gridX = (tela.getWidth() / 20) + 5;
        int gridY = (tela.getHeight() / 20) + 5;

        adicionarJogadorVisitante(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 11, gridY * 8));
        adicionarJogadorVisitante(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 11, gridY * 12));
        adicionarJogadorVisitante(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 14, gridY * 6));

        adicionarJogadorVisitante(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 14, gridY * 14));

        adicionarJogadorVisitante(new JogadorActor(Comportamentos.JOGADOR_IA, gridX * 16, gridY * 10));
        adicionarJogadorVisitante(new JogadorActor(Comportamentos.GOLEIRO_IA, (gridX / 2) * 35, gridY * 10));

    }

    /**
     * Atualização da partida, responsável por que no tempo determinado que
     * encerra a partida, apresenta o placar para o usuário.
     */
    @Override
    public void update() {
        if (System.currentTimeMillis() - inicioPartida <= 60000 * tempoDeJogo) {
            partida();
        } else {
            JOptionPane.showMessageDialog(tela, "\tFim da partida\nCasa " + placar.getGolTime1() + " X " + placar.getGolTime2() + " Visitante");
            voltarMenu();
        }

    }

    /**
     * Renderiza todos os objetos redenderizavel em seu respectivos estados
     * presentes na cena a qual esta no momento atribuida ao atributo scene da
     * classe.
     */
    @Override
    public void render() {
        List<Renderable> renderables = new LinkedList<>(todos);
        renderables.add(0, campo);
        renderables.add(placar);
        tela.render(renderables);
    }

    private List<Actor> getActorsNear(Actor actor) {
        List actorsNear = new ArrayList<Actor>();
        for (Actor atorVerificado : todos) {
            if (actor.isNear(atorVerificado)) {
                actorsNear.add(atorVerificado);
            }
        }
        return actorsNear;
    }

    private void trocarPlayer1() {

        player1 = (JogadorActor) atoresCasa.get(posPlayer1);
        player1.setComportamento(Comportamentos.JOGADOR_IA);

        posPlayer1 = (posPlayer1 + 1) % (atoresCasa.size() - 1);

        player1 = (JogadorActor) atoresCasa.get(posPlayer1);

        player1.setComportamento(Comportamentos.PLAYER_1);

    }

    private void trocarPlayer2() {
        player2 = (JogadorActor) atoresVisitantes.get(posPlayer2);
        player2.setComportamento(Comportamentos.JOGADOR_IA);

        posPlayer2 = (posPlayer2 + 1) % (atoresVisitantes.size() - 1);

        player2 = (JogadorActor) atoresVisitantes.get(posPlayer2);

        player2.setComportamento(Comportamentos.PLAYER_2);

    }

    private Action generateAction(Actor actor) {
        return genericAction;
    }

    /**
     * Quando é realizado um gol, é contabilizado no placar, para o respectivo
     * time e acionado um audio.
     *
     * @param timeCasal
     */
    @Override
    public void onGoal(boolean timeCasal) {
        if (timeCasal) {
            placar.addGolTime1();
        } else {
            placar.addGolTime2();
        }
        try {
            audioGol = AudioManager.getInstance().loadAudio("gol.wav");
            audioGol.play();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Actor todo : todos) {
            todo.reset();
        }

    }

    private void partida() {

        for (Actor ator : todos) {
            ator.act(generateAction(ator), getActorsNear(ator));
        }

        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_Q)) {
            trocarPlayer1();
        }
        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_CONTROL)) {
            trocarPlayer2();
        }
        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_ESCAPE)) {
            voltarMenu();
        }
        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_M)) {
            if (tocandoTheme) {
                BGM.stop();
                tocandoTheme = false;
            } else {
                BGM.loop();
                tocandoTheme = true;
            }
        }

        Collections.sort(todos);
    }

    /**
     * Método que reponsável pela troca de cena, voltando para o menu.
     */
    public void voltarMenu() {
        BGM.stop();
        for (ChangeSceneListener sceneListener : sceneListeners) {
            sceneListener.changeScene(new MenuScene(tela, sceneListener));
        }
    }

}
