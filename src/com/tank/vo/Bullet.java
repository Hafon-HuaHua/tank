package com.tank.vo;

import com.tank.enums.DirEnum;
import com.tank.enums.GroupEnum;
import com.tank.main.TankFrame;
import com.tank.util.PropertiesMgr;
import com.tank.util.ResourceMgr;

import java.awt.*;
import java.io.IOException;

public class Bullet {

    private static final int SPEED = PropertiesMgr.getIntVal("bulletSpeed");
    private int x;
    private int y;
    private DirEnum dir;
    private TankFrame tk;
    public static int WEIGHT = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean isLive = true;
    private GroupEnum groupEnum = GroupEnum.BAD;
    private Rectangle rect = new Rectangle();

    public Bullet(int x, int y, DirEnum dir, GroupEnum groupEnum, TankFrame tk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.groupEnum = groupEnum;
        this.tk = tk;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WEIGHT;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g){
        if(!isLive){
            tk.bullets.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
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
        if(x < 0 || y < 0 || x > tk.GAME_WIDTH || y > tk.GAME_HEIGHT){
            isLive = false;
        }
    }

    /**
     * 销毁坦克
     * @param tank
     */
    public void destoryTanks(Tank tank) {
        if(this.groupEnum == tank.getGroup()){
            return;
        }
//        Rectangle r1 = new Rectangle(this.x,this.y,Bullet.WEIGHT,Bullet.HEIGHT);
//        Rectangle r2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        /*通过Rectangle判断碰撞*/
        if(rect.intersects(tank.getRect())){
            this.die();
            try {
                tank.die();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 子弹消失
     */
    private void die() {
        this.isLive = false;
    }
}
