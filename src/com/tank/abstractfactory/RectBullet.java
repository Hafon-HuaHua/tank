package com.tank.abstractfactory;

import com.tank.*;
import com.tank.facecade.GameModel;

import java.awt.*;

public class RectBullet extends BaseBullet{
    private static final int SPEED = PropertiesMgr.getIntVal("bulletSpeed");
    private int x;
    private int y;
    private DirEnum dir;
    private GameModel gm;
    public static int WEIGHT = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean isLive = true;
    private GroupEnum groupEnum = GroupEnum.BAD;
    private Rectangle rect = new Rectangle();

    public RectBullet(int x, int y, DirEnum dir, GroupEnum groupEnum, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.groupEnum = groupEnum;
        this.gm = gm;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WEIGHT;
        rect.height = HEIGHT;
    }
    @Override
    public void paint(Graphics g){
        if(!isLive){
            gm.getBullets().remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,20,20);
        g.setColor(c);
        moving();
    }
    private void moving(){
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        rect.x = this.x;
        rect.y = this.y;
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            isLive = false;
        }
    }

    /**
     * 销毁坦克
     * @param tank
     */
    @Override
    public void destoryTanks(Tank tank) {
        if(this.groupEnum == tank.getGroup()){
            return;
        }
//        Rectangle r1 = new Rectangle(this.x,this.y,Bullet.WEIGHT,Bullet.HEIGHT);
//        Rectangle r2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        /*通过Rectangle判断碰撞*/
        if(rect.intersects(tank.getRect())){
            this.die();
            tank.die();
        }
    }

    /**
     * 子弹消失
     */
    private void die() {
        this.isLive = false;
    }
}
