package com.tank.abstractfactory;

import com.tank.*;

public class DefaultFactory extends GameFactory {
    private DefaultFactory(){}

    static class DefaultFactoryInner{
        private static final DefaultFactory defaultFactory = new DefaultFactory();
    }
    public static DefaultFactory getInstance(){
        return DefaultFactoryInner.defaultFactory;
    }
    @Override
    public BaseTank createTank(int x, int y, DirEnum dir, GroupEnum group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, TankFrame tf) {
        return new Bullet(x,y,dir,groupEnum,tf);
    }

    @Override
    public BaseBoom createBoom(int x, int y, TankFrame tf) {
        return new Boom(x,y,tf);
    }
}
