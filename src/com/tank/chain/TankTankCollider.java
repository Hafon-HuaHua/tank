package com.tank.chain;

import com.tank.Tank;
import com.tank.facecade.GameObject;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject g1, GameObject g2) {
        if(g1 instanceof Tank && g2 instanceof Tank){
            Tank t = (Tank)g1;
            Tank t1 = (Tank) g2;
            if(t.getRect().intersects(t1.getRect())){
                t.setX(t.getOldX());
                t.setY(t.getOldY());
                t1.setX(t1.getOldX());
                t1.setY(t1.getOldY());
            }
        }
        return true;
    }
}
