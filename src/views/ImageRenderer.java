package views;

import models.GameRect;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class ImageRenderer {
    Image image;

    public Image getImage() {
        return image;
    }

    public ImageRenderer() {

    }

    public ImageRenderer(Image image) {
        this.image = image;
    }

    public ImageRenderer(String path) {
        this(Utils.loadImage(path));
    }

    public void render(Graphics graphics, GameRect gameRect){
        graphics.drawImage(image, gameRect.getX(), gameRect.getY(),gameRect.getWidth(),gameRect.getHeight(), null);
    }

}
