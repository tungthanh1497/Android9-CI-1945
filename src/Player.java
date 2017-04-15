import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zTitiK on 12-Apr-17.
 */
public class Player {
    private int x;
    private int y;
    private Image img;

    private int dx;
    private int dy;

    private ArrayList<Bullet> bullets;
    private boolean shootEnabled;
    private int countDown;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImg() {
        return img;
    }

    public Player(int x, int y, Image img) {
        this.img = img;
        this.x = x - img.getWidth(null) / 2;
        this.y = y - img.getHeight(null);
        dx = 0;
        dy = 0;
        bullets = new ArrayList<>();
        shootEnabled = true;
    }

    public void processInput(InputManager inputManager) {
        dx = 0;
        dy = 0;
        if (inputManager.isRightPressed()) {
            if ((x + 10) <= (600 - 70)) {
                dx += 10;
            }
        }
        if (inputManager.isLeftPressed()) {
            if ((x - 10) >= 0) {
                dx -= 10;
            }
        }
        if (inputManager.isUpPressed()) {
            if ((y - 40) >= 0) {
                dy -= 10;
            }
        }
        if (inputManager.isDownPressed()) {
            if ((y + 10) <= 700 - 50) {
                dy += 10;
            }
        }
        if (inputManager.isSpacePressed()) {
            if (shootEnabled) {
                Bullet bullet = null;
                try {
                    bullet = new Bullet(x + img.getWidth(null) / 2, y, Utils.loadImage("res/bullet.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bullets.add(bullet);
                shootEnabled = false;
                countDown = 10;
            }
        }
    }


    public void draw(Graphics g) {
        g.drawImage(img, x, y, 70, 50, null);
        for (Bullet b : bullets) {
            b.draw(g);
        }
    }

    public void update() {
        this.x += dx;
        this.y += dy;

        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getBulletY() < 0) {
                bullets.remove(bullets.get(i));
                i--;
            } else
                bullets.get(i).update();
        }
        if (!shootEnabled) {
            countDown--;
            if (countDown == 0) {
                shootEnabled = true;
            }
        }
    }
}
