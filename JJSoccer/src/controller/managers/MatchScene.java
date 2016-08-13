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
import java.util.Collections;
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

    private Frame tela;
    private Campo campo;
    private Actor bola;
    private JogadorActor player;
    private ArrayList<Actor> atoresCasa;
    private Action genericAction;//Mudar depois no projeto final

    public MatchScene() {

        tela = new Frame("Jogo");
        campo = new Campo(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        bola = new Bola(500,500);
        atoresCasa = new ArrayList<>();
        atoresCasa.add(new JogadorActor(Comportamentos.JOGADOR_IA,100,100));
        atoresCasa.add(new JogadorActor(Comportamentos.JOGADOR_IA,200,200));
        atoresCasa.add(new JogadorActor(Comportamentos.JOGADOR_IA,300,300));
        player = new JogadorActor(Comportamentos.CONTROLADO,400,400); 
        
  
        genericAction = new Action() {
            @Override
            public Dimensao getLimite() {
                return campo.getDimencao();
            }
        };

    }
    
    

    @Override
    public void update() {
       
        for (Actor ator : atoresCasa) {
            ator.act(generateAction(ator),atoresCasa);
        }
        if(InputManager.getInstance().isPressed(KeyEvent.VK_TAB)){
            System.err.println("Trocou");
            trocarPlayer();
        }
        player.act(genericAction, atoresCasa);
        bola.act(genericAction, atoresCasa);
        Collections.sort(atoresCasa);
        
    }

    @Override
    public void render() {
        List<Renderable> renderables = new LinkedList<>(atoresCasa);
        renderables.add(player);
        renderables.add(0, campo);
        renderables.add(bola);
        tela.render(renderables);
    }
    
    private List<Actor> getActorsNear(Actor actor) {
        List actorsNear =  new ArrayList<Actor>();
        for (Actor atorVerificado : atoresCasa) {
            if(actor.isColliding(atorVerificado)){
                actorsNear.add(atorVerificado);
            }
        }
        return actorsNear;
    }

    private void trocarPlayer(){
        
        JogadorActor aux = player;
        player.setComportamento(Comportamentos.JOGADOR_IA);
        Random rand = new Random();
        int pos = rand.nextInt(atoresCasa.size());
        player = (JogadorActor) atoresCasa.get(pos);
        player.setComportamento(Comportamentos.CONTROLADO);
        atoresCasa.add(aux);
    }
    
    private Action generateAction(Actor actor) {
        return genericAction;
    }

}
