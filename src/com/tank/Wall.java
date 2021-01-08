package com.tank;

import com.tank.facecade.GameObject;

import java.awt.*;

public class Wall extends GameObject {
    private int w,h;
    private Rectangle rectangle;
    public Wall(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x,y,w,h);
    }
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,w,h);
    }
}