package com.tank.abstractfactory;

import com.tank.DirEnum;
import com.tank.GroupEnum;
import com.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, DirEnum dir, GroupEnum group, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, TankFrame tf);
    public abstract BaseBoom createBoom(int x, int y, TankFrame tf);
}
