package game.models;

import java.awt.*;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class GameRect {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean invicible;
    private boolean isDead;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isInvicible() {
        return invicible;
    }

    public void setInvicible(boolean invicible) {
        this.invicible = invicible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameRect(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GameRect() {

    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

//    public boolean intersects(GameRect gameRect) {
//        if (x > gameRect.getX() - width && x < gameRect.getX() + gameRect.getWidth()
//                && y > gameRect.getY() - height && y < gameRect.getY() + gameRect.getHeight())
//            return true;
//        return false;
//    }

    public boolean intersects(GameRect gameRect) {
        Rectangle rect1 = new Rectangle(x, y, width, height);
        Rectangle rect2 = new Rectangle(gameRect.x, gameRect.y, gameRect.width, gameRect.height);
        return rect1.intersects(rect2);
    }


}
