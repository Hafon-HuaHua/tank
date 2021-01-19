package com.tank.vo;

import com.tank.main.TankFrame;
import com.tank.util.AudioUtil;
import com.tank.util.ResourceMgr;

import java.awt.*;
import java.io.IOException;

/**
 * 爆炸类
 */
public class Boom {
    public static final int WIDTH = ResourceMgr.boomImages[0].getWidth();
    public static final int HEIGHT = ResourceMgr.boomImages[0].getHeight();
    private int x;
    private int y;
    private TankFrame tk;
    /*爆炸*/
    private int step = 0;

    public Boom(int x, int y, TankFrame tk) throws IOException {
        this.x = x;
        this.y = y;
        this.tk = tk;
        new Thread(() -> new AudioUtil("audio/explode.wav").play()).start();
    }

    /**
     * 画爆炸并判断爆炸数超过限制后移除爆炸效果
     * @param g
     */
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.boomImages[step++],this.x,this.y,null);
        if(step >= ResourceMgr.boomImages.length){
            tk.booms.remove(this);
        }
    }
}
