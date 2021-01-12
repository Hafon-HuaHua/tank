package com.tank.chain;

import com.tank.Tank;
import com.tank.Wall;
import com.tank.facecade.GameObject;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject g1, GameObject g2) {
        if(g1 instanceof Tank && g2 instanceof Wall){
            Tank t = (Tank)g1;
            Wall w = (Wall) g2;
            if(t.getRect().intersects(w.getRectangle())){
                t.setX(t.getOldX());
                t.setY(t.getOldY());
            }
        }
        return true;
    }
}
