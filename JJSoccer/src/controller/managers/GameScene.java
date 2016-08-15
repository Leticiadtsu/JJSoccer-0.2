package controller.managers;

import view.Frame;

/**
 * Classe abstrata que define a cena do game, possui métodos que devem ser
 * implementados nas classes que a herdam. Utilizada apenas para o polimorfismo
 * no Game, para que o jogo não tenha de se importar com o que seja a cena,
 * desde que essa seja capaz de se controlar e se imprimir.
 */
public abstract class GameScene {

    /**
     * Realiza uma atualização da cena.
     */
    public abstract void update();

    /**
     * Renderiza todos os objetos redenderizáveis em seu respectivos estados.
     */
    public abstract void render();
}
