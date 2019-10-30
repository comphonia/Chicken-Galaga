package model.bird;

import java.awt.*;

public class BirdAnimFalling implements BirdAnimStrategy {
    Bird context;

    public BirdAnimFalling(Bird context){
        this.context = context;
        context.AVAILABLE_UNITS--;
    }
    @Override
    public void animate() {
        context.color = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;
    }
}
