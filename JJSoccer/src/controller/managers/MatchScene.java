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
import models.JogadorActor;
import models.JogadorActor.Comportamentos;
import models.Placar;

/**
 *
 * @author
 */
public class MatchScene extends GameScene {

    private int posJogadorControaldo;
    private Frame tela;
    private Campo campo;
    private Actor bola;
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

        atoresCasa = new ArrayList<>();
        atoresCasa.add(new JogadorActor(Comportamentos.JOGADOR_IA, 500, 500));
        atoresCasa.add(new JogadorActor(Comportamentos.JOGADOR_IA, 200, 200));
        atoresCasa.add(new JogadorActor(Comportamentos.JOGADOR_IA, 300, 300));
        atoresCasa.add(0, new JogadorActor(Comportamentos.CONTROLADO, 100, 100));

        todos = new ArrayList<>(atoresCasa);
        todos.add(new Bola(200, 200));

        posJogadorControaldo = 0;

        genericAction = new Action() {
            @Override
            public Polygon getLimite() {
                return campo.getLimite();
            }
        };

    }

    @Override
    public void update() {

        for (Actor ator : todos) {
            ator.act(generateAction(ator), todos);
        }

        if (InputManager.getInstance().isPressed(KeyEvent.VK_M)) {
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
        if (System.currentTimeMillis()-ultimaTroca  >= 30) {
            System.err.println("Vou troacar");
            player = (JogadorActor) atoresCasa.get(posJogadorControaldo);
            player.setComportamento(Comportamentos.JOGADOR_IA);
            Random rand = new Random();
            posJogadorControaldo = rand.nextInt(atoresCasa.size());
            player = (JogadorActor) atoresCasa.get(posJogadorControaldo);
            player.setComportamento(Comportamentos.CONTROLADO);
            ultimaTroca = System.currentTimeMillis();
        }else
        {
            System.err.println("Nao vou tocar");
        }
    }

    private Action generateAction(Actor actor) {
        return genericAction;
    }

}
