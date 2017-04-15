import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by zTitiK on 12-Apr-17.
 */
public class Bullet {
    private int bulletX;
    private int bulletY;
    private Image bulletImage;

    public Bullet() throws IOException {
    }

    public Bullet(int bulletX, int bulletY, Image img) throws IOException {
        this.bulletImage = img;
        this.bulletX = bulletX - bulletImage.getWidth(null) / 2;
        this.bulletY = bulletY - bulletImage.getHeight(null);
    }

    public void update() {
        this.bulletY -= 15;
    }

    public void draw(Graphics g) {
        g.drawImage(bulletImage, bulletX, bulletY, 13, 33, null);
    }


    public int getBulletY() {
        return bulletY;
    }
}
