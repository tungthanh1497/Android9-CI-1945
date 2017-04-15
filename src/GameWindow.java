import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zTitiK on 08-Apr-17.
 */
public class GameWindow extends Frame {
    Random rand = new Random();
    Image bgImage;
    Player player;

    BufferedImage backBufferImage;
    Graphics backBufferGraphics;


    ArrayList<Enemy> enemies = new ArrayList<>();

    boolean creatNewEnemyEnable = true;
    int countDown;
    InputManager inputManager = new InputManager();


    public GameWindow() {
        this.setVisible(true);
        setSize(600, 700);
        setTitle("1945");

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

            @Override
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
        });


        //1 Load image
        player = new Player(this.getWidth() / 2, this.getHeight(), Utils.loadImage("res/plane3.png"));
        bgImage = Utils.loadImage("res/background.png");
        // 2 Draw

        Thread gameLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (creatNewEnemyEnable) {
                        Enemy enemy = new Enemy(getRandomX(), 0, Utils.loadImage("res/enemy-green-3.png"));
                        enemies.add(enemy);
                        creatNewEnemyEnable = false;
                        countDown = 50;
                    } else {
                        countDown--;
                        if (countDown == 0) {
                            creatNewEnemyEnable = true;
                        }
                    }

                    for (int i = 0; i < enemies.size(); i++) {
                        if (enemies.get(i).getY() > 700 || enemies.get(i).getX() < 0 || enemies.get(i).getX() > 600) {
                            enemies.remove(enemies.get(i));
                            i--;
                        } else
                            enemies.get(i).update();
                    }

                    // Logic
                    player.processInput(inputManager);
                    player.update();


                    // Draw
                    repaint();
                }
            }
        });
        gameLoop.start();
    }



    private int getRandomX() {
        return (rand.nextInt(this.getWidth()) + 1);
    }

    //2 Draw
    @Override
    public void update(Graphics g) {
        backBufferGraphics.drawImage(bgImage, 0, 0, 600, 700, null);
        player.draw(backBufferGraphics);
        for (Enemy e : enemies) {
            e.draw(backBufferGraphics);
        }

        g.drawImage(backBufferImage, 0, 0, null);
    }
}
