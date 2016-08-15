package jjsoccer;

import controller.managers.Game;
import java.util.ConcurrentModificationException;
import javax.swing.JOptionPane;

/**
 * Classe de execução do jogo, possui o método main que dá inicio ao Game.
 */
public class JJSoccer {

    /**
     * Tenta inicializar o jogo, caso ocorra algum erro, há um tratamento de
     * exceção.
     */
    public static void main(String[] args) {
        try {
            new Game().init();

        } catch (ConcurrentModificationException e) {
            JOptionPane.showMessageDialog(null, "Erro no controlador de entradas do usuário\nNossas equipes estão trabalhando duro para que possa resolver\n" + e + "\nO sistema será finalizado");
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Infelizmente Ocorreu algum erro no sistema\n" + e + "\nO sistema será finalizado");
            System.exit(0);
        }

    }
}
