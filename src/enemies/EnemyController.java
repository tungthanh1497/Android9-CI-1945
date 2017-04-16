package enemies;

import models.GameRect;
import utils.Utils;
import views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class EnemyController {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    private Behaviors behaviors;
    int dx;
    int dy;
    ArrayList<BulletEnemy> bullets;
    boolean shootEnabled = true;
    int countDown;

    public GameRect getGameRect() {
        return gameRect;
    }

    public ImageRenderer getImageRenderer() {
        return imageRenderer;
    }

    public EnemyController(int x, int y, Image image) {
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        imageRenderer = new ImageRenderer(image);
        bullets = new ArrayList<>();
    }

    public void setBehaviors(Behaviors behaviors) {
        this.behaviors = behaviors;
    }

    public EnemyController() {

    }

    public void update() {
//        dx = 0;
//        dy = 0;
//        if (Utils.getRandom() == 1) {
//            dx += 10; // right
//        }
//        if (Utils.getRandom() == 1) {
//            dx -= 10; // left
//        }
//        //if (getRandom() == 1) {
//        dy += 2; //down
//        // }
//
//        gameRect.move(dx, dy);

        if(behaviors!=null){
            behaviors.doMoving(gameRect);
        }


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
            bullet = new BulletEnemy(gameRect.getX() + imageRenderer.getImage().getWidth(null) / 2, gameRect.getY() + imageRenderer.getImage().getHeight(null), Utils.loadImage("res/enemy_bullet.png"));
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
        imageRenderer.render(g, gameRect);
    }
}
