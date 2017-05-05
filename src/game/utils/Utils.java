package game.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zTitiK on 15-Apr-17.
 */
//utilities
public class Utils {
    static Random rand = new Random();
    public static Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getRandom() {
        return (rand.nextInt(2) + 1);
    }
}
