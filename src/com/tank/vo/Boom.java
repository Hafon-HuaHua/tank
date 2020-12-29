package com.tank.vo;

import com.tank.main.TankFrame;
import com.tank.util.AudioUtil;
import com.tank.util.ResourceMgr;

import java.awt.*;
import java.io.IOException;

public class Boom {
    public static final int WIDTH = ResourceMgr.boomImages[0].getWidth();
    public static final int HEIGHT = ResourceMgr.boomImages[0].getHeight();
    private int x;
    private int y;
    private TankFrame tk;
    private int step = 0;
    public Boom(int x, int y, TankFrame tk) throws IOException {
        this.x = x;
        this.y = y;
        this.tk = tk;
        new Thread(() -> new AudioUtil("audio/explode.wav").play()).start();
    }
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.boomImages[step++],this.x,this.y,null);
        if(step >= ResourceMgr.boomImages.length){
            tk.booms.remove(this);
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

    public TankFrame getTk() {
        return tk;
    }

    public void setTk(TankFrame tk) {
        this.tk = tk;
    }
}
