package com.tank;

import com.tank.main.TankFrame;

public class FrameTest {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true){
            Thread.sleep(10);
            tankFrame.repaint();
        }
    }

}
