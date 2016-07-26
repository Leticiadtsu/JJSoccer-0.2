package view;

import models.interfaces.Renderable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import models.Campo;

/**
 *
 * @author
 */
public class Renderer {

    private Renderer() {
    }

    public static Renderer getInstance() {
        return RendererHolder.INSTANCE;
    }

    /**
     *
     * @param sprites lista de renderables a ser desenhado
     * @param g Graphics 2D para desenho na tela
     */
    public void render(List<Renderable> sprites, Graphics2D g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, 400, 400);
        for (Renderable sprite : sprites) {
            if (sprite instanceof Campo) {
                g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(),null);
            }
            g.drawImage(sprite.getImage(), sprite.getX() - (sprite.getImage().getWidth(null) / 2), sprite.getY() - (sprite.getImage().getHeight(null) / 2), null);
        }

    }

    private static class RendererHolder {

        private static final Renderer INSTANCE = new Renderer();
    }
}
