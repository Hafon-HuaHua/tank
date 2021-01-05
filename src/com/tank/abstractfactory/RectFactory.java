package com.tank.abstractfactory;

import com.tank.DirEnum;
import com.tank.GroupEnum;
import com.tank.TankFrame;

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
    public BaseTank createTank(int x, int y, DirEnum dir, GroupEnum group, TankFrame tf) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, TankFrame tf) {
        return new RectBullet(x,y,dir,groupEnum,tf);
    }

    @Override
    public BaseBoom createBoom(int x, int y, TankFrame tf) {
        return new RectBoom(x,y,tf);
    }
}
