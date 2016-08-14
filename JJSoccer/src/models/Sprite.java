package models;

import controller.managers.ImageManager;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
