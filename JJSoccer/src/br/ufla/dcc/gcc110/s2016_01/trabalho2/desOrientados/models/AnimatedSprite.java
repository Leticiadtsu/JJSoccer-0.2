package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.models;

/**
 * É a definição das imagens do Actor que será impressa na tela. Caso especial
 * de Sprite, por isso o estende.
 */
public class AnimatedSprite extends Sprite {

    /**
     * O construtor recebe como parâmetro o nome da imagem e chama o construtor
     * da sua superclasse.
     *
     * @param nomeImagem nome da imagem.
     */
    public AnimatedSprite(String nomeImagem) {
        super(nomeImagem);
    }

}
