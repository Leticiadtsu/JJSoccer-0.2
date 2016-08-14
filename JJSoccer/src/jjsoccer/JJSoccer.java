/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jjsoccer;

import controller.managers.Game;
import java.util.ConcurrentModificationException;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class JJSoccer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new Game().init();

        } catch (ConcurrentModificationException e) {
            JOptionPane.showMessageDialog(null, "Erro no controlador de entradas do usuário\nNossas equipes estão trabalhando duro para que possa resolver\n" + e + "\nO sistema será finalizado");
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Infelizmente Ocorreu algum erro no sistema\n" + e + "\nO sistema será finalizado");
        }

    }
}
