package br.ufla.dcc.gcc110.s2016_01.trabalho2.desOrientados.controller.managers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * Classe que segue o padrão singleton e serve para controlar as imagens do
 * jogo. Possui um HashMap que salva as imagens.
 */
public class ImageManager {

    static private ImageManager instance;
    private HashMap<String, BufferedImage> images;

    private ImageManager() {
        images = new HashMap<String, BufferedImage>();
    }

    /**
     * Método que garante que não há replicações de instâncias de uma mesma
     * imagem.
     *
     * @return instancia da classe.
     */
    static public ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    /**
     * Método que carrega uma imagem, primeiramete verifica se essa existe no
     * HashMap, caso exista retorna uma referência à essa. Caso não existe,
     * carrega essa imagem do HD para o HashMap, e retorna uma referência dessa.
     *
     * @param fileName nome da imagem.
     * @return imagem.
     */
    public BufferedImage loadImage(String fileName) {
        try {
            URL url = getClass().getResource("/br/ufla/dcc/gcc110/s2016_01/trabalho2/desOrientados/resources/" + fileName);
            if (url == null) {
                url = getClass().getResource("/br/ufla/dcc/gcc110/s2016_01/trabalho2/desOrientados/resources/x.png");
            }

            String path = url.getPath();
            if (images.containsKey(path)) {
                return images.get(path);
            } else {

                BufferedImage img = ImageIO.read(url);
                images.put(path, img);
                return img;

            }
        } catch (IOException ex) {
            return new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB_PRE);
        }
    }

    public BufferedImage loadImage(String fileName, int x, int y, int w,
            int h)
            throws IOException {
        BufferedImage sheet = loadImage(fileName);
        BufferedImage img = sheet.getSubimage(x, y, w, h);
        return img;
    }
}
