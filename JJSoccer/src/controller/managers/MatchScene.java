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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import models.Campo;
import models.Dimensao;
import models.JogadorActor;
import models.JogadorActor.Comportamentos;

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
    private List<Actor> todos;
    private ArrayList<Actor> atoresCasa;
    private ArrayList<Actor> atoresProximos;
    private Action genericAction;//Mudar depois no projeto final

    public MatchScene() {
        tela = new Frame("Jogo");
        campo = new Campo(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        
        todos = new ArrayList<>();
        todos.add(new Bola(200,200));
        todos.add(0, new JogadorActor(Comportamentos.CONTROLADO,100,100));
        posJogadorControaldo = 0;       
        
        
        genericAction = new Action() {
            @Override
            public Dimensao getLimite() {
                return campo.getDimencao();
            }
        };

    }
    
    

    @Override
    public void update() {
       
        for (Actor ator : todos) {
            ator.act(generateAction(ator),todos);
        }
        
        if(InputManager.getInstance().isPressed(KeyEvent.VK_M)){
            System.err.println("Trocou");
            trocarPlayer();
        }
        
        //Collections.sort(atoresCasa);
        
    }

    @Override
    public void render() {
        List<Renderable> renderables = new LinkedList<>(todos);
        renderables.add(0, campo);
        tela.render(renderables);
    }
    
    private List<Actor> getActorsNear(Actor actor) {
        List actorsNear =  new ArrayList<Actor>();
        for (Actor atorVerificado : todos) {
            if(actor.isColliding(atorVerificado)){
                actorsNear.add(atorVerificado);
            }
        }
        return actorsNear;
    }

    private void trocarPlayer(){
        
    }
    
    private Action generateAction(Actor actor) {
        return genericAction;
    }

}
