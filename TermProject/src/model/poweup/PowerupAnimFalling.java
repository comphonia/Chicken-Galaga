package model.poweup;

import controller.Main;

import java.awt.*;

public class PowerupAnimFalling implements PowerupAnimStrategy {
    Powerup context;
    private int tempTime=0;
    private float saveX, saveY;
    public PowerupAnimFalling(Powerup context){
        this.context = context;
        saveX = context.location.x;
        saveY = context.location.y;
    }

    @Override
    public void animate() {
            context.location.y -= context.UNIT_MOVE * Math.sin(-90);
    }
}
