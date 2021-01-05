package com.tank;

public class SingleBulletFireStrategy implements FireStrategy{
    private static class SingleBulletFireInner{
        private static final SingleBulletFireStrategy instance = new SingleBulletFireStrategy();
    }
    @Override
    public void fire(Tank tk) {
        int bX = tk.getX() + Tank.WIDTH/2 - Bullet.WEIGHT/2;
        int bY = tk.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tk.getTf().bullets.add(new Bullet(bX,bY,tk.getDir(),tk.getGroup(),tk.getTf()));
    }
    public static SingleBulletFireStrategy getInstance(){
        return SingleBulletFireInner.instance;
    }
}
