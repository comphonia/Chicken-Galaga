package model.asteroid;

public class AsteroidAnimEntry implements AsteroidAnimStrategy {

    Asteroid context;

    public AsteroidAnimEntry(Asteroid context){
        this.context = context;
    }

    @Override
    public void animate() {
        if(context.location.x <= context.destinationx){
            context.location.x += context.UNIT_MOVE;
        }
        if(context.location.y <= context.destinationy){
            context.location.y += context.UNIT_MOVE;
        }
    }
}
