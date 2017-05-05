package game.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by zTitiK on 05-May-17.
 */
public interface GameScene {
    void update();

    void draw(Graphics graphics);

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);
}
