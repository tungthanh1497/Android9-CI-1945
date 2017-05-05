package game.controllers;

import game.models.GameRect;
import game.views.ImageRenderer;

import java.awt.*;

/**
 * Created by zTitiK on 26-Apr-17.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRenderer imageRenderer;

    public Controller() {
    }

    public GameRect getGameRect() {
        return gameRect;
    }



    public Controller(GameRect gameRect, ImageRenderer imageRenderer) {
        this.gameRect = gameRect;
        this.imageRenderer = imageRenderer;
    }

    public void draw(Graphics graphics) {
        if(gameRect.isInvicible())
            return;
        imageRenderer.render(graphics, gameRect);
    }

    public void update() {
    }
}
