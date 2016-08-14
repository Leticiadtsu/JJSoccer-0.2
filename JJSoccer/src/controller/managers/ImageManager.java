/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller.managers;

/**
 *
 * @author
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageManager {

    static private ImageManager instance;
    private HashMap<String, BufferedImage> images;

    private ImageManager() {
        images = new HashMap<String, BufferedImage>();
    }

    static public ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    public BufferedImage loadImage(String fileName) {
        try {
            URL url = getClass().getResource("/resources/" + fileName);
            if (url == null) {
                url = getClass().getResource("/resources/x.png");
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
