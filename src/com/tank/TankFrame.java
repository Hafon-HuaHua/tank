package com.tank;

import com.tank.facecade.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    /*窗口宽和高*/
    public static final int GAME_WIDTH = PropertiesMgr.getIntVal("gameWidth"),GAME_HEIGHT = PropertiesMgr.getIntVal("gameHeight");

    private GameModel gm = new GameModel();

    public TankFrame(){
        /*初始窗口属性*/
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        setBackground(Color.BLACK);
        setLocation(400,200);

        /*添加键盘按键监听*/
        this.addKeyListener(new MyKeyListener());
        /*添加窗口监听，点击窗口X时结束游戏进程，防止内存泄漏*/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
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

    /**
     * 窗口中绘画
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }
    /**
     * 内部类
     * 键盘按键按下和松开监听
     */
    class MyKeyListener extends KeyAdapter {
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

            new Thread(() -> new AudioUtil("audio/tank_move.wav").play()).start();
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
                    gm.getTank().tankFireEvent();
                    break;
            }
            setMarkDir();
        }

        private void setMarkDir(){
            Tank tank = gm.getTank();
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
}
