package com.tank.facecade;

import com.tank.DirEnum;
import com.tank.GroupEnum;
import com.tank.PropertiesMgr;
import com.tank.Tank;
import com.tank.abstractfactory.BaseBoom;
import com.tank.abstractfactory.BaseBullet;
import com.tank.abstractfactory.DefaultFactory;
import com.tank.abstractfactory.GameFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    /*主战坦克*/
    private Tank tank = new Tank(200,400, DirEnum.UP, GroupEnum.GOOD,this);
    /*敌方坦克*/
    private List<Tank> enemyTanks = new ArrayList<>();
    /*子弹*/
    private List<BaseBullet> bullets = new ArrayList<>();
    /*爆炸*/
    private List<BaseBoom> booms = new ArrayList<>();

    private GameFactory gameFactory = DefaultFactory.getInstance();

    public GameModel(){
        /*初始化敌方坦克*/
        initEnemyTanks();
    }

    /**
     * 初始化敌方坦克
     */
    private void initEnemyTanks(){
        int enemyTankCount = PropertiesMgr.getIntVal("initBadTankCount");
        for(int i = 0; i < enemyTankCount; i++){
            Tank tank = new Tank(180 + i * 10,200,DirEnum.DOWN, GroupEnum.BAD,this);
            enemyTanks.add(tank);
        }
    }
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(),10,40);
        g.drawString("敌方坦克的数量：" + enemyTanks.size(),10,80);
        g.drawString("爆炸数量：" + booms.size(),10,120);
        g.setColor(c);
        /*画主战坦克*/
        tank.paint(g);
        /*画故方坦克*/
        for(int i = 0; i<enemyTanks.size();i++){
            enemyTanks.get(i).paint(g);
        }
        /*画子弹*/
        for(int i = 0; i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        /*画爆炸效果*/
        for(int i = 0; i<booms.size();i++){
            booms.get(i).paint(g);
        }
        /*逐一判断子弹与坦克是否相撞，如果相撞就双双销毁*/
        for(int i = 0;i < bullets.size();i++){
            for(int j = 0; j < enemyTanks.size();j++){
                bullets.get(i).destoryTanks(enemyTanks.get(j));
            }
        }
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public List<BaseBullet> getBullets() {
        return bullets;
    }

    public List<BaseBoom> getBooms() {
        return booms;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }
}
