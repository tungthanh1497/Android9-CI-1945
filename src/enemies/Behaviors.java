package enemies;

import models.GameRect;

/**
 * Created by zTitiK on 15-Apr-17.
 */
public class Behaviors {
    public void doMoving(GameRect gameRect) {
        gameRect.move(0, 2);
    }
}
