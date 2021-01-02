package com.tank.service;

import com.tank.vo.Bullet;
import com.tank.vo.Tank;

public class BigBulletStrategy implements FireStrategy{
    @Override
    public void fire(Tank tk) {
        int bX = tk.getX() + Tank.WIDTH/2 - Bullet.WEIGHT/2;
        int bY = tk.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tk.getTf().bullets.add(new Bullet(bX,bY,tk.getDir(),tk.getGroup(),tk.getTf()));
    }
}
