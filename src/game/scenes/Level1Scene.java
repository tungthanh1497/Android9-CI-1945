package game.scenes;

import game.Bullet;
import game.InputManager;
import game.PlayerController;
import game.controllers.ControllerManager;
import game.enemies.Behaviors;
import game.enemies.DiagBehaviors;
import game.enemies.EnemyController;
import game.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zTitiK on 05-May-17.
 */
public class Level1Scene implements GameScene {
    Random rand = new Random();
    Image bgImage;
    PlayerController player;
    private ArrayList<Bullet> bullets = new ArrayList<>();


    boolean creatNewEnemyEnable = true;
    int countDown;
    InputManager inputManager = new InputManager();

    public Level1Scene() {
        //1 Load image
        player = new PlayerController(600 / 2, 700, Utils.loadImage("res/plane3.png"));
        bgImage = Utils.loadImage("res/background.png");
        // 2 Draw


        // Logic

    }

    public void update() {
        if (creatNewEnemyEnable) {
            EnemyController enemy;
            System.out.println(ControllerManager.instance.getControllers().size());
            if (ControllerManager.instance.getControllers().size() > 4) {
                enemy = new EnemyController(getRandomX(), 0, Utils.loadImage("res/enemy-green-1.png"));
                enemy.setBehaviors(new DiagBehaviors());
            } else {
                enemy = new EnemyController(getRandomX(), 0, Utils.loadImage("res/enemy-green-3.png"));
                enemy.setBehaviors(new Behaviors());
            }
            ControllerManager.instance.add(enemy);
            creatNewEnemyEnable = false;
            countDown = 50;
        } else {
            countDown--;
            if (countDown == 0) {
                creatNewEnemyEnable = true;
            }
        }
        player.processInput(inputManager, bullets);
        player.update(bullets);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(bgImage, 0, 0, 600, 700, null);
        player.draw(graphics, bullets);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            inputManager.setRightPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            inputManager.setLeftPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            inputManager.setUpPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            inputManager.setDownPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            inputManager.setSpacePressed(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            inputManager.setRightPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            inputManager.setLeftPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            inputManager.setUpPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            inputManager.setDownPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            inputManager.setSpacePressed(false);
        }
    }

    private int getRandomX() {
        return (rand.nextInt(600) + 1);
    }
}
