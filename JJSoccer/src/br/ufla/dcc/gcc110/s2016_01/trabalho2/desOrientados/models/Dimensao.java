package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models;

/**
 * Classe que define as dimensões, auxilia na coesão por encapsular uma
 * dimensão, possuindo altura e largura.
 */
public class Dimensao {

    private final int largura;
    private final int altura;

    /**
     * Construtor que inicializa uma dimensão recebendo a sua largura e altura.
     *
     * @param largura largura da dimensão.
     * @param altura altura da dimensão.
     */
    public Dimensao(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    /**
     * Retorna a largura da dimensão.
     *
     * @return largura da dimensão.
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Retorna a altura da dimensão.
     *
     * @return altura da dimensão.
     */
    public int getAltura() {
        return altura;
    }

}
