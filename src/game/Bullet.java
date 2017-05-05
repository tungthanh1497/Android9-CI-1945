package game;

import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.enemies.EnemyController;
import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;
import java.io.IOException;

/**
 * Created by zTitiK on 12-Apr-17.
 */
public class Bullet extends Controller implements Collider {

    private int damage = 1;

    public Bullet() throws IOException {
    }

    public Bullet(int bulletX, int bulletY, Image img) throws IOException {
        imageRenderer = new ImageRenderer(img);
        gameRect = new GameRect(bulletX - img.getWidth(null) / 2, bulletY - img.getHeight(null), img.getWidth(null), img.getHeight(null));

        CollisionManager.instance.add(this);

    }

    public void update() {
        gameRect.move(0, -15);
    }

    public void draw(Graphics g) {
        imageRenderer.render(g, gameRect);
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    @Override
    public void onCollide(Collider other) {

        if(other instanceof EnemyController){
            ((EnemyController) other).getHit();
        }
    }
}
