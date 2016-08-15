package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.basics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe responsável por modelar os times presentes no jogo. Não interfere no
 * motor e nem na lógica do jogo. Define o nome, a cor e a lista de jogaores de
 * cada time.
 */
public class Time {

    private static final Random rand = new Random();
    private int id;
    private String nome;
    private Color cor;
    private List<Jogador> jogadores;

    /**
     * Construtor que recebe como parâmetro o nome e a quantidade de jogadores
     * do time. Gera os jogadores através da chamada de um método específico
     * para tal função.
     *
     * @param nome nome do time.
     * @param qtdjogadores quantidade de jogadores presentes no time.
     */
    public Time(String nome, int qtdjogadores) {
        this.id = id;
        this.nome = nome;
        this.jogadores = gerarJogadores(qtdjogadores, nome);
    }

    /**
     * Método que cria os jogadores do time. Recebe como parâmetro a quantidade
     * de jogadores e um nome default (padrão), o qual será passado para
     * identificar todos os jogadores do time.
     *
     * @param qtdJogadores quantidade de jogadores presente no time.
     * @param nomeDefault nome padrão para criação dos jogadores.
     * @return lista de jogadores.
     */
    private List gerarJogadores(int qtdJogadores, String nomeDefault) {
        List jogadores = new ArrayList();
        for (int i = 1; i <= qtdJogadores; i++) {
            String nome = nomeDefault + " " + i;
            jogadores.add(new Jogador(nome, rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1));
        }
        return jogadores;
    }
    
}
