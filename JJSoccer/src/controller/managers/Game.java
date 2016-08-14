/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import view.Frame;
import models.interfaces.ChangeSceneListener;

/**
 * Classe responsavel pelo motor do jogo, ela que gerencia todo fluxo do jogo
 * chamada de telas, realizar o looping que atualiza as telas e o que é
 * renderizado na saída.
 *
 */
public class Game implements ChangeSceneListener {

    private final int DELAY = 10;
    private GameScene scene;
    private Frame tela;
    private long previousMillis;

    /**
     * Metodo que deve ser chamado para da inicio no game. Este metodo
     * inicializa com a cena de menu.
     */
    public void init() {
        previousMillis = System.currentTimeMillis();

        tela = new Frame("Jogo");
        scene = new MenuScene(tela, this);
        gameloop();
    }

    /**
     * Motor do jogo que e executado continuamente atualzando todos os elementos
     * do jogo. A cena renderizada e a qual esta no momento atribuida ao
     * atributo scene da calasse.
     */
    private void gameloop() {
        while (true) {

            update();//criar variavel dps
            render();
            InputManager.getInstance().update();
            try {
                Thread.sleep(10);
                //throw  new Exception();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro no looping de jogo!!!\n\tFinalizando sitema ");
                System.exit(0);
            }

        }

    }

    /**
     * Realiza a utualizacao da cena a qual esta no momento atribuida ao
     * atributo scene da calasse. Como é o metodo mais pesado somente é realizdo
     * se tenha passado um tempo predefinido após o ultimo chamado.
     */
    private void update() {
        if (System.currentTimeMillis() - previousMillis >= DELAY) {
            previousMillis = System.currentTimeMillis();
            scene.update();
        }
    }

    /**
     * Renderiza todos os objetos redenderizavel em seu respectivos estados
     * presentes na cena a qual esta no momento atribuida ao atributo scene da
     * calasse.
     */
    private void render() {
        scene.render();
    }

    /**
     * Realiza a mudança de cena da classe, a classe Game sempre será uma
     * ouvinte de suas cenas para que está possa comunicar qualquer modificação
     * reqeurida.
     *
     * @param scene
     */
    @Override
    public void changeScene(GameScene scene) {
        this.scene = scene;

    }

}
