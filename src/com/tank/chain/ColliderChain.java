package com.tank.chain;

import com.tank.facecade.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {

    private List<Collider> list = new LinkedList<>();

    public ColliderChain(){
        add(new TankBulletCollider());
        add(new TankTankCollider());
    }
    public void add(Collider c){
        list.add(c);
    }

    @Override
    public boolean collide(GameObject g1, GameObject g2) {
        for(int i = 0;i < list.size();i++){
            if(!list.get(i).collide(g1,g2)){
                return false;
            }
        }
        return true;
    }
}
