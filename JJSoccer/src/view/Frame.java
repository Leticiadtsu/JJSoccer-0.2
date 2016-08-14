package view;

import controller.managers.InputManager;
import models.interfaces.Renderable;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;
import javax.swing.JFrame;

/**
 * Classe que representa a Janela onde todo jogo é exibido. Herda de JFrame. A
 * Janela ocupa toda tela do computador, é undecorated, isso é, não possui a
 * armação da janela. A Janela adiona o InputManager como um listener de seus
 * input.
 */
public class Frame extends JFrame {

    private BufferStrategy bufferStrategy;

    /**
     * Constroi a janena com seu padrão: Ocupando toda janela, e sem decorações.
     *
     * @param titulo Titulo da Janela, esperado ser o nome do Jogo.
     */
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

    /**
     * Metodo reponsavel por chamar a renderização na tela. Imprime na tela a
     * lista de Renderables recebidas como padrão. A cada chamada do render, a
     * tela é limpa e é impresso as imagens do Renderable
     *
     * @param sprites Lista de Renderables a serem pintados na tela
     */
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
