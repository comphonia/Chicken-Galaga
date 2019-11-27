package model.asteroid;

import java.awt.*;

public class AsteroidAnimFalling implements AsteroidAnimStrategy {
    Asteroid context;

    public AsteroidAnimFalling(Asteroid context){
        this.context = context;
        context.AVAILABLE_UNITS--;
    }
    @Override
    public void animate() {
        context.color = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;
    }
}
