package game;

import game.controllers.CollisionManager;
import game.controllers.Controller;
import game.controllers.ControllerManager;
import game.enemies.Behaviors;
import game.enemies.DiagBehaviors;
import game.enemies.EnemyController;
import game.scenes.GameScene;
import game.scenes.StartScene;
import game.utils.Utils;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by zTitiK on 08-Apr-17.
 */
public class GameWindow extends Frame {

    BufferedImage backBufferImage;
    Graphics backBufferGraphics;
    GameScene currentScene;
    public static GameWindow instance;

    public void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }

    public GameWindow() {
        instance = this;

        this.setVisible(true);
        setSize(600, 700);
        setTitle("1945");

        currentScene = new StartScene();


        backBufferImage = new BufferedImage(600, 700, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics = backBufferImage.getGraphics();

        // listener
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                //dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
//        addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                currentScene.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentScene.keyReleased(e);
            }
        });


        Thread gameLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    Iterator<Controller> controllerIterator = ControllerManager.instance.getControllers().iterator();
                    while (controllerIterator.hasNext()) {
                        Controller controller = controllerIterator.next();
                        if (controller.getGameRect().getY() > 700
                                || controller.getGameRect().getX() < 0
                                || controller.getGameRect().getX() > 600) {
                            controllerIterator.remove();
                        }
                    }
                    ControllerManager.instance.update();
//
//                    for (int i = 0; i < bullets.size(); i++) {
//                        for (int j = 0; j < enemies.size(); j++) {
//                            if (enemies.get(j).getGameRect().intersects(bullets.get(i).getGameRect())) {
//                                System.out.println("Boom");
//                                enemies.remove(enemies.get(j));
//                                bullets.remove(bullets.get(i));
//                                i--;
//                                break;
//                            }
//                        }
//                    }
                    currentScene.update();
                    CollisionManager.instance.update();


                    // Draw
                    repaint();
                }
            }
        });
        gameLoop.start();
    }


    //2 Draw
    @Override
    public void update(Graphics g) {
        currentScene.draw(backBufferGraphics);
        ControllerManager.instance.draw(backBufferGraphics);


        g.drawImage(backBufferImage, 0, 0, null);
    }
}
