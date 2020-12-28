package com.tank.vo;

import com.tank.enums.DirEnum;
import com.tank.main.TankFrame;
import com.tank.util.ResourceMgr;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 2;
    private int x;
    private int y;
    private DirEnum dir;
    private TankFrame tk;

    private boolean isPaint = true;

    public Bullet(int x, int y, DirEnum dir,TankFrame tk) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tk = tk;
    }

    public void paint(Graphics g){
        if(!isPaint){
            tk.bullets.remove(this);
        }

        /*if(tk.enemyTanks.size() > 0){
            for(Iterator<Tank> itor = tk.enemyTanks.iterator();itor.hasNext();){
                if(!itor.next().isLive()){
                    itor.remove();
                    tk.repaint();
                    tk.bullets.remove(this);
                    break;
                }
            }
        }*/
        Color c = g.getColor();
        g.setColor(Color.WHITE);
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
        g.setColor(c);
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
            isPaint = false;
        }
        /*for(Iterator<Tank> itor = tk.enemyTanks.iterator();itor.hasNext();){
            Tank tank = itor.next();
            if((x >= tank.getX() && y <= tank.getY()) && (x < tk.getGAME_WIDTH() && y < tk.getGAME_HEIGHT())){
                tank.setLive(false);
                break;
            }
        }*/
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
}
