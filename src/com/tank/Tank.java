package com.tank;

import com.tank.abstractfactory.BaseTank;
import com.tank.abstractfactory.DefaultFactory;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Tank extends BaseTank {
    private int x;
    private int y;
    private DirEnum dir = DirEnum.DOWN;
    private static final int SPEED = PropertiesMgr.getIntVal("tankSpeed");
    private boolean isLive = true;
    private boolean moving = true;
    public static int WIDTH = ResourceMgr.badTankD.getWidth();
    public static int HEIGHT = ResourceMgr.badTankD.getHeight();
    private TankFrame tf;
    private Random r = new Random();
    private GroupEnum groupEnum = GroupEnum.BAD;
    private Rectangle rect = new Rectangle();
    private FireStrategy fs = FourFireStrategy.getInstance();
    public Tank(int x, int y, DirEnum dir, GroupEnum groupEnum, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.groupEnum = groupEnum;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
//        if(groupEnum == GroupEnum.GOOD){
//            String classPath = PropertiesMgr.getStringVal("goodFs");
//            try {
//                fs = (FireStrategy) Class.forName(classPath).newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 画坦克
     * @param g
     */
    public void paint(Graphics g){
        if(!isLive){
            tf.enemyTanks.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ?ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.groupEnum == GroupEnum.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
        }
        moving();
    }

    /**
     * 坦克移动
     */
    private void moving(){
        if(!moving){
            return;
        }
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if(this.groupEnum == GroupEnum.BAD && r.nextInt(40) == 9){
            this.fire();
        }
        if(this.groupEnum == GroupEnum.BAD && r.nextInt(40) == 9){
            randomDir();
        }
        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;
    }

    /**
     * 边界检查
     */
    private void boundsCheck() {
        if(this.x < 0){
            this.x = 0;
        }
        if(this.y < 28){
            this.y = 28;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH){
            this.x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT){
            this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    /**
     * 坦克开火
     */
    public void fire() {
        if(this.groupEnum == GroupEnum.GOOD){
            new Thread(()-> new AudioUtil("audio/tank_fire.wav").play()).start();
        }
        int bX = this.x + Tank.WIDTH/2 - Bullet.WEIGHT/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        fs.fire(this);
        //tf.bullets.add(new Bullet(bX,bY,this.dir,this.groupEnum,this.tf));
    }

    /**
     * 坦克死亡
     * @throws IOException
     */
    public void die(){
        this.isLive = false;
        int eX = this.getX() + Tank.WIDTH/2 - Boom.WIDTH/2;
        int eY = this.getY() + Tank.HEIGHT/2 - Boom.HEIGHT/2;
        tf.booms.add(tf.getGameFactory().createBoom(eX,eY,tf));
    }

    private void randomDir() {
        this.dir = DirEnum.values()[r.nextInt(4)];
    }

    public GroupEnum getGroup() {
        return groupEnum;
    }

    public void setGroup(GroupEnum groupEnum) {
        this.groupEnum = groupEnum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDir(DirEnum dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Rectangle getRect() {
        return rect;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirEnum getDir() {
        return dir;
    }
}