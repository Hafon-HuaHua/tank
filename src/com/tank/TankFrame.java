package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Tank tank = new Tank(200,200,DirEnum.DOWN);

    public TankFrame(){
        setSize(800,900);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        tank.paint(g);
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
}
