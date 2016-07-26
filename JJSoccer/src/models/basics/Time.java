package models.basics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 
 */
public class Time {

    private static final Random rand = new Random();
    private int id;
    private String nome;
    private Color cor;
    private List<Jogador> jogadores;

    public Time(String nome, int qtdjogadores) {
        this.id = id;
        this.nome = nome;
        //Criar jogadores aqui detro????;
        this.jogadores = gerarJogadores(qtdjogadores, nome);
    }

    private List gerarJogadores(int qtdJogadores, String nomeDefault) {
        List jogadores = new ArrayList();
        for (int i = 1; i <= qtdJogadores; i++) {
            String nome = nomeDefault+" " + i;
            jogadores.add(new Jogador(nome, rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1));
        }
        return jogadores;
    }

}
