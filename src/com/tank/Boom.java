package com.tank;

import com.tank.abstractfactory.BaseBoom;

import java.awt.*;
import java.io.IOException;

/**
 * 爆炸类
 */
public class Boom extends BaseBoom {
    public static final int WIDTH = ResourceMgr.boomImages[0].getWidth();
    public static final int HEIGHT = ResourceMgr.boomImages[0].getHeight();
    private int x;
    private int y;
    private TankFrame tf;
    /*爆炸*/
    private int step = 0;

    public Boom(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new AudioUtil("audio/explode.wav").play()).start();
    }

    /**
     * 画爆炸并判断爆炸数超过限制后移除爆炸效果
     * @param g
     */
    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.boomImages[step++],this.x,this.y,null);
        if(step >= ResourceMgr.boomImages.length){
            tf.booms.remove(this);
        }
    }
}
