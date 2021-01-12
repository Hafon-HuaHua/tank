package com.tank.chain;

import com.tank.Bullet;
import com.tank.Tank;
import com.tank.Wall;
import com.tank.facecade.GameObject;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject g1, GameObject g2) {
        if(g1 instanceof Bullet && g2 instanceof Wall){
            Bullet b = (Bullet)g1;
            Wall w = (Wall) g2;
            if(b.getRect().intersects(w.getRectangle())){
                b.die();
            }
        }else if(g1 instanceof Wall && g2 instanceof Bullet){
            return collide(g2,g1);
        }
        return true;
    }
}
