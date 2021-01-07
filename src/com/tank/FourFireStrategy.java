package com.tank;

public class FourFireStrategy implements FireStrategy{
    private FourFireStrategy(){}
    static class FourFireStrategyInner{
        private static final FourFireStrategy FOUR_FIRE_STRATEGY = new FourFireStrategy();
    }
    public static FourFireStrategy getInstance(){
        return FourFireStrategyInner.FOUR_FIRE_STRATEGY;
    }
    @Override
    public void fire(Tank tk) {
        int bX = tk.getX() + Tank.WIDTH/2 - Bullet.WEIGHT/2;
        int bY = tk.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        for(DirEnum dir : DirEnum.values()){
            tk.getGm().getBullets().add(tk.getGm().getGameFactory().createBullet(bX,bY,dir,tk.getGroup(),tk.getGm()));
        }
    }
}
