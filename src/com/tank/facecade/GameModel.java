package com.tank.facecade;

import com.tank.*;
import com.tank.abstractfactory.DefaultFactory;
import com.tank.abstractfactory.GameFactory;
import com.tank.chain.Collider;
import com.tank.chain.ColliderChain;
import com.tank.chain.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    /*主战坦克*/
    private Tank tank = new Tank(200,400, DirEnum.UP, GroupEnum.GOOD,this);
    /*子弹、敌方坦克、爆炸*/
    private List<GameObject> gameObjects = new ArrayList<>();
    /*碰撞接口*/
    //private Collider collider = new TankTankCollider();
    private Collider chain = new ColliderChain();

    private GameFactory gameFactory = DefaultFactory.getInstance();

    public GameModel(){
        /*初始化敌方坦克*/
        initEnemyTanks();
        /*初始化墙*/
        //initWalls();
    }

    private void initWalls() {
        add(new Wall(200,200,120,100));
        add(new Wall(400,300,220,130));
    }

    public void add(GameObject gm){
        gameObjects.add(gm);
    }
    /**
     * 初始化敌方坦克
     */
    private void initEnemyTanks(){
        int enemyTankCount = PropertiesMgr.getIntVal("initBadTankCount");
        for(int i = 0; i < enemyTankCount; i++){
            Tank tank = new Tank(180 + i * 180,200 + i * 120,DirEnum.DOWN, GroupEnum.BAD,this);
            add(tank);
        }
    }
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + bullets.size(),10,40);
//        g.drawString("敌方坦克的数量：" + enemyTanks.size(),10,80);
//        g.drawString("爆炸数量：" + booms.size(),10,120);
        g.setColor(c);
        /*画主战坦克*/
        tank.paint(g);
        /*画故方坦克*/
        for(int i = 0; i<gameObjects.size();i++){
            gameObjects.get(i).paint(g);
        }
        /*逐一判断子弹与坦克是否相撞，如果相撞就双双销毁*/
        for(int i = 0;i < gameObjects.size();i++){
            for(int j = i + 1; j < gameObjects.size();j++){
                //bullets.get(i).destoryTanks(enemyTanks.get(j));
                chain.collide(gameObjects.get(i),gameObjects.get(j));
            }
        }
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }
}
