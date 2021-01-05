package com.tank.abstractfactory;

import com.tank.AudioUtil;
import com.tank.ResourceMgr;
import com.tank.TankFrame;

import java.awt.*;

public class RectBoom extends BaseBoom {
    private int x;
    private int y;
    private TankFrame tf;
    /*爆炸*/
    private int step = 0;

    public RectBoom(int x, int y, TankFrame tf){
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
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,step*5,step*5);
        step++;
        g.setColor(c);
        if(step >= ResourceMgr.boomImages.length){
            tf.booms.remove(this);
        }
    }
}