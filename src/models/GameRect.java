package models;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class GameRect {
    private int x;
    private int y;
    private int width;
    private int height;

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

    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    public boolean isMatch(GameRect gameRect){
        if(x>gameRect.getX()-width && x< gameRect.getX()+gameRect.getWidth()
                && y>gameRect.getY()-height && y<gameRect.getY()+gameRect.getHeight())
            return true;
        return false;
    }
}
