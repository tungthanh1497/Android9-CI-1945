package game.enemies;

import game.models.GameRect;

/**
 * Created by zTitiK on 16-Apr-17.
 */
public class DiagBehaviors extends Behaviors {
    @Override
    public void doMoving(GameRect gameRect) {
        gameRect.move(2,4);
    }
}
