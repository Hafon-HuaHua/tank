package com.tank.vo;

import com.tank.enums.DirEnum;
import com.tank.enums.Group;
import com.tank.main.TankFrame;
import com.tank.util.ResourceMgr;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 2;
    private int x;
    private int y;
    private DirEnum dir;
    private TankFrame tk;
    public static int WEIGHT = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean isLive = true;
    private Group group = Group.BAD;
    public Bullet(int x, int y, DirEnum dir,Group group,TankFrame tk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tk = tk;
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
        if(x < 0 || y < 0 || x > tk.getGAME_WIDTH() || y > tk.getGAME_HEIGHT()){
            isLive = false;
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

    public void destoryTanks(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }
        Rectangle r1 = new Rectangle(this.x,this.y,Bullet.WEIGHT,Bullet.HEIGHT);
        Rectangle r2 = new Rectangle(tank.getX(),tank.getY(),Tank.WEIGHT,Tank.HEIGHT);
        if(r1.intersects(r2)){
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.isLive = false;
    }
}
