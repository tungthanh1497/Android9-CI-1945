package game.controllers;

import game.Collider;
import game.models.GameRect;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zTitiK on 26-Apr-17.
 */
public class CollisionManager {

    public static final CollisionManager instance = new CollisionManager();

    private ArrayList<Collider> colliders;

    private CollisionManager() {
        colliders = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                Collider ci = colliders.get(i);
                Collider cj = colliders.get(j);

                GameRect recti = ci.getGameRect();
                GameRect rectj = cj.getGameRect();

                if(recti.intersects(rectj)){
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                }
            }
        }

        Iterator<Collider> colliderIterator = colliders.iterator();
        while(colliderIterator.hasNext()){
            Collider collider = colliderIterator.next();
            if(collider.getGameRect().isDead()){
                colliderIterator.remove();
            }
        }
    }

    public void add(Collider collider){
        colliders.add(collider);
    }
}
