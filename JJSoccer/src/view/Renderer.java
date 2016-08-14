package view;

import java.awt.Color;
import models.interfaces.Renderable;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import models.Bola;
import models.Campo;
import models.JogadorActor;
import models.Placar;

/**
 * Classe Singleton responsavel pela impressão de imagens numa tela(graphics)
 * Possui apenas o metodo Render, que pinta uma lista de Renderable em um
 * Graphics
 */
public class Renderer {

    private Renderer() {
    }

    public static Renderer getInstance() {
        return RendererHolder.INSTANCE;
    }

    /**
     * Limpa o conteudo do Graphics recebido, e desenha nesse a lista de sprites
     * recebidas por parametro
     *
     * @param sprites lista de renderables a ser desenhado
     * @param g Graphics 2D para desenho na tela
     */
    public void render(List<Renderable> sprites, Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, 100);
        g.setColor(new Color(0, 200, 0, 255));
        g.fillRect(0, 100, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        for (Renderable sprite : sprites) {
            if (sprite instanceof JogadorActor || sprite instanceof Bola) {
                g.drawImage(sprite.getImage(), sprite.getX() - (sprite.getImage().getWidth(null) / 2), sprite.getY() - (sprite.getImage().getHeight(null) / 2), null);
            } else {
                g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);
            }

        }

    }

    private static class RendererHolder {

        private static final Renderer INSTANCE = new Renderer();
    }
}
