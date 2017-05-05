package game.controllers;

import game.Collider;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zTitiK on 03-May-17.
 */
public class ControllerManager {
    private ArrayList<Controller> controllers;

    public static final ControllerManager instance = new ControllerManager();

    private ControllerManager() {
        controllers = new ArrayList<>();
    }

    public ArrayList<Controller> getControllers() {
        return controllers;
    }

    public void add(Controller controller) {
        controllers.add(controller);
    }

    public void draw(Graphics graphics) {
        for (Controller controller : controllers) {
            controller.draw(graphics);
        }
    }

    public void update() {
        //remove dead obj
        Iterator<Controller> controllerIterator = controllers.iterator();
        while (controllerIterator.hasNext()) {
            Controller controller = controllerIterator.next();
            if (controller.gameRect.isDead()) {
                controllerIterator.remove();
            }
        }

        for (Controller controller : controllers) {
            controller.update();
        }
    }
}
