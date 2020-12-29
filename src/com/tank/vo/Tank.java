package com.tank.vo;

import com.tank.enums.DirEnum;
import com.tank.enums.GroupEnum;
import com.tank.main.TankFrame;
import com.tank.util.AudioUtil;
import com.tank.util.ResourceMgr;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Tank {
    private int x;
    private int y;
    private DirEnum dir = DirEnum.DOWN;
    private static final int SPEED = 1;
    private boolean isLive = true;
    private boolean moving = true;
    public static int WEIGHT = ResourceMgr.badTankD.getWidth();
    public static int HEIGHT = ResourceMgr.badTankD.getHeight();
    private TankFrame tk;
    private Random r = new Random();
    private GroupEnum groupEnum = GroupEnum.BAD;
    public Tank(int x, int y, DirEnum dir, GroupEnum groupEnum, TankFrame tk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.groupEnum = groupEnum;
        this.tk = tk;
    }

    public void paint(Graphics g){
        if(!isLive){
            tk.enemyTanks.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ?ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
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
        if(this.groupEnum == GroupEnum.BAD && r.nextInt(40) == 9){
            this.fire();
        }
        if(this.groupEnum == GroupEnum.BAD && r.nextInt(40) == 9){
            randomDir();
        }
    }

    private void randomDir() {
        this.dir = DirEnum.values()[r.nextInt(4)];
    }

    public GroupEnum getGroup() {
        return groupEnum;
    }

    public void setGroup(GroupEnum groupEnum) {
        this.groupEnum = groupEnum;
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
        if(this.groupEnum == GroupEnum.GOOD){
            new Thread(()-> new AudioUtil("audio/tank_fire.wav").play()).start();
        }
        int bX = this.x + Tank.WEIGHT/2 - Bullet.WEIGHT/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tk.bullets.add(new Bullet(bX,bY,this.dir,this.groupEnum,this.tk));
    }

    public void die() throws IOException {
        this.isLive = false;
        int eX = this.getX() + Tank.WEIGHT/2 - Boom.WIDTH/2;
        int eY = this.getY() + Tank.HEIGHT/2 - Boom.HEIGHT/2;
        tk.booms.add(new Boom(eX,eY,tk));
    }
}
