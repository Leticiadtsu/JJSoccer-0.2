package models;

import controller.managers.ImageManager;
import java.awt.Image;

/**
 * A classe Sprite é a definição das imagens do Actor que será impressa na tela.
 * Possui como atributo apenas uma image. 
 */
public class Sprite {

    Image imagem;

    /**
     * Construtor que simplesmente atribui a imagem definida na classe através de uma busca pelas imagens do InputManager.
     * @param nomeImagem nome da imagem.
     */
    public Sprite(String nomeImagem) {
            imagem = ImageManager.getInstance().loadImage(nomeImagem);
       
    }

    /**
     * Retorna a imagem.
     * @return imagem.
     */
    public Image getImage() {
        return imagem;
    }
}
