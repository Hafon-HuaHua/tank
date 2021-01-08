package com.tank.chain;

import com.tank.facecade.GameObject;

public interface Collider {
    boolean collide(GameObject g1, GameObject g2);
}
