package com.tank.abstractfactory;

import com.tank.AudioUtil;
import com.tank.ResourceMgr;
import com.tank.facecade.GameModel;

import java.awt.*;

public class RectBoom extends BaseBoom {
    private int x;
    private int y;
    private GameModel gm;
    /*爆炸*/
    private int step = 0;

    public RectBoom(int x, int y, GameModel gm){
        this.x = x;
        this.y = y;
        this.gm = gm;
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
            gm.getBooms().remove(this);
        }
    }
}
