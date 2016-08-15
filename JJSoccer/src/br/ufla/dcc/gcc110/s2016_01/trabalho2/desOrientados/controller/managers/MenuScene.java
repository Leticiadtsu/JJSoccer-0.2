package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.controller.managers;

import com.sun.glass.events.KeyEvent;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.Renderable;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.menuModels.ButtonActor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.menuModels.LogoActor;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.menuModels.PaneRenderable;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.view.Frame;
import br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.interfaces.ChangeSceneListener;

/**
 * Classe responsável pela cena de menu, a qual pode inicializar uma nova
 * partida, ou sair do jogo. Possui como atricuto uma lista de cenas ouvinte, o
 * audio e as imagens que serão exibidas na tela.
 */
public class MenuScene extends GameScene {

    private List<ChangeSceneListener> sceneListeners;
    private AudioClip BGM;
    private LogoActor logo;
    private PaneRenderable pane;
    private final Frame tela;
    private List<ButtonActor> buttons;

    /**
     * Enumerador que define as opções do menu.
     */
    public enum Opcoes {
        JOGAR, SAIR;
    }

    /**
     * Construtor do menu o qual altera a sua visibilidade, inicializa a tela,
     * cria uma lista ouvintes da cena, inicializa o audio, define os botões de
     * opções e as imagens exibidas.
     *
     * @param tela tela.
     * @param parent ouvinte.
     */
    public MenuScene(Frame tela, ChangeSceneListener parent) {
        this.tela = tela;
        tela.setVisible(true);
        this.sceneListeners = new ArrayList<>();
        this.sceneListeners.add(parent);
        logo = new LogoActor(100, 50, 4);
        pane = new PaneRenderable(tela.getWidth(), tela.getHeight());

        initAudio();

        buttons = new LinkedList<>();

        // BT JOGAR
        ButtonActor btJogar = new ButtonActor(300, 300, 5, "Jogar");
        btJogar.setFocused(true);
        btJogar.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogar();
            }
        });
        buttons.add(btJogar);

        // BT SAIR
        ButtonActor btSair = new ButtonActor(300, 400, 6, "Sair");
        btSair.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        buttons.add(btSair);
    }

    private void initAudio() {
        long previousMillis = System.currentTimeMillis();
        try {
            BGM = AudioManager.getInstance().loadAudio("Theme.wav");

            int delay = 1000;
            while (System.currentTimeMillis() - previousMillis < delay) {
            }

            BGM.play();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Realiza a atualizacao da cena a qual esta no momento atribuída ao
     * atributo scene da classe. Como é o método mais pesado somente é realizdo
     * se tenha passado um tempo predefinido após o ultimo chamado.
     */
    @Override
    public void update() {
        logo.act(null, null);
        for (ButtonActor button : buttons) {
            button.act(null, null);
        }
        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_DOWN)) {
            downButton();
        } else if (InputManager.getInstance().isJustPressed(KeyEvent.VK_UP)) {
            upButton();
        } else if (InputManager.getInstance().isJustPressed(KeyEvent.VK_ENTER)) {
            enterButton();
        }
        if (InputManager.getInstance().isJustPressed(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
    }

    private void downButton() {
        boolean found = false;
        boolean set = false;
        for (ButtonActor button : buttons) {
            if (found) {
                button.setFocused(true);
                set = true;
                break;
            } else if (button.isFocused()) {
                button.setFocused(false);
                found = true;
            }
        }
        if (!set && buttons.size() >= 1) {
            buttons.get(0).setFocused(true);
        }
    }

    private void upButton() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isFocused()) {
                buttons.get(i).setFocused(false);
                if (i > 0) {
                    buttons.get(i - 1).setFocused(true);
                } else {
                    buttons.get(buttons.size() - 1).setFocused(true);
                }
            }
        }

    }

    private void enterButton() {
        for (ButtonActor button : buttons) {
            if (button.isFocused()) {
                button.ActionPerformed();
                break;
            }
        }
    }

    /**
     * Para o áudio que estava sendo executado e avisa aos ouvintes que foi
     * escolhido a opção jogar. Trocando de cena.
     */
    public void jogar() {
        BGM.stop();

        for (ChangeSceneListener sceneListener : sceneListeners) {
            sceneListener.changeScene(new MatchScene(tela, sceneListener));
        }

    }

    /**
     * Sair do jogo.
     */
    public void sair() {
        System.exit(0);
    }

    /**
     * Renderiza todos os objetos redenderizaveis em seu respectivos estados
     * presentes na cena a qual esta no momento atribuida ao atributo scene da
     * classe.
     */
    @Override
    public void render() {
        LinkedList<Renderable> sprites = new LinkedList<Renderable>();
        sprites.add(pane);
        sprites.add(logo);
        for (ButtonActor button : buttons) {
            sprites.add(button);
        }
        tela.render(sprites);

    }

}
