package com.tank.abstractfactory;

import com.tank.DirEnum;
import com.tank.GroupEnum;
import com.tank.facecade.GameModel;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, DirEnum dir, GroupEnum group, GameModel gm);
    public abstract BaseBullet createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, GameModel gm);
    public abstract BaseBoom createBoom(int x, int y, GameModel gm);
}
