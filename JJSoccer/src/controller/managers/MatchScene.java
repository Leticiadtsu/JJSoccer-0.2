/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

import java.awt.Point;
import java.awt.Polygon;
import models.Actor;
import models.Bola;
import models.interfaces.Action;
import models.interfaces.Renderable;
import view.Frame;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import models.Campo;
import models.Dimensao;
import models.Gol;
import models.JogadorActor;
import models.JogadorActor.Comportamentos;
import models.Placar;
import models.interfaces.GolListener;

/**
 *
 * @author
 */
public class MatchScene extends GameScene implements GolListener {

    private int posJogadorControaldo;
    private Frame tela;
    private Campo campo;
    private Bola bola;
    private final Point posInicialBola;
    private JogadorActor player;
    private Placar placar;
    private List<Actor> todos;
    private ArrayList<Actor> atoresCasa;
    private ArrayList<Actor> atoresProximos;
    private Action genericAction;//Mudar depois no projeto final

    private long ultimaTroca;

    public MatchScene() {
        ultimaTroca = System.currentTimeMillis();
        tela = new Frame("Jogo");
        Dimensao tamanhoDoCampo = new Dimensao(tela.getWidth(), tela.getHeight() - 100);
        Dimensao tamanhoDoGol = new Dimensao(80, 200);
        campo = new Campo(new Point(20, 100), tamanhoDoCampo, tamanhoDoGol);
        placar = new Placar(tela.getWidth() / 2, 10, "Time da Casa", "Time Visitante");
        posInicialBola = new Point(tela.getWidth() / 2, tela.getHeight() / 2 + 100);
        todos = new ArrayList<>();
        atoresCasa = new ArrayList<>();
        bola = new Bola(posInicialBola.x, posInicialBola.x);
        todos.add(bola);
        todos.add(new Gol(0, tela.getHeight() / 2 - 50, this, false));
        todos.add(new Gol(tela.getWidth() - 80, tela.getHeight() / 2 - 50, this, true));

        player = new JogadorActor(Comportamentos.CONTROLADO, 100, 100);
        todos.add(0, player);
        atoresCasa.add(0, player);
        adicionarJogadorCasa(new JogadorActor(Comportamentos.JOGADOR_IA, 150, 150));
        posJogadorControaldo = 0;

        genericAction = new Action() {
            @Override
            public Polygon getLimite() {
                return campo.getLimite();
            }
        };

    }

    private void adicionarJogador(Actor a) {
        todos.add(a);
    }

    private void adicionarJogadorCasa(Actor a) {
        todos.add(a);
        atoresCasa.add(a);

    }

    @Override
    public void update() {

        for (Actor ator : todos) {
            ator.act(generateAction(ator), todos);
        }

        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_M)) {
            System.err.println("Trocou");
            trocarPlayer();
        }
        Collections.sort(todos);
    }

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
            if (actor.isColliding(atorVerificado)) {
                actorsNear.add(atorVerificado);
            }
        }
        return actorsNear;
    }

    private void trocarPlayer() {

//        if (System.currentTimeMillis() - ultimaTroca >= 30) {
        System.err.println("Vou troacar");
        player = (JogadorActor) atoresCasa.get(posJogadorControaldo);
        player.setComportamento(Comportamentos.JOGADOR_IA);
        Random rand = new Random();
        int novaPosicao = rand.nextInt(atoresCasa.size());
        if (novaPosicao == posJogadorControaldo) {
            novaPosicao = (novaPosicao + 1) % atoresCasa.size();
        }

        posJogadorControaldo = novaPosicao;
        player = (JogadorActor) atoresCasa.get(posJogadorControaldo);

        player.setComportamento(Comportamentos.CONTROLADO);
        ultimaTroca = System.currentTimeMillis();
//        } else {
//            System.err.println("Nao vou tocar");
//        }

    }

    private Action generateAction(Actor actor) {
        return genericAction;
    }

    @Override
    public void onGoal(Gol gol) {
        if (gol.isTimeCasa()) {
            placar.addGolTime1();
        } else {
            placar.addGolTime2();

        }
        for (Actor todo : todos) {
            todo.reset();
        }
    }

}
