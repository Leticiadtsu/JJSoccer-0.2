/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

import models.Actor;
import models.Bola;
import models.interfaces.Action;
import models.interfaces.Renderable;
import view.Frame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import models.Campo;
import models.Dimensao;
import models.JogadorActor;
import models.JogadorActor.Comportamentos;

/**
 *
 * @author
 */
public class MatchScene extends GameScene {

    private Frame tela;
    private Campo campo;
    private ArrayList<Actor> atores;
    private Action genericAction;//Mudar depois no projeto final

    public MatchScene() {

        tela = new Frame("Jogo");
        campo = new Campo(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        atores = new ArrayList<>();
        atores.add(new JogadorActor(Comportamentos.CONTROLADO, 30, Toolkit.getDefaultToolkit().getScreenSize().height / 2));
        atores.add(Bola.getInstance(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2));
        atores.add(new JogadorActor(JogadorActor.Comportamentos.JOGADOR_IA, 250, 250));
        atores.add(new JogadorActor(Comportamentos.GOLEIRO_IA, Toolkit.getDefaultToolkit().getScreenSize().height / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2));
        //kjKJDWAKJDWALKDAW
        genericAction = new Action() {
            @Override
            public Dimensao getLimite() {
                return campo.getDimencao();
            }
        };

    }

    @Override
    public void update() {
        for (Actor ator : atores) {
            ator.act(generateAction(ator),atores);
        }
        Collections.sort(atores);
    }

    @Override
    public void render() {
        List<Renderable> renderables = new LinkedList<>(atores);
        renderables.add(0, campo);
        tela.render(renderables);
    }
    
    private List<Actor> getActorsNear(Actor actor) {
        List actorsNear =  new ArrayList<Actor>();
        for (Actor atorVerificado : atores) {
            if(actor.isColliding(atorVerificado)){
                actorsNear.add(atorVerificado);
            }
        }
        return actorsNear;
    }

    private Action generateAction(Actor actor) {
        return genericAction;
    }

}
