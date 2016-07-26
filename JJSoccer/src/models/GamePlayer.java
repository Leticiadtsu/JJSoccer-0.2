package models;

import controller.managers.InputManager;
import models.interfaces.Action;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 *
 * @author 
 */
public class GamePlayer extends JogadorActor {
    
    public GamePlayer(int x, int y) {
        super(x, y);
    }

    public GamePlayer() {
        super();
    }

    @Override
    public void act(Action action, List<Actor> collisions) {
        int oldX = getX();
        int oldY = getY();
        if (InputManager.getInstance().isPressed(KeyEvent.VK_UP)) {
            mover(Direcao.CIMA, action.getLimite(),collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_DOWN)) {
            mover(Direcao.BAIXO, action.getLimite(),collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
             mover(Direcao.DIREITA, action.getLimite(),collisions);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)) {
             mover(Direcao.ESQUERDA, action.getLimite(),collisions);
        }
    }
}
