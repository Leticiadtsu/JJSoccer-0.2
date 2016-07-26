package view;

import controller.managers.InputManager;
import models.interfaces.Renderable;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author
 */
public class Frame extends JFrame {

    private BufferStrategy bufferStrategy;

    public Frame(String titulo) {
        super(titulo);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        addKeyListener(InputManager.getInstance());
        setUndecorated(true);
        setIgnoreRepaint(true);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            createBufferStrategy(2);
        } catch (Exception e) {
            System.exit(0);
        }
        bufferStrategy = getBufferStrategy();
    }

    public void render(List<Renderable> sprites) {
        try {
            Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
            Renderer.getInstance().render(sprites, graphics);
            graphics.dispose();
            bufferStrategy.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
