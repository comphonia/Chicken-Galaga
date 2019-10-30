package model.missle;

public class MissileAnimShooting implements MissileAnimStrategy {
    Missile context;
    public MissileAnimShooting(Missile context){
        this.context = context;
    }

    @Override
    public void animate() {
        context.location.y += context.UNIT_MOVE * Math.sin(-90);
    }
}
