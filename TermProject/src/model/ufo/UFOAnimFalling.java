package model.ufo;

import java.awt.*;

public class UFOAnimFalling implements UFOAnimStrategy {
    UFO context;

    public UFOAnimFalling(UFO context){
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;
    }
}
