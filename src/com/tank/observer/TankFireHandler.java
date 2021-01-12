package com.tank.observer;

import com.tank.Tank;

public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionEvent(TankFireEvent event) {
        Tank t = event.getSource();
        t.fire();
    }
}
