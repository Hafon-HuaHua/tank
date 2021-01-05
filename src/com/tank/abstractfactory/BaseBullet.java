package com.tank.abstractfactory;

import com.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void destoryTanks(Tank tank);
}
