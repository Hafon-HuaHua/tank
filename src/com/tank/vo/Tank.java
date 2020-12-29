package com.tank.vo;

import com.tank.enums.DirEnum;
import com.tank.enums.Group;
import com.tank.main.TankFrame;
import com.tank.util.ResourceMgr;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x;
    private int y;
    private DirEnum dir = DirEnum.DOWN;
    private static final int SPEED = 1;
    private boolean isLive = true;
    private boolean moving = true;
    public static int WEIGHT = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();
    private TankFrame tk;
    private Random r = new Random();
    private Group group = Group.BAD;
    public Tank(int x, int y, DirEnum dir,Group group,TankFrame tk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tk = tk;
    }

    public void paint(Graphics g){
        if(!isLive){
            tk.enemyTanks.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }
        moving();
    }
    private void moving(){
        if(!moving){
            return;
        }
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
        if(r.nextInt(40) == 9){
            this.fire();
        }
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirEnum getDir() {
        return dir;
    }

    public void setDir(DirEnum dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void fire() {
        int bX = this.x + Tank.WEIGHT/2 - Bullet.WEIGHT/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tk.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tk));
    }

    public void die() {
        this.isLive = false;
    }
}
