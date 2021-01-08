package com.tank;

import com.tank.facecade.GameModel;
import com.tank.facecade.GameObject;

import java.awt.*;

/**
 * 爆炸类
 */
public class Boom extends GameObject {
    public static final int WIDTH = ResourceMgr.boomImages[0].getWidth();
    public static final int HEIGHT = ResourceMgr.boomImages[0].getHeight();
    private GameModel gm;
    /*爆炸*/
    private int step = 0;

    public Boom(int x, int y, GameModel gm){
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
        g.drawImage(ResourceMgr.boomImages[step++],this.x,this.y,null);
        if(step >= ResourceMgr.boomImages.length){
            gm.getGameObjects().remove(this);
        }
    }
}
