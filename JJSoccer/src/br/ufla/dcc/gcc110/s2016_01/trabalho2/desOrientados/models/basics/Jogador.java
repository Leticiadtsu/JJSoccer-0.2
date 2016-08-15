package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models.basics;

import java.util.Random;

/**
 * Classe responsável por modelar os jogadores do jogo. Não interfere no motor e
 * nem na lógica do jogo. Define o nome e as habilidades de cada jogador.
 */
public class Jogador {

    private static final Random rand = new Random();

    private int id;
    private String nome;
    private Habilidades habilidades;

    /**
     * Construtor que receber por parâmetro apenas o nome. As habilidades do
     * jogador são definidas aleatóriamente através da Classe Random, importada
     * do pacote java.util.
     *
     * @param nome nome do jogador.
     */
    public Jogador(String nome) {
        this.nome = nome;
        this.habilidades = new Habilidades(rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1);
    }

    /**
     * Construtor que recebe por parâmetro todos os atributos do jogador.
     *
     * @param nome nome do jogador.
     * @param velocidade número que define a velocidade de um jogador.
     * @param drible número que define a capacidade de drible de um jogador.
     * @param defesa número que define a capacidade de defesa de um jogador.
     * @param especifica número que define uma habilidade específica de um
     * jogador.
     */
    public Jogador(String nome, int velocidade, int drible, int defesa, int especifica) {
        this.id = id;
        this.nome = nome;
        this.habilidades = new Habilidades(velocidade, drible, defesa, especifica);
    }

    /**
     * Retorna o nome do jogador.
     *
     * @return nome do jogador.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o número que representa alguma habiidade do jogador, essa
     * habilidade deve ser passada como parâmetro. Habilidades disponíveis:
     * velocidade, drible, defesa e específica.
     *
     * @param tipo habilidade desejada.
     * @return o número correspondente a habilidade desejada, passada por
     * parâmetro.
     */
    public int getHabilidade(Habilidades.habilidade tipo) {
        return habilidades.getHabilidade(tipo);
    }
    
}
