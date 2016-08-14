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
 *
 * @author
 */
public class Game implements ChangeSceneListener {

    private final int DELAY = 10;
    private GameScene scene;
    private Frame tela;
    private long previousMillis;

    public void init() {
        previousMillis = System.currentTimeMillis();

        tela = new Frame("Jogo");
        scene = new MenuScene(tela, this);
        gameloop();
    }

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

    private void update() {
        if (System.currentTimeMillis() - previousMillis >= DELAY) {
            previousMillis = System.currentTimeMillis();
            scene.update();
        }
    }

    private void render() {
        scene.render();
    }

    @Override
    public void changeScene(GameScene scene) {
        this.scene = scene;

    }

}
