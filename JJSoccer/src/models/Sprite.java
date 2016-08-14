package models;

import controller.managers.ImageManager;
import java.awt.Image;

/**
 *
 * @author
 */
public class Sprite {

    Image imagem;

    public Sprite(String nomeImagem) {
            imagem = ImageManager.getInstance().loadImage(nomeImagem);
       
    }

    public Image getImage() {
        return imagem;
    }
}
