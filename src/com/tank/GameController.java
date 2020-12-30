package com.tank;

import com.tank.main.TankFrame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @RequestMapping(value = "/play",method = RequestMethod.POST)
    public String play(String name){
        System.out.println("name...." + name);
        TankFrame tankFrame = new TankFrame();
        while (true){
            try {
                Thread.sleep(10);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
