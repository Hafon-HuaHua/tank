package com.tank.main;

import com.tank.enums.DirEnum;
import com.tank.enums.GroupEnum;
import com.tank.vo.Boom;
import com.tank.vo.Bullet;
import com.tank.vo.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    public Tank tank = new Tank(200,400, DirEnum.UP, GroupEnum.GOOD,this);
    //敌方坦克
    public List<Tank> enemyTanks = new ArrayList<>();
    public List<Bullet> bullets = new ArrayList<>();
    public List<Boom> booms = new ArrayList<>();
    private final int GAME_WIDTH = 1000,GAME_HEIGHT = 1000;

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        setBackground(Color.BLACK);
        setLocation(400,200);
        //初始化敌方坦克
        initEnemyTanks();
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
    private void initEnemyTanks(){
        for(int i = 0; i < 4;i++){
            Tank tank = new Tank(180 + i * 10,200,DirEnum.DOWN, GroupEnum.BAD,this);
            enemyTanks.add(tank);
        }
    }
    /**
     * 双缓冲消除画面闪烁
     */
    @Override
    public void update(Graphics g) {
        Image image = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        Graphics gOffscreen = image.getGraphics();
        Color c = gOffscreen.getColor();
        gOffscreen.setColor(Color.BLACK);
        gOffscreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffscreen.setColor(c);
        paint(gOffscreen);
        g.drawImage(image,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(),10,40);
        g.drawString("敌方坦克的数量：" + enemyTanks.size(),10,80);
        g.drawString("爆炸数量：" + booms.size(),10,120);
        g.setColor(c);
        tank.paint(g);
        for(int i = 0; i<enemyTanks.size();i++){
            enemyTanks.get(i).paint(g);
        }
        for(int i = 0; i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        for(int i = 0; i<booms.size();i++){
            booms.get(i).paint(g);
        }
        for(int i = 0;i < bullets.size();i++){
            for(int j = 0; j < enemyTanks.size();j++){
                bullets.get(i).destoryTanks(enemyTanks.get(j));
            }
        }

    }
    class MyKeyListener extends KeyAdapter{
        boolean vl = false;
        boolean vr = false;
        boolean vu = false;
        boolean vd = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT :
                    vl = true;
                    break;
                case KeyEvent.VK_RIGHT :
                    vr = true;
                    break;
                case KeyEvent.VK_UP :
                    vu = true;
                    break;
                case KeyEvent.VK_DOWN :
                    vd = true;
                    break;
            }
            setMarkDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT :
                    vl = false;
                    break;
                case KeyEvent.VK_RIGHT :
                    vr = false;
                    break;
                case KeyEvent.VK_UP :
                    vu = false;
                    break;
                case KeyEvent.VK_DOWN :
                    vd = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
            }
            setMarkDir();
        }

        private void setMarkDir(){
            if(!vl && !vr && !vu && !vd){
                tank.setMoving(false);
            }else{
                tank.setMoving(true);
            }
            if(vl){
                tank.setDir(DirEnum.LEFT);
            }else if(vr){
                tank.setDir(DirEnum.RIGHT);;
            }else if(vu){
                tank.setDir(DirEnum.UP);;
            }else if(vd){
                tank.setDir(DirEnum.DOWN);;
            }
        }
    }

    public int getGAME_WIDTH() {
        return GAME_WIDTH;
    }

    public int getGAME_HEIGHT() {
        return GAME_HEIGHT;
    }
}
