package game.enemies;

import game.Collider;
import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.models.GameRect;
import game.utils.Utils;
import game.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class EnemyController extends Controller implements Collider {
    private Behaviors behaviors;
    ArrayList<BulletEnemy> bullets;
    boolean shootEnabled = true;
    int countDown;
    private int hp = 3;

    public GameRect getGameRect() {
        return gameRect;
    }

    @Override
    public void onCollide(Collider other) {

    }

    public ImageRenderer getImageRenderer() {
        return imageRenderer;
    }

    public EnemyController(int x, int y, Image image) {
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        imageRenderer = new ImageRenderer(image);

        CollisionManager.instance.add(this);

        bullets = new ArrayList<>();
    }

    public void setBehaviors(Behaviors behaviors) {
        this.behaviors = behaviors;
    }

    public EnemyController() {

    }

    public void update() {


        if (behaviors != null) {
            behaviors.doMoving(gameRect);
        }


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

    public void getHit() {
        System.out.println("get hit");
        hp--;
        System.out.println(hp);
        if (hp == 0) {
            gameRect.setDead(true);
        }
//        gameRect.setInvicible(true);
    }

//    public void draw(Graphics g) {
//        for (BulletEnemy b : bullets) {
//            b.draw(g);
//        }
//        imageRenderer.render(g, gameRect);
//    }
}
