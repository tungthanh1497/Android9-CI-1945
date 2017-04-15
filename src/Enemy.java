import javax.imageio.ImageIO;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zTitiK on 12-Apr-17.
 */
public class Enemy {

    Random rand = new Random();
    private int x;
    private int y;
    private Image img;
    boolean moveEnabled;
    int countDownMove;
    ArrayList<BulletEnemy> bullets;
    boolean shootEnabled;
    int countDown;

    private int dx;
    private int dy;

    public Enemy(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        dx = 0;
        dy = 0;
        bullets = new ArrayList<>();
        shootEnabled = true;
        moveEnabled = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {

        return y;
    }

    private int getRandom() {
        return (rand.nextInt(2) + 1);
    }

    public void update() {
        dx = 0;
        dy = 0;
        if (getRandom() == 1) {
            dx += 10; // right
        }
        if (getRandom() == 1) {
            dx -= 10; // left
        }
        //if (getRandom() == 1) {
        dy += 2; //down
        // }

        x += dx;
        y += dy;


//        for (BulletEnemy b : bullets) {
//            b.update();
//        }

        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getY() > 700) {
                bullets.remove(bullets.get(i));
                i--;
            } else
                bullets.get(i).update();
        }

        if (shootEnabled) {
            BulletEnemy bullet = null;
            bullet = new BulletEnemy(x + img.getWidth(null) / 2, y + img.getHeight(null), Utils.loadImage("res/enemy_bullet.png"));
            bullets.add(bullet);
            shootEnabled = false;
            countDown = 50;
        } else {
            countDown--;
            if (countDown == 0) {
                shootEnabled = true;
            }

        }
    }

    public void draw(Graphics g) {
        for (BulletEnemy b : bullets) {
            b.draw(g);
        }
        g.drawImage(img, x, y, null);
    }
}
