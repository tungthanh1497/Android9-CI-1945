import models.GameRect;
import utils.Utils;
import views.ImageRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zTitiK on 12-Apr-17.
 */
public class PlayerController {
    private GameRect gameRect;
    private int dx;
    private int dy;
    private ImageRenderer imageRenderer;


    private ArrayList<Bullet> bullets;
    private boolean shootEnabled;
    private int countDown;

    public GameRect getGameRect() {
        return gameRect;
    }

    public ImageRenderer getImageRenderer() {
        return imageRenderer;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public PlayerController(int x, int y, Image img) {
//        this.x = x - img.getWidth(null) / 2;
//        this.y = y - img.getHeight(null);
        dx = 0;
        dy = 0;
        gameRect = new GameRect(x - img.getWidth(null) / 2, y - img.getHeight(null), 70, 50);
        imageRenderer = new ImageRenderer(img);
        bullets = new ArrayList<>();
        shootEnabled = true;
    }

    public void processInput(InputManager inputManager) {
        dx = 0;
        dy = 0;
        if (inputManager.isRightPressed()) {
            if ((gameRect.getX() + 10) <= (600 - 70)) {
                dx += 10;
            }
        }
        if (inputManager.isLeftPressed()) {
            if ((gameRect.getX() - 10) >= 0) {
                dx -= 10;
            }
        }
        if (inputManager.isUpPressed()) {
            if ((gameRect.getY() - 40) >= 0) {
                dy -= 10;
            }
        }
        if (inputManager.isDownPressed()) {
            if ((gameRect.getY() + 10) <= 700 - 50) {
                dy += 10;
            }
        }
        if (inputManager.isSpacePressed()) {
            if (shootEnabled) {
                Bullet bullet = null;
                try {
                    bullet = new Bullet(gameRect.getX() + imageRenderer.getImage().getWidth(null) / 2, gameRect.getY(), Utils.loadImage("res/bullet.png"));
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
        imageRenderer.render(g, gameRect);
        for (Bullet b : bullets) {
            b.draw(g);
        }
    }

    public void update() {
        gameRect.move(dx, dy);

        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getGameRect().getY() < 0) {
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
