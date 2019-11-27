package model.asteroid;

import controller.Main;

public class AsteroidAnimFlying implements AsteroidAnimStrategy {

    Asteroid context;

    public AsteroidAnimFlying(Asteroid context) {
        this.context = context;
    }

    @Override
    public void animate() {
        if (context.location.x >= Main.win.canvas.width) {
            context.movingRight = false;
        } else if (context.location.x <= 0) {
            context.movingRight = true;
        }
        if (context.movingRight) context.location.x += context.UNIT_MOVE;
        else context.location.x -= context.UNIT_MOVE;
    }
}
