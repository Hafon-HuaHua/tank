package com.tank.abstractfactory;

import com.tank.DirEnum;
import com.tank.GroupEnum;
import com.tank.facecade.GameModel;
import com.tank.facecade.GameObject;

public class RectFactory extends GameFactory{
    private static volatile RectFactory rectFactory;

    private RectFactory(){}

    public static RectFactory getInstance(){
        if(rectFactory == null){
            synchronized (RectFactory.class){
                if(rectFactory == null){
                    rectFactory = new RectFactory();
                }
            }
        }
        return rectFactory;
    }

    @Override
    public GameObject createTank(int x, int y, DirEnum dir, GroupEnum group, GameModel gm) {
        return null;
    }

    @Override
    public GameObject createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, GameModel gm) {
        return new RectBullet(x,y,dir,groupEnum,gm);
    }

    @Override
    public GameObject createBoom(int x, int y, GameModel gm) {
        return new RectBoom(x,y,gm);
    }
}
