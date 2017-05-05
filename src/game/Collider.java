package game;

import game.models.GameRect;

/**
 * Created by zTitiK on 26-Apr-17.
 */
public interface Collider {
    public GameRect getGameRect();
    void onCollide(Collider other);
}
