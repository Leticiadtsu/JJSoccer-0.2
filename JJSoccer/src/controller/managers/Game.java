/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Game {

    private final int DELAY = 10;
    private GameScene scene;
    private long previousMillis;

    public void init() {
        previousMillis = System.currentTimeMillis();
        scene = new MatchScene();
        gameloop();
    }

    private void gameloop() {
        while (!InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)) {
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
        System.exit(0);
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

}
