package controller.managers;

/**
 * Classe unica, que realiza o tratamento de entrada de teclas, ela armazena
 * todas entradas do toclado durante o jogo, e disponibilizando informações
 * sobre estas apartir dos seus metodos. Como o contole do jogo deve ser
 * executado por um unico objeto esta classe utiliza o padrão Singleton
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe unica, que realiza o tratamento de entrada de teclas, ela armazena
 * todas entradas do toclado durante o jogo, e disponibilizando informações
 * sobre estas apartir dos seus metodos. Como o contole do jogo deve ser
 * executado por um unico objeto esta classe utiliza o padrão Singleton
 */
public class InputManager implements KeyListener {

    static protected int KEY_RELEASED = 0;
    static protected int KEY_JUST_PRESSED = 1;
    static protected int KEY_PRESSED = 2;
    static private InputManager instance;
    private HashMap<Integer, Integer> keyCache;
    private ArrayList<Integer> pressedKeys;
    private ArrayList<Integer> releasedKeys;

    private InputManager() {
        keyCache = new HashMap<>();
        pressedKeys = new ArrayList<>();
        releasedKeys = new ArrayList<>();
    }

    /**
     * Metodo estatico que serve com um construtor onde retorna uma isntancia
     * unica da classe
     *
     * @return instancia unica da classe InputManager
     */
    static public InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    /**
     * Verifica se a tecla está precionada, deve ser passado o codigo da tecla
     * que deseja.
     *
     * @param keyId codigo da tecla esperada.
     * @return true se estiver precionada, false caso contrário.
     */
    public boolean isPressed(int keyId) {
        return keyCache.containsKey(keyId)
                && !keyCache.get(keyId).equals(KEY_RELEASED);
    }

    /**
     * Verifica se a tecla já foi precionada, ou seja, pressionada e solta, deve
     * ser passa o codigo da tecla que deseja.
     *
     * @param keyId codigo da tecla esperada.
     * @return true se foi precionada, false caso contrario (Incluindo ainda
     * estar precionada).
     */
    public boolean isJustPressed(int keyId) {
        return keyCache.containsKey(keyId)
                && keyCache.get(keyId).equals(KEY_JUST_PRESSED);
    }

    /**
     * Verifica se a tecla ja foi solta.
     *
     * @param keyId codigo da tecla esperada.
     * @return true caso a tecla já tenha sido solta, false caso contrário.
     */
    public boolean isReleased(int keyId) {
        return !keyCache.containsKey(keyId)
                || keyCache.get(keyId).equals(KEY_RELEASED);
    }

    /**
     * Deve ser chamado a cada looping do jogo.Carrega os dados de entrada do
     * teclado para que possa ser disponivilizado.
     */
    public void update() {
        for (Integer keyCode : keyCache.keySet()) {
            if (isJustPressed(keyCode)) {
                keyCache.put(keyCode, KEY_PRESSED);
            }
        }
        for (Integer keyCode : releasedKeys) {
            keyCache.put(keyCode, KEY_RELEASED);
        }
        for (Integer keyCode : pressedKeys) {
            if (isReleased(keyCode)) {
                keyCache.put(keyCode, KEY_JUST_PRESSED);
            } else {
                keyCache.put(keyCode, KEY_PRESSED);
            }
        }
        pressedKeys.clear();
        releasedKeys.clear();
    }

    /**
     * Não utilizado na aplicação.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metodo da inteface KeyListener chamado quando uma tecla é a precionada.
     *
     * @param e codigo da tecla pressionada.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    /**
     * Metodo da inteface KeyListener chamado quando uma tecla é a solta.
     *
     * @param e codigo da tecla pressionada.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        releasedKeys.add(e.getKeyCode());
    }

    /**
     * Reinica todos dados de entradas salvos no momento.
     */
    public void reset() {
        keyCache = new HashMap<>();
        pressedKeys = new ArrayList<>();
        releasedKeys = new ArrayList<>();
    }
}
