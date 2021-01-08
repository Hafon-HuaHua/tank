package com.tank.chain;

import com.tank.Bullet;
import com.tank.Tank;
import com.tank.chain.Collider;
import com.tank.facecade.GameObject;

public class TankBulletCollider implements Collider {
    @Override
    public boolean collide(GameObject g1, GameObject g2) {
        if(g1 instanceof Bullet && g2 instanceof Tank){
            Bullet bullet = (Bullet)g1;
            Tank t = (Tank)g2;
            bullet.destoryTanks(t);
        }else if(g1 instanceof Tank && g2 instanceof Bullet){
            collide(g2,g1);
        }else{
            return false;
        }
        return true;
    }
}
