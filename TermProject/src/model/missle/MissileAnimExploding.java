package model.missle;

import java.awt.*;

public class MissileAnimExploding implements MissileAnimStrategy {

    Missile context;

    public MissileAnimExploding(Missile context){
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.YELLOW;
        ++context.size;
    }
}
