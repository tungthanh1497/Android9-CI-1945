package game.scenes;

import game.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by zTitiK on 05-May-17.
 */
public class StartScene implements GameScene {
    Image backgroundImage;
    Image content;

    @Override
    public void update() {
        try {
            backgroundImage = ImageIO.read(new File("res/background.png"));
            content = ImageIO.read(new File("res/1945ayr.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, null);
        graphics.drawImage(content, 150, 250, 311, 123, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameWindow.instance.setCurrentScene(new Level1Scene());
        }
    }
}
