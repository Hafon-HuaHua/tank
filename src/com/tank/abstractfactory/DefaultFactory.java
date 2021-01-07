package com.tank.abstractfactory;

import com.tank.*;
import com.tank.facecade.GameModel;

public class DefaultFactory extends GameFactory {
    private DefaultFactory(){}

    static class DefaultFactoryInner{
        private static final DefaultFactory defaultFactory = new DefaultFactory();
    }
    public static DefaultFactory getInstance(){
        return DefaultFactoryInner.defaultFactory;
    }
    @Override
    public BaseTank createTank(int x, int y, DirEnum dir, GroupEnum group, GameModel gm) {
        return new Tank(x,y,dir,group,gm);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, GameModel gm) {
        return new Bullet(x,y,dir,groupEnum,gm);
    }

    @Override
    public BaseBoom createBoom(int x, int y, GameModel gm) {
        return new Boom(x,y,gm);
    }
}
