package com.tank.abstractfactory;

import com.tank.DirEnum;
import com.tank.GroupEnum;
import com.tank.facecade.GameModel;
import com.tank.facecade.GameObject;

public abstract class GameFactory {
    public abstract GameObject createTank(int x, int y, DirEnum dir, GroupEnum group, GameModel gm);
    public abstract GameObject createBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, GameModel gm);
    public abstract GameObject createBoom(int x, int y, GameModel gm);
}
