import models.GameRect;
import views.ImageRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by zTitiK on 12-Apr-17.
 */
public class Bullet {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;

    public Bullet() throws IOException {
    }

    public Bullet(int bulletX, int bulletY, Image img) throws IOException {
        imageRenderer = new ImageRenderer(img);
        gameRect = new GameRect(bulletX - img.getWidth(null) / 2, bulletY - img.getHeight(null), img.getWidth(null), img.getHeight(null));

    }

    public void update() {
        gameRect.move(0,-15);
    }

    public void draw(Graphics g) {
        imageRenderer.render(g, gameRect);
    }

    public GameRect getGameRect() {
        return gameRect;
    }
}
