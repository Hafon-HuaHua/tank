package com.tank.vo;

import com.tank.enums.DirEnum;
import com.tank.main.TankFrame;
import com.tank.util.ResourceMgr;

import java.awt.*;

public class Tank {
    private int x;
    private int y;
    private DirEnum dir = DirEnum.DOWN;
    private static final int SPEED = 10;
    private boolean isLive = true;
    private boolean moving = false;
    private TankFrame tk;
    public Tank(int x, int y, DirEnum dir,TankFrame tk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tk = tk;
    }

    public void paint(Graphics g){
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
        tk.bullets.add(new Bullet(this.x,this.y,this.dir,this.tk));
    }
}
