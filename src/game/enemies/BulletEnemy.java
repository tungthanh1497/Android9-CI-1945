package game.enemies;

import java.awt.*;
import java.io.IOException;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class BulletEnemy {

    private int x;
    private int y;
    private Image img;

    public BulletEnemy() throws IOException {
    }

    public BulletEnemy(int x, int y, Image img) {
        this.img = img;
        this.x = x - this.img.getWidth(null) / 2;
        this.y = y;
    }

    public void update() {
        this.y += 4;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, 13, 33, null);
    }

    public int getY() {
        return y;
    }
}
