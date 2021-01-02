package com.tank.service;

import com.tank.enums.DirEnum;
import com.tank.vo.Bullet;
import com.tank.vo.Tank;

public class FourFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tk) {
        int bX = tk.getX() + Tank.WIDTH/2 - Bullet.WEIGHT/2;
        int bY = tk.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        for(DirEnum dir : DirEnum.values()){
            tk.getTf().bullets.add(new Bullet(bX,bY,dir,tk.getGroup(),tk.getTf()));
        }
    }
}
