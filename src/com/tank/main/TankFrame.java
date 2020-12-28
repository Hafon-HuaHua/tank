package com.tank.main;

import com.tank.vo.Bullet;
import com.tank.vo.Tank;
import com.tank.enums.DirEnum;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame {

    public Tank tank = new Tank(200,200, DirEnum.DOWN,this);
    //敌方坦克
    public List<Tank> enemyTanks = new ArrayList<>();
    public List<Bullet> bullets = new ArrayList<>();
    private final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        setBackground(Color.BLACK);
        setLocation(400,200);
        //初始化敌方坦克
        //initEnemyTanks();
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
    private void initEnemyTanks(){
        Random random = new Random();
        for(int i = 0; i < 2;i++){
            Tank tank = new Tank(random.nextInt(300 + random.nextInt(30)),random.nextInt(300 + random.nextInt(50)),DirEnum.DOWN,this);
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
        tank.paint(g);
        /*for(int i = 0; i<enemyTanks.size();i++){
            Color c1 = g.getColor();
            g.setColor(Color.WHITE);
            Tank t = enemyTanks.get(i);
            g.drawString("x:" + t.getX() + ",y:" + t.getY(),t.getX() - 5,t.getY() - 5);
            g.setColor(c1);
            enemyTanks.get(i).paint(g);
        }*/
        for(int i = 0; i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        //bullets.stream().forEach(a -> a.paint(g));
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
