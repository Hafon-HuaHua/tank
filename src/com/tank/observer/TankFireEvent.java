package com.tank.observer;

import com.tank.Tank;

public class TankFireEvent {
    private Tank tank;
    public Tank getSource(){
        return tank;
    }
    public TankFireEvent(Tank tank){
        this.tank = tank;
    }
}
