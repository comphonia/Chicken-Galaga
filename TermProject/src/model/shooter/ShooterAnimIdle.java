package model.shooter;

import java.awt.*;

public class ShooterAnimIdle implements ShooterAnimStrategy {
    Shooter context;
    public ShooterAnimIdle(Shooter context){
        this.context = context;
    }

    @Override
    public void animate() {
    }
}