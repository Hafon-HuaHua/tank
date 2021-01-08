package com.tank;

public class BigBulletStrategy implements FireStrategy{
    @Override
    public void fire(Tank tk) {
        int bX = tk.getX() + Tank.WIDTH/2 - Bullet.WEIGHT/2;
        int bY = tk.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tk.getGm().getGameObjects().add(new Bullet(bX,bY,tk.getDir(),tk.getGroup(),tk.getGm()));
    }
}
